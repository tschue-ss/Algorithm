package BJ.BJ1325;

import java.io.IOException; 

/** 
 * 
 * @author tschuss
 * @since 2021-10-10
 * 
 * @category Utility
 * 
 * Gen sample input
 *
 */

public class GenTestCase1325 {
	public static void main(String[] args) throws IOException {
//		genTestCase();
		genAnswer();
	}

	private static void genTestCase() {
		int n = 10000, m = 100000;
		System.out.println(String.format("%d %d", n, m));
	    for (int i = 0; i < n; i++)
	    	System.out.println(String.format("%d %d", i + 1, (i + 1) % n + 1));
	    m -= n;
	    for (int i = n - 1; m > 0; i--)
	        for (int j = n; j > i && m > 0; j--, m--)
	        	System.out.println(String.format("%d %d", i, j));
	}

	private static void genAnswer() {
		int n = 10000;
	    for (int i = 1; i <= n; i++)
	    	System.out.print(String.format("%d%c", i, i == n ? '\n' : ' '));
		
	}
}


