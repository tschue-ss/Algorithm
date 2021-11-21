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
 * 
 * @category Algorithm
 * @category Segment Tree
 * @category Lazy Propagation
 * 
 * @url https://www.acmicpc.net/problem/2042
 *
 */

public class Main_SegmentTree {
	static BufferedReader br = null;
	static StringTokenizer st = null;
	static int N, M, K, TreeSize, Answer;
	static long[] Segment, Lazy;
	static int[][] ChangeQuery, SumQuery;
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	private static long stol(String str) {
		return Long.parseLong(str);
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("src/BJ/BJ2042/input.txt")));
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		TreeSize = (int) (Math.pow(2.0, Math.floor((Math.log((double)N) / Math.log(2.0)) + 1)));
		
		Segment = new long[TreeSize * 2];
		Lazy = new long[TreeSize * 2];
		
		for (int n = 1; n <= N; n++) {
			long val = stol(br.readLine());
			insert(n, val);
		}
		
		for (int q = 0; q < (M + K); q++) {
			st = new StringTokenizer(br.readLine());
			
			int type = stoi(st.nextToken());
			
			switch (type) {
			case 1:
				int idx = stoi(st.nextToken());
				long val = stol(st.nextToken());
				update(1, idx, 1, TreeSize, val);
				break;
			case 2:
				int from = stoi(st.nextToken());
				int to = stoi(st.nextToken());
				System.out.println(sumQuery(1, from, to, 1, TreeSize));
				break;
			}
		}
	}
	
	private static void insert(int idx, long value) {
		idx = TreeSize - 1 + idx;
		Segment[idx] = value;
		
		while (idx > 0) {
			idx /= 2;
			Segment[idx] = Segment[idx * 2] + Segment[idx * 2 + 1]; 
		}
	}

	private static void update(int node, int idx, int l, int r, long value) {
		lazyUpdate(node, l, r);
		
		if (idx > r || idx < l) return;
		
		if (idx <= l && idx >= r) {
			Segment[node] = (r - l + 1) * value;
			
			if (l != r) {
				Lazy[node * 2] += value;
				Lazy[node * 2 + 1] += value;
			}
			return;
		}
		
		int mid = (l + r) / 2;		
		update(node * 2, idx, l, mid, value);
		update(node * 2 + 1, idx, mid + 1, r, value);
		Segment[node] = Segment[node * 2] + Segment[node * 2 + 1]; 
	}
	
	private static void lazyUpdate(int node, int l, int r) {
		if (Lazy[node] != 0) {
			Segment[node] += (r - l + 1) * Lazy[node];
			
			if (l != r) {
				Lazy[node * 2] += Lazy[node];
				Lazy[node * 2 + 1] += Lazy[node];
			}
			Lazy[node] = 0;
		}
	}
	
	private static long sumQuery(int node, int s, int e, int l, int r) {
		lazyUpdate(node, l, r);
		
		long result = 0;
		if (s > r || e < l) return result;
		if (s <= l && e >= r) return Segment[node];
		
		int mid = (l + r) / 2;
		result += sumQuery(node * 2, s, e, l, mid);
		result += sumQuery(node * 2 + 1, s, e, mid + 1, r);
		
		return result; 
	}
}