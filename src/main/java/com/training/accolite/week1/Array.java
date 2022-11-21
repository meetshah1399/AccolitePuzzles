package com.training.accolite.week1;

import java.util.*;
import java.io.*;
import java.math.*;

public class Array {
    private final Scanner sc;


    public Array() {
        this(new Scanner(System.in));
    }

    public Array(Scanner sc) {
        this.sc = sc;
    }

    public static void main(String[] args) {
        new Array().run();
    }

    public int run() {
        int result = solve();
        System.out.println(result);
        return result;
    }


    int solve() {
        int n = sc.nextInt();
        int m = sc.nextInt();

        // Minor memory optimization (size of a)
        int[] a = new int[2 * n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            a[i + n] = a[i];
        }

        // Fixed the mistake with reading the input (as well as size of b)
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }

        // Since each "glyph" is unique, we can use a hash map to look up its index in O(1)
        // This index will be used to check if a substring of a, is a subsequence of b (indices should be
        // increasing, but we should allow for one wrap around (decreasing index))
        HashMap<Integer, Integer> bIndex = new HashMap<>();
        for (int i = 0; i < m; i++) {
            bIndex.put(b[i], i);
        }

        // Get substrings of a, in decreasing length
        for (int lenght = n; lenght > 0; lenght--) {
            // For every substring of length L, check each possible rotation (offset) of a
            for (int offset = 0; offset < n; offset++) {
                int[] substring = Arrays.copyOfRange(a, offset, offset + lenght);
                // Initialize assumptions
                boolean isSubsequence = true;
                boolean wrappedAround = false;
                int indexOfLastGlyph = -1;
                int indexOfFirstGlyph = -1;
                // Check if every element in current substring of a, exists in b
                for (int glyph : substring) {
                    if (bIndex.containsKey(glyph)) {
                        // Check if the order of glyphs in the current substring of a, is increasing in b
                        // (which is the condition for a substring of a, to be subsequence of b)
                        if (bIndex.get(glyph) > indexOfLastGlyph) {
                            indexOfLastGlyph = bIndex.get(glyph);
                            // If this is the first glyph that we find, keep its index for wraparound checking
                            if (indexOfFirstGlyph == -1) indexOfFirstGlyph = bIndex.get(glyph);
                            // if we have already wrapped around, be careful of going out of bounds (to do this,
                            // compare the index of current glyph and the index of the first glyph which was found)
                            if (wrappedAround && bIndex.get(glyph) > indexOfFirstGlyph) {
                                isSubsequence = false;
                                break;
                            }
                        } else {
                            // If the order was not increasing, allow for one wraparound, but be careful of
                            // wrapping around more than once (to do this, compare the index of current glyph and
                            // the index of the first glyph which was found)
                            if (!wrappedAround && bIndex.get(glyph) < indexOfFirstGlyph) {
                                wrappedAround = true;
                            } else {
                                // If we have already wrapped, the current substring is not a subsequence, so
                                // stop checking
                                isSubsequence = false;
                                break;
                            }
                        }
                    } else {
                        // If a glyph in the current substring does not exist in b, there is no subsequence
                        // that contains it, so break early and stop checking other glyphs
                        isSubsequence = false;
                        break;
                    }
                }
                // If a subsequence was found, it has to be of maximum of length (since we are checking
                // substrings in decreasing length), so return its length
                if (isSubsequence) return lenght;
            }
        }
        // If no subsequence was found, the inputs share no common glyphs, so return 0
        return 0;
    }

}