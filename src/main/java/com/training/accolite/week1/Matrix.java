package com.training.accolite.week1;

import java.util.Arrays;
import java.util.Scanner;

public class Matrix {
	
	private final Scanner sc;

	public int[][] solve(int[][] A) {
		// Write logic here...
	    int m = A.length;	// num rows
	    int n = A[0].length;	// num cols
		int[][] ans = new int[m][n];	// create new matrix initiated to all 0s

		// iterate through matrix
		for (int i = 0; i < m; i++){
			for (int j = 0; j < n; j++){
				int neighbours = count(A, i, j, m, n);	// number of alive neighbours

				// only need to check conditions where next state cell is alive (=1)
				if (neighbours == 3){
					ans[i][j] = 1;
				}
				else if (neighbours == 2){
					if (A[i][j] == 1){
						ans[i][j] = 1;
					}
				}
			}
		}

        return ans;
	}

	int dis[][]={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

	int count(int[][] A,int i,int j,int m,int n){
	    int cnt=0;
	    for(int k=0;k<8;k++){
	        int x= i+dis[k][0], y= j+dis[k][1];
	        if(x>=0 && y>=0 && x<m && y<n && Math.abs(A[x][y])==1) cnt++;
	    }
	    return cnt;
	}
	
	public Matrix() {
		this(new Scanner(System.in));
	}

	public Matrix(Scanner sc) {
		this.sc  = sc;
	}
	
	public int[][] run() {
		int m = sc.nextInt();
		int n = sc.nextInt();
		int[][]result= new int[m][n];
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				result[i][j]=sc.nextInt();
			}
		}
		result = solve(result);
		for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }
		return result;
	}
	
	public static void main(String[] args) {
		new Matrix().run();
	}
	
	
}
