package SWPro.D20211015;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 
 * 
 * @author tschuss
 * @since 2021-10-15
 * 
 * @category Algorithm
 * @category Samsung S/W Pro
 * @category Graph, PriorityQueue
 * 
 * 최소비용 구하기
 * 
 */


/*
Output
#1 2
#2 3
#3 0
 */

public class Main {
	static int T, N, K; // 테스트케이스, 편의시설, 비용
	static int INF = Integer.MAX_VALUE;	
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	private static class Data implements Comparable<Data> {
		int y, x, cost;
		
		Data (int y, int x, int cost) {
			this.y = y;
			this.x = x;
			this.cost = cost;
		}

		@Override
		public int compareTo(Data d) {
			return this.cost - d.cost;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("src/SWPro/D20211015/Input1.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		T = stoi(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			K = stoi(st.nextToken());
			
			int answer = INF;
			int[][] cost = new int[N][K];
			int min = INF;
			int max = 0;
			
			PriorityQueue<Data> pq = new PriorityQueue<Data>();
			
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < K; k++) {
					cost[n][k]= stoi(st.nextToken());
				}
				
				min = Math.min(cost[n][0], min);
				max = Math.max(cost[n][0], max);
				
				pq.add(new Data(n, 0, cost[n][0]));
				max = Math.max(max, cost[n][0]);
			}
			
			
			while(true) {
				Data minData = pq.poll();
				// 결과 값 갱신
				answer = Math.min(answer, max - minData.cost);
				
				// 종료 조건
				if (minData.x == K - 1) break;
				
				// PQ에 추가
				pq.add(new Data(minData.y, minData.x + 1, cost[minData.y][minData.x + 1]));
				
				// max 값 갱신				
				max = Math.max(max, cost[minData.y][minData.x + 1]);
			}
			
			bw.write(String.format("#%d %d\n", t, answer));
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
