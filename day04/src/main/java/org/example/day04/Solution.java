package org.example.day04;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

record Point(int i, int j) {}

class Input {
    final int nr;
    final int nc;
    final List<char []> grid;
    final static int[] dx = {-1, 1, 0, 0};
    final static int[] dy = {0, 0, 1, -1};
    final static int[] dxy = {1, -1};

    public Input(BufferedReader reader) throws IOException {
        List<String> input = reader.lines().toList();
        nr = input.size();
        nc = input.get(0).length();

        grid = new ArrayList<char []>();
        for(String l : input) {
            grid.add(l.toCharArray());
        }
    }

    public boolean ok(int nr, int nc, int i, int j) {
        return (i > -1 && j > -1 && i < nr && j < nc);
    }

    public List<Point> get_initial_accessible() {
        List<Point> ret = new ArrayList<Point>();
        for(int i = 0; i < nr; ++i) {
            for(int j = 0;j < nc; ++j) {
                int cnt = this.count_neighbors(i, j);
                if(cnt < 4) {
                    ret.add(new Point(i, j));
                }
            }
        }
        return ret;
    }

    public void remove(Point p) {
        grid.get(p.i())[p.j()] = '.';
    }

    public int count_neighbors(Point p) {
        return this.count_neighbors(p.i(), p.j());
    }

    public List<Point> get_neighbor_list(Point p) {
        return this.get_neighbor_list(p.i(), p.j());
    }

    public List<Point> get_neighbor_list(int i, int j) {
        List<Point> ret = new ArrayList<Point>();
        for(int k = 0; k < 4; ++k) {
            int ni = i + dx[k];
            int nj = j + dy[k];
            if(ok(nr, nc, ni, nj) && grid.get(ni)[nj] == '@') {
                ret.add(new Point(ni, nj));
            }
        }
        for(int k = 0; k < 2; ++k) {
            for(int l = 0; l < 2; ++l) {
                int ni = i + dxy[k];
                int nj = j + dxy[l];
                if(ok(nr, nc, ni, nj) && grid.get(ni)[nj] == '@') {
                    ret.add(new Point(ni, nj));
                }
            }
        }
        return ret;
    }

    public int count_neighbors(int i, int j) {
        int cnt = 0;
        if(grid.get(i)[j] == '.')
            return 8;
        return this.get_neighbor_list(i, j).size();
    }

}

public class Solution {
    public void run(BufferedReader reader) throws IOException {
        int answer = 0;
        Input input = new Input(reader);
        Queue<Point> q = new LinkedList<Point>();

        for(Point p : input.get_initial_accessible()) {
            boolean success = q.offer(p);
            if(! success) {
                return;
            }
            input.remove(p);
        }

        while(q.size() != 0) {
            Point curr = q.poll();
            if(curr == null)
                throw new RuntimeException("head of queue invalid!");
            var neighs = input.get_neighbor_list(curr);
            for(Point neigh : neighs) {
                if(input.count_neighbors(neigh) < 4) {
                    boolean success = q.offer(neigh);
                    if(! success) {
                        return;
                    }
                    input.remove(neigh);
                }
            }
            answer += 1;
        }

        System.out.println(String.format("answer is : %d", answer));
    }

    public void run_part1(BufferedReader reader) throws IOException {
        int answer = 0;
        Input input = new Input(reader);
        answer = input.get_initial_accessible().size();
        System.out.println(String.format("answer is : %d", answer));
    }
}
