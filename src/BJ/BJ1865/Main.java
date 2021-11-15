package BJ.BJ1865;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer; 

/** 
 * 
 * @author tschuss
 * @since 2021-11-15
 * 
 * @category Algorithm
 * @category Bellman-Ford
 * 
 * 웜홀
 * @url https://www.acmicpc.net/problem/1865
 *
 */

/* Sample Answer
NO
YES
*/

public class Main {
	static int T, N, M, W, INF = (int) 21e8;
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
		System.setIn(new FileInputStream(new File("src/BJ/BJ1865/Input1.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = stoi(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = stoi(st.nextToken());		// 지점 (노드)
			M = stoi(st.nextToken());		// 도로 (간선)
			W = stoi(st.nextToken());		// 웜홀 (Minus 간선)
			
			Answer = new long[N + 1];
			Arrays.fill(Answer, INF);
			
			Adj = new ArrayList<>(); 	// 인접 리스트
			for (int n = 0; n <= N; n++) {
				Adj.add(new ArrayList<Node>());	// 인접리스트 초기화
			}
			
			for (int m = 0; m <= M; m++) {
				st = new StringTokenizer(br.readLine());
				int s = stoi(st.nextToken()); // 노드_1
				int e = stoi(st.nextToken()); // 노드_2
				int c = stoi(st.nextToken()); // cost

				// 양방향 노드 연결
				Adj.get(s).add(new Node(e, c));
				Adj.get(e).add(new Node(s, c));
			}
			
			for (int w = 0; w <= W; w++) {
				st = new StringTokenizer(br.readLine());
				int s = stoi(st.nextToken()); // 노드_1
				int e = stoi(st.nextToken()); // 노드_2
				int c = stoi(st.nextToken()); // cost
				
				// 단방향 노드 연결
				Adj.get(s).add(new Node(e, c));
			}
			
			if (bellmanFord()) {
				System.out.println("YES");
			} else {
				for (int i = 2; i <= N; i++) {
					if (Answer[i] == INF) {
						System.out.println("NO");
					} else {
						System.out.println("YES");
		            }
	            }
	        }
		}
		br.close();
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