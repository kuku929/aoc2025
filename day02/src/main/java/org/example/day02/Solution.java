package org.example.day02;
import java.io.*;

public class Solution {

    private long get_invalid_id(long l, long r) {
        long invalid = 0;
        for(long i = l; i <= r; ++i) {
            int ndigits = (int) (Math.log10(i) + 1);
            for(int d = 1; d <= ndigits / 2; ++d) {
                long mult = (long) Math.pow(10, d);
                long key = i % mult;
                long repeated = 0;
                if(ndigits % d == 0) {
                    for(int j = 0; j < ndigits / d; ++j) {
                        repeated *= mult;
                        repeated += key;
                    }
                }
                if(repeated == i) {
                    invalid += i;
                    break;
                }
            }
        }
        return invalid;
    }

    public void run(BufferedReader reader) throws IOException {
        long answer = 0;
        String line = reader.readLine();
        String[] ranges = line.split(",");
        for(String range : ranges) {
            String[] l_r = range.split("-");
            long l = Long.parseLong(l_r[0]);
            long r = Long.parseLong(l_r[1]);
            answer += get_invalid_id(l, r);
        }
        System.out.println(String.format("the answer is : %d\n", answer));
    }
}
