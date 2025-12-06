package org.example.day06;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.lang.Character;

class Input {
    List<String> input;
    public Input(BufferedReader reader) throws IOException{
        input = new ArrayList<String>();
        String line;
        while((line = reader.readLine()) != null) {
            input.add(line);
        }
    }

    public int get_max_line_length() {
        int ret = 0;
        for(String line : input) {
            ret = Math.max(line.length(), ret);
        }
        return ret;
    }

    public int get_problems() {
        int problems = 0;
        for(char c : input.getLast().toCharArray()) {
            if(c != ' ')
                problems += 1;
        }
        return problems;
    }

    public ArrayList<ArrayList<String>> split_problems() {
        int problems = get_problems();
        int [] problem_start_indices = new int[problems];
        char [] op_line = input.getLast().toCharArray();
        for(int i = 0, j = 0;i < op_line.length; ++i) {
            if(op_line[i] != ' ') {
                problem_start_indices[j] = i;
                j += 1;
            }
        }
        int [] problem_end_indices = new int[problems];
        for(int i = 0;i < problems - 1; ++i) {
            problem_end_indices[i] = problem_start_indices[i + 1] - 1;
        }
        problem_end_indices[problems - 1] = get_max_line_length();
        ArrayList<ArrayList<String>> nums = new ArrayList<ArrayList<String>>(problems);
        for(int i = 0;i < problems; ++i) {
            nums.add(new ArrayList<String>());
            for(String inp : input){
                int end = Math.min(inp.length(), problem_end_indices[i]);
                nums.get(i).add(inp.substring(problem_start_indices[i], end).replace(" ", "0"));
            }
        }

        return nums;
    }
}

public class Solution {

    public void run(BufferedReader reader) throws IOException {
        long answer = 0;
        Input input = new Input(reader);
        int max_line_length = input.get_max_line_length();
        int problems = input.get_problems();
        var nums = input.split_problems();
        for(int i = 0;i < problems; ++i) {
            ArrayList<String> p = nums.get(i);
            char op = p.getLast().charAt(0);
            int noperands = p.get(0).length();
            long acc = (op == '*') ? 1 : 0;
            for(int j = 0; j < noperands; ++j) {
                long num = 0L;
                for(int k = 0; k < p.size() - 1; ++k) {
                    long d = p.get(k).charAt(j) - '0';
                    if(d == 0)
                        continue;
                    num *= 10L;
                    num += d;
                }
                if(op == '*') {
                    acc *= num;
                } else acc += num;
            }
            answer += acc;
        }
        System.out.println(String.format("answer is : %d", answer));
    }

    public void run_part1(BufferedReader reader) throws IOException {
        long answer = 0;
        String line = reader.readLine();
        int problems = line.length();
        long[][] nums = new long[problems][2];
        for(int i = 0;i < problems; ++i) {
            nums[i][0] = 0;
            nums[i][1] = 1;
        }
        List<String> input = new ArrayList<String>();
        input.add(line);
        while((line = reader.readLine()) != null) {
            input.add(line);
        }
        for(int j = 0;j < input.size() - 1; ++j) {
            line = input.get(j);
            int i = 0;
            for (String str_num : line.split("\\s+")) {
                if(str_num == "")
                    continue;
                System.out.println(str_num);
                long n = Long.parseLong(str_num);
                nums[i][0] += n;
                nums[i][1] *= n;
                i += 1;
            }
        }
        int i = 0;
        for(String op : input.getLast().split("\\s+")) {
            System.out.print(op);
            if(op.equals("*")) {
                answer += nums[i][1];
                i += 1;
            } else if(op.equals("+")) {
                answer += nums[i][0];
                i += 1;
            }
        }
        System.out.println(String.format("answer is : %d", answer));
    }
}
