package BJ.BJ2042;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer; 

/** 
 * 
 * @author tschuss
 * @since 2021-09-17
 * @update 2021-11-19
 * 
 * @category Algorithm
 * @category Index Tree
 * 
 * @url https://www.acmicpc.net/problem/2042
 *
 */

public class Main_IndexTree {
	private static int N, M, K, SN;
	private static long[] IdxTree;
	private static int[] Value;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("src/BJ/BJ2042/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		SN = 1;
		while (SN < N) {
			SN *= 2;
		}
		
		IdxTree = new long[SN * 2];
		
		// Init
		for (int n = 0; n < N; n++) {
			IdxTree[SN + n] = Integer.parseInt(br.readLine());
			
			int node = SN + n;
			while (node > 1) {
				node /= 2;
				IdxTree[node] = IdxTree[node * 2] + IdxTree[node * 2 + 1];
			}
		}

		for (int q = 0; q < M + K; q++) {
			st = new StringTokenizer(br.readLine());
			
			int type = Integer.parseInt(st.nextToken());
			switch (type) {
			case 1:
				int node = Integer.parseInt(st.nextToken());
				long value = Long.parseLong(st.nextToken());
				update(SN + node - 1, value);
				break;
			case 2:
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				long answer = query(1, 1, SN, start, end);
				System.out.println(answer);
				break;
			}
		}
	}

	private static long query(int node, int l, int r, int s, int e) {
		if (e < l || r < s) {
			return 0;
		}
		
		if (s <= l && r <= e) {
			return IdxTree[node];
		}
		
		int mid = (l + r) / 2;
		
		long left = query(node * 2, l, mid, s, e);
		long right = query(node * 2 + 1, mid + 1, r, s, e);
		
		return left + right;
	}

	private static void update(int node, long value) {
		IdxTree[node] = value;
		
		while (node > 1) {
			node /= 2;
			IdxTree[node] = IdxTree[node * 2] + IdxTree[node * 2 + 1];
		}
	}
}