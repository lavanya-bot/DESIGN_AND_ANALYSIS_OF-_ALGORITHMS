import java.io.*;
import java.util.*;
public class Lcs {
	public int lcs(char[] X,char [] Y,int a,int b) {
		int [][] arr=new int[a+1][b+1];
		for(int i=0;i<=a;i++){
			for(int j=0;j<=b;j++) {
				if(i==0 || j==0)
					arr[i][j]=0;
				else if(X[i-1] == Y[j-1])
					arr[i][j] =arr[i-1][j-1]+1;
				else
					arr[i][j]=max(arr[i-1][j],arr[i][j-1]);
			}
		}
		return arr[a][b];
		
	}
	int max(int q,int w) {
		return(q>w)?q:w;
	}

public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	Lcs lcs=new Lcs();
	System.out.println("Enter string : ");
	String str1=sc.next();
	System.out.println("Enter another string: ");
	String str2=sc.next();
	char [] X =str1.toCharArray();
	char [] Y=str2.toCharArray();
	int a=X.length;
	int b=Y.length;
	System.out.println("Length is: "+" "+lcs.lcs(X,Y,a,b));
}
}
