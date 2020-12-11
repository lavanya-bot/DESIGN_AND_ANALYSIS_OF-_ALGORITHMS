import java.util.*;
import java.io.IOException;
public class Knapsack_greedy{
public static void main(String args[]) throws IOException{
 int x,y=0,wt,i,j;
 float sum=0,m;
 Scanner sc = new Scanner(System.in);
 int arr[][]=new int[2][20];
 System.out.println("Enter the items number: ");
 j=sc.nextInt();
 System.out.println("Enter the weights: ");
 for(x=0;x<j;x++)
 arr[0][x]=sc.nextInt();
 System.out.println("Enter the values: ");
 for(x=0;x<j;x++)
 arr[1][x]=sc.nextInt();
 System.out.println("Enter the volume: ");
 wt=sc.nextInt();
 i=wt;
 while(i>=0)
 {
 m=0;
 for(x=0;x<j;x++)
 {
 if(((float)arr[1][x])/((float)arr[0][x])>m)
 {
 m=((float)arr[1][x])/((float)arr[0][x]);
 y=x;
 }
 }
 if(arr[0][y]>i)
 {
 System.out.println("Quantity: " +(y+1)+ "added to the item is" +i);
 sum+=i*m;
 i=-1;
 }
 else
 {
 System.out.println("Quantity: " + (y+1) + " added to thae item is" + arr[0][y]); 
 i-=arr[0][y];
 sum+=(float)arr[1][y];
 arr[1][y]=0;
 }
 }
 System.out.println("Total profit: " + sum);
 sc.close();
 }
 }
