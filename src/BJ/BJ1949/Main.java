package BJ.BJ1949;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import BJ.Common.StringUtils; 

/** 
 * 
 * @author tschuss
 * @since 2021-10-09
 * 
 * @category Algorithm
 * @category DP
 * 
 * @url https://www.acmicpc.net/problem/1949  
 *
 */

/* Sample Answer
Input.txt
14000
*/

public class Main {
	static private int N;
	static private int[] Count;
	static private int[][] DP;
	static private ArrayList<Integer>[] Village;
	static boolean[] Visited;
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File(StringUtils.getFilePath(Main.class) + "/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		
		Count = new int[N + 1];
		Village = new ArrayList[N + 1];
		DP = new int[2][N + 1];
		Visited = new boolean[N + 1];

		st = new StringTokenizer(br.readLine());
		
		Village[0] = new ArrayList<Integer>();
		for (int n = 1; n <= N; n++) {
			Count[n] = stoi(st.nextToken());
			Village[n] = new ArrayList<Integer>();
		}
		
		for (int n = 0; n < N - 1; n++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			Village[from].add(to);
			Village[to].add(from);
		}
		
		int start = 1;
		Visited[start] = true;
		solve(start);
		
		System.out.println(Math.max(DP[0][1], DP[1][1]));
	}
	
	private static void solve(int idx) {
		DP[1][idx] = Count[idx];
		
		for (int nextIdx : Village[idx]) {
			if (Visited[nextIdx]) continue;
			Visited[nextIdx] = true;		
			
			solve(nextIdx);
			
			DP[0][idx] += Math.max(DP[0][nextIdx], DP[1][nextIdx]);			
			DP[1][idx] += DP[0][nextIdx];
			
		}
	}
}