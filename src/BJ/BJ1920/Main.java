package BJ.BJ1920;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer; 

/** 
 * 
 * @author tschuss
 * @since 2021-10-14
 * 
 * @category Algorithm
 * @category BinarySearch
 * 
 * 수 찾기
 * @url https://www.acmicpc.net/problem/1920
 *
 */

/* Sample Answer
1
1
0
0
1
*/

public class Main {
	static int N, M;
	static int[] NumberList;
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("src/BJ/BJ1920/Input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		NumberList = new int[N];
		
		st = new StringTokenizer(br.readLine()); 
		for (int n = 0; n < N; n++) {
			NumberList[n] = stoi(st.nextToken());
		}

		Arrays.sort(NumberList);
		
		M = stoi(br.readLine());
		st = new StringTokenizer(br.readLine()); 
		for (int m = 0; m < M; m++) {
//			System.out.println(binarySearch(0, N - 1, stoi(st.nextToken())));
			bw.write(binarySearch(0, N - 1, stoi(st.nextToken())) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	private static int binarySearch(int start, int end, int target) {
		int mid;
		
		if (start <= end) {
			mid = (start + end) / 2;
			if (NumberList[mid] == target) {
				return 1;
			} else if (target < NumberList[mid]) {
				return binarySearch(start, mid - 1, target);
			} else {
				return binarySearch(mid + 1, end, target);
			}
			
		}
		return 0;
	}
}