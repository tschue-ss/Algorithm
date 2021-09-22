package BJ.BJ1916;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
 * @url https://www.acmicpc.net/problem/1916  
 *
 */

/* Sample Answer
4
*/

public class Main {
	static int N, M, From, To;
	static ArrayList<Node>[] Dijkstra;
	static int[] Answer;
	static boolean[] Visited;
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static class Node implements Comparable<Node> {
		int idx, cost;

		Node (int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.cost - n.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// init
		System.setIn(new FileInputStream(new File(StringUtils.getFilePath(Main.class) + "/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// input
		N = stoi(br.readLine());
		M = stoi(br.readLine());
		
		Dijkstra = new ArrayList[N + 1];
		Visited = new boolean[N + 1];
		Answer = new int[N + 1];
		Arrays.fill(Answer, Integer.MAX_VALUE);
		
		for (int n = 0; n <= N; n++) {
			Dijkstra[n] = new ArrayList<Node>();
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			int cost = stoi(st.nextToken());
			
			Dijkstra[from].add(new Node(to, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		
		From = stoi(st.nextToken());
		To = stoi(st.nextToken());
		
		dijkstra();
		
		System.out.println(Answer[To]);
	}
	
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(From, 0));
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int nowIdx = now.idx;
			int nowCost = now.cost;
			
			if (Visited[nowIdx]) continue;
			Visited[nowIdx] = true;
			
			for (Node next : Dijkstra[nowIdx]) {
				int nextIdx = next.idx;
				int nextCost = next.cost;
				
				if (Answer[nextIdx] > (nowCost + nextCost)) {
					Answer[nextIdx] = nowCost + nextCost;
					pq.add(new Node(nextIdx, Answer[nextIdx]));
 				}
			}
		}
	}
}