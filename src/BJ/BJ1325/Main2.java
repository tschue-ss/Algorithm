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

public class Main2 {
	static int N, M;
	static int[] Memo, Answer;
	static boolean[][] Child;
	
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
			System.setIn(new FileInputStream(new File(StringUtils.getFilePath(Main2.class) + "/" + fileName)));
			BJ1325();
		}
	}
	public static void BJ1325() throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		Memo = new int[N + 1];
		Answer = new int[N + 1];
		Child = new boolean[N + 1][N + 1];
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int child = stoi(st.nextToken());
			int parent = stoi(st.nextToken());
			Memo[parent]++;
			Child[parent][child] = true;
		}
		
		int max = -1;
		for (int p = 1; p <= N; p++) {
			for (int c = 1; c <= N; c++) {
				if (Child[p][c]) {
					Answer[p] += Memo[c];
				}
			}
			
			if (max < Answer[p]) {
				sb = new StringBuffer();
				sb.append(p);
				max = Answer[p];
			} else if (max == Answer[p]) {
				sb.append(" " + p);
			}
		}
		
		System.out.println(sb.toString());
	}
}