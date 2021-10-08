package BJ.BJ1504;

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
 * @since 2021-10-07
 * 
 * @category Algorithm
 * @category Dijkstra's
 * 
 * @url https://www.acmicpc.net/problem/1504  
 *
 */

/* Sample Answer
Input.txt
7

Input1.txt
1597000
*/

public class Main {
	static int N, E, V1, V2;
	static ArrayList<Node>[] Dijkstra;
	
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
//		System.setIn(new FileInputStream(new File(StringUtils.getFilePath(Main.class) + "/input.txt")));
		System.setIn(new FileInputStream(new File(StringUtils.getFilePath(Main.class) + "/input1.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		E = stoi(st.nextToken());
		
		Dijkstra = new ArrayList[N + 1];
		
		for (int n = 0; n <= N; n++) {
			Dijkstra[n] = new ArrayList<>();
		}
		
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			int cost = stoi(st.nextToken());
			
			Dijkstra[from].add(new Node(to, cost));
			Dijkstra[to].add(new Node(from, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		
		V1 = stoi(st.nextToken());
		V2 = stoi(st.nextToken());
		
		System.out.println(solveDijkstra());
	}

	private static int solveDijkstra() {
		// Case01
		int answerA = 0;
		answerA = dijkstra(1, V1, answerA);
		answerA = dijkstra(V1, V2, answerA);
		answerA = dijkstra(V2, N, answerA);
		
		// Case02
		int answerB = 0;
		answerB = dijkstra(1, V2, answerB);
		answerB = dijkstra(V2, V1, answerB);
		answerB = dijkstra(V1, N,answerB);
		
		if (answerA < 0) return answerB;
		if (answerB < 0) return answerA;
		
		return Math.min(answerA, answerB);
	}

	private static int dijkstra(int start, int end, int answer) {
		if (answer == -1) return answer;
		boolean[] visited = new boolean[N + 1];
		int[] cost = new int[N + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int nowIdx = now.idx;
			int nowCost = now.cost;
			
			if (visited[nowIdx]) continue;
			visited[nowIdx] = true;
			
			if (nowIdx == end) {
				return answer + nowCost;
			}
			
			
			for (Node next : Dijkstra[nowIdx]) {
				int nextIdx = next.idx;
				int nextCost = next.cost;
				
				if (cost[nextIdx] < nowCost + nextCost) continue;
				cost[nextIdx] = nowCost + nextCost;
				pq.add(new Node(nextIdx, cost[nextIdx]));
			}
		}
		
		return -1;
	}
}