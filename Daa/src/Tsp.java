import java.io.*;
import java.util.*;
public class Tsp {
private int N;
 private Stack<Integer> s;
 public Tsp() {
 s = new Stack<Integer>();
 }
 public void travel(int arr[][])
 {
 N = arr[1].length - 1;
 int[] d = new int[N + 1];
 d[1] = 1;
 s.push(1);
 int x, dist = 0, y;
 int m = Integer.MAX_VALUE;
 boolean mf = false;
 System.out.print(1+ "\t");
 while (!s.isEmpty())
 {
 x = s.peek();
 y = 1;
 m = Integer.MAX_VALUE;
 while (y <= N) {
 if (arr[x][y] > 1 && d[y] == 0)
 {
 if (m > arr[x][y])
 {
 m = arr[x][y];
 dist = y;
 mf = true;
 }
 }
 y++;
 }
 if (mf)
 {
 d[dist] = 1;
 s.push(dist);
 System.out.print(dist+"\t");
 mf = false;
 continue;
 }
 s.pop();
 }
 }
 public static void main(String args[]) {
 Scanner s=null;
 int n;
 try {
 System.out.println("Enter the number of nodes: ");
 s=new Scanner(System.in);
 n=s.nextInt();
 int arr[][]=new int[n+1][n+1];
 System.out.println("Enter the matrix: ");
 for(int y=1;y<=n;y++) {
 for(int z=1;z<=n;z++) {
 arr[y][z]=s.nextInt();
 }
 }
 for(int y=1;y<=n;y++) {
 for(int z=1;z<=n;z++) {
 if(arr[y][z]==1 && arr[z][y]==0) {
 arr[z][y] = 1;
 }
 }
 }
 System.out.println("Shortest possible Route: ");
 Tsp t= new Tsp();
 t.travel(arr);
 }
 catch (InputMismatchException inputMismatch)
 {
 System.out.println("Enter the correct input");
 }
 s.close();
 }
}