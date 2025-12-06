package org.example.day03;
import java.io.*;
import java.util.*;

public class Solution {

    public long [] construct_best(long [] previous, int digits, int[] joltages) {
        int n = previous.length;
        long [] next = new long [n];
        for(int i = n - digits;i > -1; --i) {
            next[i] = Math.max(next[i + 1], joltages[i] * (long) Math.pow(10, digits - 1) + previous[i + 1]);
        }
        return next;
    }

    public void run(BufferedReader reader) throws IOException {
        long answer = 0;
        String line;
        while((line = reader.readLine()) != null) {
            String [] j = line.split("");
            int n = j.length;
            int [] joltages = Arrays.stream(j).mapToInt(Integer::parseInt).toArray();
            long [] suff_max = new long[n];
            suff_max[n - 1] = joltages[n - 1];
            for(int i = n - 2; i > -1; --i) {
                suff_max[i] = Math.max(suff_max[i + 1], joltages[i]);
            }
            for(int i = 2;i <= 12; ++i) {
                suff_max = construct_best(suff_max, i, joltages);
            }
            long max = 0;
            for(long e : suff_max)
                max = Math.max(e, max);
            System.out.println(max);
            answer += max;
        }
        System.out.println(String.format("answer is : %d", answer));
    }
}
