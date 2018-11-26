package solutions;

import java.util.*;
import java.io.*;

/**
 * UVa 558
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=499
 * @author Joe - Kliver - Christian Tamayo
 */
public class Solution1 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int t = Integer.parseInt(in.readLine());
		
		while (t-->0) {
			String line[] = in.readLine().split(" ");
			
			int V = Integer.parseInt(line[0]), E = Integer.parseInt(line[1]);
			
			Graph graph = new Graph(V);
			
			while (E-->0) {
				line = in.readLine().split(" ");
				
				int s = Integer.parseInt(line[0]);
				int e = Integer.parseInt(line[1]);
				int w = Integer.parseInt(line[2]);
				
				graph.addEdge(s, e, w);
			}
			
			out.write((graph.dijkstraList(0)?"possible\n":"not possible\n"));
		}
		
		in.close();
		out.close();
		
	}
	
	
	public static class Graph { 
		public static final int INF = 1000000000;
		private int V; // No. of vertices 
		private HashMap<Integer, Integer> adjW[]; //Adjacency Lists 
		
		@SuppressWarnings("unchecked")
		Graph(int v) { 
			V = v; 
			adjW = new HashMap[v];
			//Matrix
			for (int i=0; i<v; ++i) 
				adjW[i] = new HashMap<>(); 
		} 
		// Con peso
		void addEdge(int s,int e, int w) {
			Integer pW = adjW[s].get(e);
			
			if (pW != null) {
				if (pW>w) 
					adjW[s].put(e, w);
			}
			else 
				adjW[s].put(e, w);
			
//			adjW[e].put(s, w);
		} 
		
		//Para Listas
		boolean dijkstraList(int s) {
			
			// Dijkstra routine
			int dist[] = new int[V];
			Arrays.fill(dist, INF);
			boolean vis[] = new boolean[V];
			
			dist[s] = 0; 
			
			PriorityQueue<pair> pq = new PriorityQueue<>();
			
			pq.offer(new pair(s, 0)); // sort based on increasing distance
			
			while (!pq.isEmpty()) { // main loop
				pair p = pq.poll();
				int x = p.y;
//	    		int wei = p.w;
				
				if (!vis[x]) {
					vis[x] = true; 
					
					Set<Map.Entry<Integer, Integer>> adjacent = adjW[x].entrySet();
					
					for (Map.Entry<Integer, Integer> entry : adjacent) {
						int e = entry.getKey();
						int w = entry.getValue();
						
						if (dist[x]+w < dist[e]) {
							dist[e] = dist[x]+w;  
							pq.offer(new pair(e, dist[e]));
						}
					}
				}
			}
			
			boolean st = false;
			
			for (int i = 0; i < V; i++) {
				int u = i;
				for (Map.Entry<Integer, Integer> entry : adjW[i].entrySet()) {
					int v = entry.getKey();
					int w = entry.getValue();
					
					if (dist[u] != INF && dist[u]+w < dist[v]) 
						st = true;
				}
				
			}
			
			return st;
		}
		
		public static class pair implements Comparable<pair>{
			int y;
			int w;
			public pair(int y, int w) {
				this.y = y;
				this.w = w;
			}
			@Override
			public int compareTo(pair o) {
				return w-o.w;
			}
		}
	}
}
