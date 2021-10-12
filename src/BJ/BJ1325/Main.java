package BJ.BJ1325;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import BJ.Common.StringUtils; 

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
	static int[] Memo, Answer;
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) throws IOException {
		String[] tc = {
				"Input.txt"
				, "Input1.txt"
				, "Input2.txt"
				, "Input3.txt"
				, "Input4.txt"
		};
		
		for (String fileName : tc) {
			System.setIn(new FileInputStream(new File(StringUtils.getFilePath(Main.class) + "/" + fileName)));
			BJ1325();
		}
	}
	public static void BJ1325() throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		Graph = new ArrayList[N + 1];
		Memo = new int[N + 1];
		Answer = new int[N + 1];
		
		for (int n = 1; n <= N; n++) {
			Graph[n] = new ArrayList<Integer>();
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			Graph[from].add(to);
			Memo[to]++;
		}
		
		// 1 ~ N까지 Loop를 돌면서 solving Logic을 수행
//		for (int n = 1; n <= N; n++) {
//			int temp = solve(n);
//			if (temp > max) {
//				sb = new StringBuffer();
//				sb.append(n);
//				max = temp;
//			} else if (temp == max) {
//				sb.append(" " + n);
//			}
//		}
		
		for (int n = 1; n <= N; n++) {
			if (Memo[n] > 0) {
				solve2(n);
			}
		}

		int max = -1;
		for (int n = 1; n <= N; n++) {
			if (max < Answer[n]) {
				sb = new StringBuffer();
				sb.append(n);
				max = Answer[n];
			} else if (max == Answer[n]) {
				sb.append(" " + n);
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private static void solve2(int idx) {
		Answer[idx] += Memo[idx];
		for (int n : Graph[idx]) {
			Answer[n] += Answer[idx];
		}
		Memo[idx] = 0;
	}

	private static int solve(int idx) {
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<Integer>();
		
		for (int n : Graph[idx]) {
			q.add(n); 
		}
		int answer = 0; 
		
		while (!q.isEmpty()) {
			int nowIdx = q.poll();
			visited[nowIdx] = true;
			answer++;
			for (int nextIdx : Graph[nowIdx]) {
				if (visited[nextIdx]) continue;
				q.add(nextIdx);
			}
		}
		return answer;
	}
}