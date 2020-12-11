import java.util.*;
public class Matrix_multiplication {
	static int Order(int b[],int n) {
		int i,l,k,p;
		int a[][] =new int [n][n];
		for(i=1;i<n;i++) 
			a[i][i]=0;
		for(l=2;l<n;l++) {
			for(i=1;i<n-l+1;i++) {
				int j=i+l-1;
				if(j==n)
					continue;
				a[i][j]=Integer.MAX_VALUE;
				for(k=i;k<=j-1;k++) {
					p=a[i][k]+a[k+1][j]+b[i-1]*b[k]*b[j];
					if(p<a[i][j])
						a[i][j]=p;
				}
			}
		}
		return a[1][n-1];
	}
	public static void main(String[]args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter size: ");
		int size=sc.nextInt();
		System.out.println("Enter elements: ");
		int Mar[]=new int [size];
		for(int m=0; m<size; m++)  
		{     
		Mar[m]=sc.nextInt();  
		}
		System.out.println("Min no.of multiplication : "+Order(Mar,size));
		
	}

}
