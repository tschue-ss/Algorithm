package BJ.BJ1325;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer; 

/** 
 * 
 * @author tschuss
 * @since 2021-10-10
 * 
 * @category Algorithm
 * @category Graph
 * 
 * 효율적인 해킹
 * @url https://www.acmicpc.net/problem/1325  
 *
 */

/* Sample Answer
1 2
1 2 3 4
1 2 3
12
1 2 3 6
*/

public class Main {
	static int N, M;
	static ArrayList<Integer>[] Graph;
	static int[] Answer;
	static boolean[] visited;
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		Graph = new ArrayList[N + 1];
		Answer = new int[N + 1];
		
		for (int n = 1; n <= N; n++) {
			Graph[n] = new ArrayList<Integer>();
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			Graph[from].add(to);
		}
		
		// 1 ~ N까지 Loop를 돌면서 solving Logic을 수행
		for (int n = 1; n <= N; n++) {
			visited = new boolean[N + 1];
			visited[n] = true;
			solve(n);
		}
		
		// 산출 한 값중 최대 값을 max변수에 적재
		int max = -1;
		for (int n = 1; n <= N; n++) {
			max = Math.max(max, Answer[n]);
		}
		
		// max 값과 같은 값을 가진 Node번호를 오름차순으로 bw에 저장
		for (int n = 1; n <= N; n++) {
			if (max == Answer[n]) {
				bw.write(n + " ");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void solve(int idx) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(idx);
		while (!q.isEmpty()) {
			idx = q.poll();
			
			for (int nextIdx : Graph[idx]) {
				if (visited[nextIdx]) continue;
				visited[nextIdx] = true;
				Answer[nextIdx]++;
				q.add(nextIdx);
			}
		}
	}
}