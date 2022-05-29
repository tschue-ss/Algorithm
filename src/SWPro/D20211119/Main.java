package SWPro.D20211119;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int T, N, SN, Answer;
	private static int[] IdxTree;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("src/SWPRO/D20211119/sample.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			Answer = 0;
			IdxTree = new int[300001];
			
			
			
			
			System.out.println("#" + t + " " + Answer);
		}
	}

}
