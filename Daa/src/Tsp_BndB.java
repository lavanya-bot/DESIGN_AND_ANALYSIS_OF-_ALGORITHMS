import java.io.*;
import java.util.*;
public class Tsp_BndB { 
	static int N=4;
	static int f_path[] = new int[N + 1];
	static boolean visited[] = new boolean[N];
	static int f_res = Integer.MAX_VALUE;
	static void Final(int c_path[]) 
	{ 
		for (int i = 0; i < N; i++) 
			f_path[i] = c_path[i]; 
		f_path[N] = c_path[0]; 
	} 
	static int FirstMin(int m[][], int i) 
	{ 
		int min = Integer.MAX_VALUE; 
		for (int k = 0; k < N; k++) 
			if (m[i][k] < min && i != k) 
				min = m[i][k]; 
		return min; 
	}
	static int SecondMin(int m[][],int i) 
	{
		int first=Integer.MAX_VALUE,second=Integer.MAX_VALUE;
		for (int j=0; j<N; j++) 
		{ 
			if (i == j) 
				continue; 
			if (m[i][j] <= first) 
			{ 
				second = first; 
				first = m[i][j]; 
			} 
			else if (m[i][j] <= second && m[i][j] != first) 
				second = m[i][j]; 
		} 
		return second; 
	} 
	static void TSPrec(int m[][], int c_bound, int c_weight, int level, int c_path[]) {
		if (level == N) 
		{ 
			if (m[c_path[level - 1]][c_path[0]] != 0) 
			{  
				int curr_res = c_weight + m[c_path[level-1]][c_path[0]]; 
				if (curr_res < f_res) 
				{ 
					Final(c_path); 
					f_res = curr_res; 
				} 
			}
			return;
	}
		for (int i = 0; i < N; i++) {
			if (m[c_path[level-1]][i] != 0 && visited[i] == false) {
				int temp = c_bound; 
				c_weight += m[c_path[level - 1]][i]; 
				if (level==1) 
					c_bound -= ((FirstMin(m, c_path[level - 1]) + FirstMin(m, i))/2); 
					else
					c_bound -= ((SecondMin(m, c_path[level - 1]) + FirstMin(m, i))/2);
				if (c_bound + c_weight < f_res) {
					c_path[level] = i; 
					visited[i] = true; 
					TSPrec(m, c_bound, c_weight, level + 1,c_path); 
				}
				c_weight -= m[c_path[level-1]][i]; 
				c_bound = temp;
				Arrays.fill(visited,false); 
				for (int j = 0; j <= level - 1; j++) 
					visited[c_path[j]] = true;
			}
				
			}
		}
	static void TSP(int m[][]) {
		int c_path[] = new int[N + 1];
		int c_bound = 0; 
		Arrays.fill(c_path, -1); 
		Arrays.fill(visited, false); 
		for (int i = 0; i < N; i++) 
			c_bound += (FirstMin(m, i) +SecondMin(m, i));
		    c_bound = (c_bound==1)? c_bound/2 + 1 : c_bound/2;
		    visited[0] = true; 
			c_path[0] = 0; 
			TSPrec(m, c_bound, 0, 1, c_path);   
	}
public static void main(String[]args) {
	int m[][] = {{0, 10, 15, 20}, 
			{10, 0, 35, 25}, 
			{15, 35, 0, 30}, 
			{20, 25, 30, 0} };
			TSP(m); 

			System.out.printf("Cost : %d\n", f_res); 
			System.out.printf("Path : "); 
			for (int i = 0; i <= N; i++) 
			{ 
				System.out.printf("%d ", f_path[i]); 
			} 
		}
}


