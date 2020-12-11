import java.io.*;
import java.util.*;
public class Kmp {
	private int[] f;
	public Kmp(String text,String pat) {
		f=new int[pat.length()];
		fail(pat);
		int pos=posmatch(text,pat);
		if(pos == -1)
			System.out.println("NO MATCHING");
		else
			System.out.println("MATCHING FOUND AT:"+pos);
	}
	private void fail(String pat) {
		int n=pat.length();
		f[0]=-1;
		for(int j=1;j<n;j++) {
			int i=f[j-1];
			while((pat.charAt(j) !=pat.charAt(i+1)) && i>=0)
				i=f[i];
			if(pat.charAt(j) == pat.charAt(i+1))
				f[j]=i+1;
			else
				f[j]=-1;
		}
	}
	private int posmatch(String text,String pat) {
		int i=0,j=0;
		int len=text.length();
		int lenp=pat.length();
		while(i <len && j<lenp) {
			if(text.charAt(i) == pat.charAt(j)) {
				i++;
				j++;
			}
			else if(j==0)
				i++;
			else
				j=f[j-1]+1;
		}
		return ((j==lenp) ? (i-lenp) : -1);
	}
	public static void main(String[]args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter text:");
		String text=sc.nextLine();
		System.out.println("Enter pattern : ");
		String pattern=sc.nextLine();
		Kmp a=new Kmp(text,pattern);
	}

}
