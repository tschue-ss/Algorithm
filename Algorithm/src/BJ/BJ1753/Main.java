package BJ.BJ1753;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import BJ.Common.StringUtils; 

/** 
 * 
 * @author tschuss
 * @since 2021-09-22
 * 
 * @category Algorithm
 * @category Dijkstra's
 * 
 * @url https://www.acmicpc.net/problem/1753  
 *
 */

/* Sample Answer
0
2
3
7
INF
*/

public class Main {
	static int V, E, K;
	static ArrayList<Node>[] Dijkstra;
	static int[] MinCost;
	static boolean[] Visited;
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	private static class Node implements Comparable<Node> {
		int idx, cost;
		
		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			// ASC
			return this.cost - n.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File(StringUtils.getFilePath(Main.class) + "/input.txt")));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = stoi(st.nextToken());
		E = stoi(st.nextToken());
		K = stoi(br.readLine());
		
		Dijkstra = new ArrayList[V + 1];
		MinCost = new int[V + 1];
		Visited = new boolean[V + 1];
		Arrays.fill(MinCost, Integer.MAX_VALUE);
		
		for (int v = 0; v <= V; v++) {
			Dijkstra[v] = new ArrayList<>();
		}
		
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			int cost = stoi(st.nextToken());
			
			Dijkstra[from].add(new Node(to, cost));
		}
		
		dijkstras(K);
		
		for (int v = 1; v <= V; v++) {
			if (MinCost[v] == Integer.MAX_VALUE) {
				sb.append("INF\n");
			} else {
				sb.append(MinCost[v] + "\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
    
	private static void dijkstras(int idx) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(idx, 0));
		MinCost[idx] = 0;
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int nowIdx = now.idx;
			int nowCost = now.cost;
			
			if(Visited[nowIdx]) continue;	        
			Visited[nowIdx] = true;
			
			for (Node next : Dijkstra[nowIdx]) {
				int nextidx = next.idx;
				int nextCost = next.cost;
				if (MinCost[nextidx] > (nowCost + nextCost)) {
					MinCost[nextidx] = nowCost + nextCost;
					pq.add(new Node(nextidx, MinCost[nextidx]));
				}
			}
		}
	}
}