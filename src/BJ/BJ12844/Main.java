package BJ.BJ12844;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import BJ.Common.StringUtils; 

/** 
 * 
 * @author tschuss
 * @since 2021-11-19
 * 
 * @category Algorithm
 * @category SegmentTree, Lazy Propagation
 * 
 * @url https://www.acmicpc.net/problem/12844  
 *
 */

/* Sample Answer
1
8
*/

public class Main {
	static int N, M;
	static int[] STree, Lazy; // SegmentTree, LazyPropagation	
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public static void main(String[] args) throws IOException {
		// init
		System.setIn(new FileInputStream(new File(StringUtils.getFilePath(Main.class) + "/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// input
		N = stoi(br.readLine());
//		STree = 
//		st = new StringTokenizer(br.readLine());
		
		
		M = stoi(br.readLine());
		st = new StringTokenizer(br.readLine());
	}
}