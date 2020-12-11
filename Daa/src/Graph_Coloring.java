import java.util.*;
public class Graph_Coloring {
	private int V,nofcolors;
	private int[] color;
	private int[][] graph;
	public void graphColor(int[][] g,int noc) {
		V=g.length;
		nofcolors=noc;
		color=new int[V];
		graph=g;
		try {
			solve(0);
			System.out.println("No Solution");
		}
		catch(Exception e) {
			System.out.println("Solution exists");
		}
	}
	public void solve(int v) throws Exception{
		if(v==V)
			throw new Exception("Solution found");
		for(int c=1;c<=nofcolors;c++) {
			if(isPossible(v,c)) {
				color[v]=c;
				solve(v+1);
				color[v]=0;
			}
		}
	}
	public boolean isPossible(int v,int c) {
		for(int i=0;i<V;i++)
			if(graph[v][i]==1 && c==color[i])
				return false;
		return true;
	}
	public void display() {
		System.out.println("colors: ");
		for(int i=0;i<V;i++)
			System.out.println(color[i]+ " ");
		System.out.println();
	}
	public static void main(String[]args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter no.of.vertices:");
		int V=sc.nextInt();
		System.out.println("Enter matrix:");
		int [][] g=new int[V][V];
		for(int i=0;i<V;i++)
			for(int j=0;j<V;j++)
				g[i][j]=sc.nextInt();
		System.out.println("Enter no.of colors:");
		int c=sc.nextInt();
		Graph_Coloring gc= new Graph_Coloring();
		gc.graphColor(g,c);
	}

}
