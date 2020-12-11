public class BranchandBound {
static void BranchandBound(int capacity, int wt[],int value[], int n){
int i, j;
int arr[][] = new int[n + 1][capacity + 1];
for (i = 0; i <= n; i++) {
for (j = 0; j <= capacity; j++) {
if (i == 0 || j == 0)
arr[i][j] = 0;
else if (wt[i - 1] <= j)
arr[i][j] = Math.max(value[i - 1] + arr[i - 1][j - wt[i - 1]],arr[i - 1][j]);
else
arr[i][j] =arr[i - 1][j];
}
}
int r = arr[n][capacity];
System.out.println("Maximum profit: "+r);
j= capacity;
for (i = n; i > 0 && r > 0; i--) {
if (r == arr[i - 1][j])
continue;
else {
System.out.println("weight of the item: "+wt[i - 1]);
r = r - value[i - 1];
j = j - wt[i - 1];
}
}
}
public static void main(String arg[]){
int value[] = { 10,20,30};
int wt[] = { 20,15,25};
int capacity =100;
int n = value.length;
BranchandBound(capacity, wt, value, n);
}
static int max(int a, int b){
return (a > b) ? a : b;
}
}
