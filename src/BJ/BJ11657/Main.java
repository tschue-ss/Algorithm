package BJ.BJ11657;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer; 

/** 
 * 
 * @author tschuss
 * @since 2021-10-15
 * 
 * @category Algorithm
 * @category Bellman-Ford
 * 
 * 타임머신
 * @url https://www.acmicpc.net/problem/11657
 *
 */

/* Sample Answer
Input1.txt
4
3

Input2.txt
-1

Input3.txt
-1

Input4.txt
3
-1
*/

public class Main {
	static int N, M, INF = (int) 21e8;
	static ArrayList<ArrayList<Node>> Adj;
	static long[] Answer;
	
	
	private static class Node {
		int idx;
		long cost;
		
		Node(int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream(new File("src/BJ/BJ11657/Input1.txt")));
//		System.setIn(new FileInputStream(new File("src/BJ/BJ11657/Input2.txt")));
//		System.setIn(new FileInputStream(new File("src/BJ/BJ11657/Input3.txt")));
//		System.setIn(new FileInputStream(new File("src/BJ/BJ11657/Input4.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		Answer = new long[N + 1];
		Adj = new ArrayList<>();
		
		Arrays.fill(Answer, INF);
		
		for (int n = 0; n <= N; n++) {
			Adj.add(new ArrayList<Node>());
		}
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			int cost = stoi(st.nextToken());
			Adj.get(from).add(new Node(to, cost));
		}
		
		if (bellmanFord()) {
			System.out.println(-1);
//			bw.write("-1\n");
		} else {
			for (int i = 2; i <= N; i++) {
				if (Answer[i] == INF) {
//					bw.write("-1\n");
					System.out.println(-1);
				} else {
//					bw.write(Answer[i] + "\n");
					System.out.println(Answer[i]);
	            }
            }
        }
		
//		bw.flush();
//		bw.close();
//		br.close();
	}

	private static boolean bellmanFord() {
		Answer[1] = 0;
		boolean updateFlag = false;
		
		for (int i = 1; i < N; i++) {
			updateFlag = false;
			
			for (int n = 1; n <= N; n++) {
				for (Node next : Adj.get(n)) {
					if (Answer[n] == INF) break;
					
					if (Answer[next.idx] > Answer[n] + next.cost) {
						Answer[next.idx] = Answer[n] + next.cost; 
						updateFlag = true;
					}
				}
				
				// Cycle이 존재하지 않음
				if (!updateFlag) break;
			}
			
			// 음수의 Cycle이 존재
			if (updateFlag) {
				for (int n = 1; n <= N; n++) {
					for (Node next : Adj.get(n)) {
						if (Answer[n] == INF) break;
						
						if (Answer[next.idx] > Answer[n] + next.cost) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
}