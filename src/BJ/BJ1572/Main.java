package BJ.BJ1572;

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
 * 
 * @url https://www.acmicpc.net/problem/1572
 *
 */

public class Main {
	static int N, K, SN, LIMIT = 16 * 2;
	static long Answer = 0;
	static int[] STree, Value;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("src/BJ/BJ1572/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		SN = 1;
		while (SN < N) {
			SN *= 2;
		}
		
		Value = new int[N + 1];
		STree = new int[SN * 2];
		
		int idx = 0;
		for (int n = 1; n <= N; n++) {
			idx++;
			Value[n] = Integer.parseInt(br.readLine());
			update(1, 1, LIMIT, Value[n] + SN + n, 1);
			if (idx == K) {
				Answer += query(1, 1, LIMIT - 1, 0);
				update(1, 1, LIMIT, Value[n] - K + 1, -1);
				idx = 0;
			}
		}
		
		System.out.println(Answer);
	}

	private static long query(int node, int l, int r, int idx) {
		if (l <= 0 || LIMIT <= r) return 0;
		if (STree[node] == idx) return STree[node];
		
		int mid = (l + r) / 2;
		if (STree[node] > idx) {
			query(node * 2, l, mid, idx);
		} else {
			query(node * 2 + 1, mid, r, idx - STree[node * 2]);
		}
		return node - 1;
	}

	private static void update(int node, int l, int r, int target, int val) {
		if (node <= 0 || node >= LIMIT) return;
		
		int mid = (l + r) / 2;
		
		if (target == l && target == r) {
			STree[node] += val;
			return;
		} else if (node >= l) {
			STree[node] += val;
			update(node * 2, l, mid, target, val);
		} else if (node < r) {
			STree[node] += val;
			update(node * 2 + 1, mid + 1, r, target, val);
		}
	}
}