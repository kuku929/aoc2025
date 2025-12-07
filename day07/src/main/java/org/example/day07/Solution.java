package org.example.day07;
import java.io.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


record Point(int r, int c) {}

class Input {
    private final char [][] grid;
    private final int nc;
    private final int nr;
    public Input(BufferedReader reader) {
        List<String> input = reader.lines().toList();
        nr = input.size();
        nc = input.get(0).length();
        grid = new char[nr][nc];
        for(int r = 0; r < nr; ++r) {
            grid[r] = input.get(r).toCharArray();
        }
    }

    public Point get_start() {
        for(int r = 0; r < nr; ++r) {
            for(int c = 0; c < nc; ++c) {
                if(grid[r][c] == 'S') {
                    return new Point(r, c);
                }
            }
        }
        return new Point(0, 0);
    }

    public List<Integer> get_splitter_cols(int r) {
        List<Integer> ret = new ArrayList<Integer>();
        for(int c = 0; c < nc; ++c) {
            if(grid[r][c] == '^') {ret.add(c);}
        }
        return ret;
    }

    public int rows() {return nr;}
    public int cols() {return nc;}
}

public class Solution {
    public void run(BufferedReader reader) throws IOException {
        long answer = 0;
        Input input = new Input(reader);
        Set<Integer> beams = new TreeSet<Integer>();
        Point start = input.get_start();
        beams.add(start.c());
        int nr = input.rows();
        int nc = input.cols();
        long [] ways = new long[nc];
        for(int i = 0; i < nc; ++i) ways[i] = 0L;
        ways[start.c()] = 1L;
        for(int r = start.r() + 1; r < nr; ++r) {
            long [] nways = Arrays.copyOf(ways, ways.length);
            var cols = input.get_splitter_cols(r);
            for(int c : cols) {
                nways[c] -= ways[c];
                if(beams.remove(c)) {
                    beams.add(c + 1);
                    beams.add(c - 1);
                    nways[c + 1] += ways[c];
                    nways[c - 1] += ways[c];
                }
            }
            for(int i = 0;i < nc; ++i) ways[i] = nways[i];
        }
        for(int i = 0;i < nc; ++i) {answer += ways[i];}
        System.out.println(String.format("answer is : %d", answer));
    }
}
