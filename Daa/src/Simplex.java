public class Simplex {
 private double[][] tableaux; 
 private int numberOfConstraints; 
 private int numberOfOriginalVariables; 
 private boolean maximizeOrMinimize;
 private static final boolean MAXIMIZE = true;
 private static final boolean MINIMIZE = false;
 private int[] basis; 
 public Simplex(double[][] tableaux, int numberOfConstraint,
   int numberOfOriginalVariable, boolean maximizeOrMinimize) {
  this.maximizeOrMinimize = maximizeOrMinimize;
  this.numberOfConstraints = numberOfConstraint;
  this.numberOfOriginalVariables = numberOfOriginalVariable;
  this.tableaux = tableaux;
  basis = new int[numberOfConstraints];
  for (int i = 0; i < numberOfConstraints; i++)
   basis[i] = numberOfOriginalVariables + i;
  solve();
 }
 private void solve() {
  while (true) {
   show();
   int q = 0;
   if (maximizeOrMinimize) {
    q = dantzig();
   } else {
    q = dantzigNegative();
   }
   if (q == -1)
    break;
   int p = minRatioRule(q);
   if (p == -1)
    throw new ArithmeticException("Linear program is unbounded");
   pivot(p, q);
   basis[p] = q;
  }
 }
 private int dantzig() {
  int q = 0;
  for (int j = 1; j < numberOfConstraints + numberOfOriginalVariables; j++)
   if (tableaux[numberOfConstraints][j] > tableaux[numberOfConstraints][q])
    q = j;
  if (tableaux[numberOfConstraints][q] <= 0)
   return -1; 
  else
   return q;
 }
 private int dantzigNegative() {
  int q = 0;
  for (int j = 1; j < numberOfConstraints + numberOfOriginalVariables; j++)
   if (tableaux[numberOfConstraints][j] < tableaux[numberOfConstraints][q])
    q = j;
  if (tableaux[numberOfConstraints][q] >= 0)
   return -1; 
  else
   return q;
 }
 private int minRatioRule(int q) {
  int p = -1;
  for (int i = 0; i < numberOfConstraints; i++) {
   if (tableaux[i][q] <= 0)
    continue;
   else if (p == -1)
    p = i;
   else if ((tableaux[i][numberOfConstraints
     + numberOfOriginalVariables] / tableaux[i][q]) < (tableaux[p][numberOfConstraints
     + numberOfOriginalVariables] / tableaux[p][q]))
    p = i;
  }
  return p;
 }
 private void pivot(int p, int q) {
  for (int i = 0; i <= numberOfConstraints; i++)
   for (int j = 0; j <= numberOfConstraints
     + numberOfOriginalVariables; j++)
    if (i != p && j != q)
     tableaux[i][j] -= tableaux[p][j] * tableaux[i][q]
       / tableaux[p][q];
  for (int i = 0; i <= numberOfConstraints; i++)
   if (i != p)
    tableaux[i][q] = 0.0;
  for (int j = 0; j <= numberOfConstraints + numberOfOriginalVariables; j++)
   if (j != q)
    tableaux[p][j] /= tableaux[p][q];
  tableaux[p][q] = 1.0;
 }
 public double value() {
  return -tableaux[numberOfConstraints][numberOfConstraints
    + numberOfOriginalVariables];
 }
 public double[] primal() {
  double[] x = new double[numberOfOriginalVariables];
  for (int i = 0; i < numberOfConstraints; i++)
   if (basis[i] < numberOfOriginalVariables)
    x[basis[i]] = tableaux[i][numberOfConstraints
      + numberOfOriginalVariables];
  return x;
 }
 public void show() {
  System.out.println("M = " + numberOfConstraints);
  System.out.println("N = " + numberOfOriginalVariables);
  for (int i = 0; i <= numberOfConstraints; i++) {
   for (int j = 0; j <= numberOfConstraints
     + numberOfOriginalVariables; j++) {
    System.out.printf("%7.2f ", tableaux[i][j]);
   }
   System.out.println();
  }
  System.out.println("value = " + value());
  for (int i = 0; i < numberOfConstraints; i++)
   if (basis[i] < numberOfOriginalVariables)
    System.out.println("x_"
      + basis[i]
      + " = "
      + tableaux[i][numberOfConstraints
        + numberOfOriginalVariables]);
  System.out.println();
 }
 public static void main(String[] args) {
  double[] objectiveFunc = { 3, 2 };
  double[][] constraintLeftSide = {{ 1, 2 }, { 2, 1 }, { -1, 1 }, { 0, 1 } };
  Constraint[] constraintOperator = { Constraint.lessThan,
    Constraint.lessThan, Constraint.lessThan, Constraint.lessThan };
  double[] constraintRightSide = { 6, 8, 1, 2 };
  Modeler model = new Modeler(constraintLeftSide, constraintRightSide,
    constraintOperator, objectiveFunc);
  Simplex simplex = new Simplex(model.getTableaux(),
    model.getNumberOfConstraint(),
    model.getNumberOfOriginalVariable(), MAXIMIZE);
  double[] x = simplex.primal();
  for (int i = 0; i < x.length; i++)
   System.out.println("x[" + i + "] = " + x[i]);
  System.out.println("Solution: " + simplex.value());
 }
 private enum Constraint {
  lessThan, equal, greatherThan
 }
 public static class Modeler {
  private double[][] a; 
  private int numberOfConstraints; 
  private int numberOfOriginalVariables; 
  public Modeler(double[][] constraintLeftSide,
    double[] constraintRightSide, Constraint[] constraintOperator,
    double[] objectiveFunction) {
   numberOfConstraints = constraintRightSide.length;
   numberOfOriginalVariables = objectiveFunction.length;
   a = new double[numberOfConstraints + 1][numberOfOriginalVariables + numberOfConstraints + 1];
   for (int i = 0; i < numberOfConstraints; i++) {
    for (int j = 0; j < numberOfOriginalVariables; j++) {
     a[i][j] = constraintLeftSide[i][j];
    }
   }

   for (int i = 0; i < numberOfConstraints; i++)
    a[i][numberOfConstraints + numberOfOriginalVariables] = constraintRightSide[i];
   for (int i = 0; i < numberOfConstraints; i++) {
    int slack = 0;
    switch (constraintOperator[i]) {
    case greatherThan:
     slack = -1;
     break;
    case lessThan:
     slack = 1;
     break;
    default:
    }
    a[i][numberOfOriginalVariables + i] = slack;
   }
   for (int j = 0; j < numberOfOriginalVariables; j++)
    a[numberOfConstraints][j] = objectiveFunction[j];
  }

  public double[][] getTableaux() {
   return a;
  }

  public int getNumberOfConstraint() {
   return numberOfConstraints;
  }

  public int getNumberOfOriginalVariable() {
   return numberOfOriginalVariables;
  }
 }
}