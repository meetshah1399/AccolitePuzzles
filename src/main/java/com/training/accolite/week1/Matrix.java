package com.training.accolite.week1;

import java.util.Arrays;
import java.util.Scanner;

public class Matrix {

    private final Scanner sc;
    private int rows;
    private int cols;

    // Method to print a matrix
    public void print(int[][] A) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(A[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    // Method to determine if a cell will be alive in the next step, based on current state and number of
    // alive neighbors (rules of game of life)
    public int isAlive(int current, int aliveNeighbors) {
        switch (current) {
            case 0:
                return (aliveNeighbors == 3) ? 1 : 0;
            case 1:
                if (aliveNeighbors < 2) return 0;
                if (aliveNeighbors == 2 || aliveNeighbors == 3) return 1;
                if (aliveNeighbors >= 3) return 0;
        }
        return 0;
    }

    public int[][] solve(int[][] A) {
        int[][] solution = new int[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                solution[i][j] = isAlive(A[i][j], count(A, i, j));
            }
        }
        //print(solution);
        return solution;
    }

    // An easier to understand method for counting the number of alive neighbors
    int count(int[][] A, int row, int col) {
        int sum = 0;
        for (int i = Math.max(row - 1, 0); i < Math.min(row + 2, this.rows); i++) {
            for (int j = Math.max(col - 1, 0); j < Math.min(col + 2, this.cols); j++) {
                sum += A[i][j];
            }
        }
        return sum - A[row][col];
    }

    public Matrix() {
        this(new Scanner(System.in));
    }

    public Matrix(Scanner sc) {
        this.sc = sc;
    }

    public int[][] run() {
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = sc.nextInt();
            }
        }
        // Save the number of rows and columns as they will be used in the future
        this.rows = m;
        this.cols = n;
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
