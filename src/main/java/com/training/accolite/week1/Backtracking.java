package com.training.accolite.week1;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;

import com.training.accolite.AccoliteApplication;
 
public class Backtracking {
	boolean[] ones;
	private boolean[][] s;
	private int[] c;
	private final Scanner sc;
	
	public Backtracking() {
		this(new Scanner(System.in));
	}

	public Backtracking(Scanner sc) {
		this.sc  = sc;
	}
	
	int solve() {
		int n = sc.nextInt();
		int m = sc.nextInt();
		s = new boolean[m][n];
		c = new int[m];
		for (int i = 0; i < m; i++) {
			String str = sc.next();
			for (int j = 0; j < n; j++)
				s[i][j] = str.charAt(j) == '1';
			c[i] = sc.nextInt();
		}
		sc.close();
		ones = new boolean[n];
		return go(0, c, s, ones);
	}

	/* can make a tree that has every possible iteration of the code:
	* 				root
	* 			0			1
	* 		0		1	0		1
	* and so on....  */

	private int go(int step, int[] errors, boolean[][] attempts, boolean[] test) {
		int n = 0;	// number of remaining valid codes

		// iterate until step is length of code to be tested
		if (step < test.length){
			// left branch (0)
			test[step] = false;
			n += go(step+1, errors, attempts, test);
			// right branch (1)
			test[step] = true;
			n += go(step+1, errors, attempts, test);
		}
		// execute when we have reached one of the possible permutations of the code
		else{
			// check how many values the code has in common with each guess
			for (int i = 0; i < errors.length; i++) {
				int common = 0;
				for (int j = 0; j < test.length; j++) {
					if(test[j] == attempts[i][j]){
						common++;
					}
				}
				/* if the code does not have exactly the number of correct values as the guess
				* had, then it does not hold up to the conditions to be a possible guess.
				* therefore return 0 */
				if (common != errors[i]){ return 0; }
			}
			/* if the code combo adheres to all the conditions of previous guesses, then tally
			* it as a possible solution */
			return 1;
		}
		// return total tally
		return n;
	}
 
	public int run() {
		int result = solve();
		System.out.println(result);
		return result;
	}
	
	public static void main(String[] args) {
		new Backtracking().run();
	}	
}
