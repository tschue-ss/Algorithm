package BJ.BJ11657;

import java.io.IOException;

/**
 * 
 * @author tschuss
 * @since 2021-10-14
 * 
 * @category Utility
 * 
 *           Gen sample input
 *
 */

public class GenTestCase11657 {
	public static void main(String[] args) throws IOException {
		genTestCase();
	}

	private static void genTestCase() {
		// Answer : -1
		int n = 500;
		int m = 6000;
		System.out.println(n + " " + m);
		for (int i = 0; i < m; ++i) {
			System.out.println(1 + i % 2 + " " + 1 + (i + 1) % 2 + " " + -10000);
		}
	}
}