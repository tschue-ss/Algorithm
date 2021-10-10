package BJ.BJ13549;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import BJ.Common.StringUtils; 

/** 
 * 
 * @author tschuss
 * @since 2021-10-10
 * 
 * @category Algorithm
 * @category Graph, DFS, Dijkstra
 * 
 * 숨바꼭질
 * @url https://www.acmicpc.net/problem/13549  
 *
 */

/* Sample Answer
2
1
5
6
1
*/

public class Main {
	static int N, K, Answer;
	
	private static class Node implements Comparable<Node> {
		int idx, cost;
		
		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node n) {
			if (this.cost != n.cost) return this.cost - n.cost;
			else return this.idx - n.idx;
		}
	}
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) throws IOException {
		String[] tc = {
				"input.txt"
				, "input1.txt"
				, "input2.txt"
				, "input3.txt"
				, "input4.txt"
		};
		
		for (String fileName : tc) {
			System.setIn(new FileInputStream(new File(StringUtils.getFilePath(Main.class) + "/" + fileName)));
			BJ13549();
		}
	}
	public static void BJ13549() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		solve();
		
		System.out.println(Answer);
	}

	private static void solve() {
		int[] visited = new int[200001];
		Arrays.fill(visited, Integer.MAX_VALUE);
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(N, 0));
		
		while (!q.isEmpty()) {
			Node n = q.poll();
			int nowIdx = n.idx;
			int nowCost = n.cost;
			
			if (nowIdx == K) {
				Answer = nowCost;
				return;
			}
				
			int go = nowIdx + 1;
			int back = nowIdx - 1;
			int jump = nowIdx * 2;
			
			
			if (go <= K && visited[go] > (nowCost + 1)) {
				visited[go] = nowCost + 1;
				q.add(new Node(go, nowCost + 1));
			}
			
			if (back >= 0 && visited[back] > (nowCost + 1)) {
				visited[back] = nowCost + 1;
				q.add(new Node(back, nowCost + 1));
			}
			
			if (jump < (K * 2) && visited[jump] > nowCost) {
				visited[jump] = nowCost;
				q.add(new Node(jump, nowCost));
			}
		}
	}
}