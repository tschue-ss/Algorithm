package BJ.BJ1325;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
	static int[] Answer;
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) throws IOException {
		String fileName = "Input.txt";
//		String fileName = "Input1.txt";
//		String fileName = "Input2.txt";
//		String fileName = "Input3.txt";
//		String fileName = "Input4.txt";
//		String fileName = "Input9.txt";
		
		System.setIn(new FileInputStream(new File(StringUtils.getFilePath(Main.class) + "/" + fileName)));
		
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
			Graph[to].add(from);
		}
		
		int max = -1;
		
		// 1 ~ N까지 Loop를 돌면서 solving Logic을 수행
		for (int n = 1; n <= N; n++) {
			solve(n);
			max = Math.max(Answer[n], max);
		}
		
		for (int n = 1; n <= N; n++) {
			if (max == Answer[n]) {
				bw.write(n + " ");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	private static void solve(int idx) {
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<Integer>();
		
		for (int n : Graph[idx]) {
			q.add(n); 
		}
		
		visited[idx] = true;
		Answer[idx]++;
		
		while (!q.isEmpty()) {
			int nowIdx = q.poll();
			visited[nowIdx] = true;
			Answer[idx]++;
			for (int nextIdx : Graph[nowIdx]) {
				if (visited[nextIdx]) continue;
				q.add(nextIdx);
			}
		}
	}
}