import java.io.*;
import java.util.*;
public class Boyer_Moore {
	public void find(String tex,String patt) {
		char[] text=tex.toCharArray();
		char[] pattern=patt.toCharArray();
		int posi = indexof(text,pattern);
		if(posi == -1)
			System.out.println(" NO MATCHING");
		else
			System.out.println("PATTER FOUND AT POSITION : "+posi);
	}
	public int indexof(char[] text, char[] pattern) {
		if(pattern.length ==0)
			return 0;
		int chartable[] = makechartable(pattern);
		int offsettable[] = makeoffsettable(pattern);
		for(int i=pattern.length -1,j;i < text.length;)
		{
			for(j=pattern.length-1;pattern[j]==text[i];--i,--j)
				if(j==0)
					return i;
			i+=Math.max(offsettable[pattern.length-1-j], chartable[text[i]]);
		}
		return -1;
	}
private int[] makechartable(char[] pattern) {
	final int alphabet_size=256;
	int [] table=new int[alphabet_size];
	for(int i=0;i<table.length;++i)
		table[i]=pattern.length;
	for(int i=0;i<pattern.length-1;++i)
		table[pattern[i]]=pattern.length-1-i;
	return table;
}
private static int[] makeoffsettable(char[] pattern) {
	int [] table=new int[pattern.length];
	int lastprefixposition=pattern.length;
	for(int i=pattern.length-1;i>=0;--i) {
		if(isprefix(pattern,i+1))
			lastprefixposition=i+1;
		table[pattern.length-1-i]=lastprefixposition-i+pattern.length-1;
	}
	for(int i=0;i<pattern.length-1;++i) {
		int sleng=suffixlength(pattern,i);
		table[sleng]=pattern.length-1-i+sleng;
	}
	return table;
}
private static boolean isprefix(char[] pattern,int p) {
	for(int i=p,j=0;i<pattern.length;++i,++j)
		if(pattern[i] != pattern[j])
			return false;
	return true;
}
private static int suffixlength(char[] pattern,int p) {
	int len=0;
	for(int i=p,j=pattern.length-1;i>=0 && pattern[i]== pattern[j];--i,--j)
		len+=1;
	return len;
}
public static void main(String[]args) {
	Scanner sc=new Scanner (System.in);
	System.out.println("Enter text : ");
	String text=sc.nextLine();
	System.out.println("Enter pattern to search:");
	String pattern=sc.nextLine();
	Boyer_Moore bm= new Boyer_Moore();
	bm.find(text, pattern);
}
		}

