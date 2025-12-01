package org.example.day01;
import java.io.*;

public class Solution {
    private int head;

    public Solution() {
        head = 50;
    }

    public int rotate(String dir) {
        int shift = Integer.parseInt(dir.substring(1));
        int total = 0;
        if(dir.charAt(0) == 'L') {
            int first_rot = head == 0 ? 100 : head;
            total = (shift - first_rot + 100) / 100;
            head -= shift;
        } else if(dir.charAt(0) == 'R') {
            int first_rot = head == 0 ? 100 : 100 - head;
            total = (shift - first_rot + 100) / 100;
            head += shift;
        } else {
            System.out.println("Invalid format: expected [LR][0-99]\n");
        }
        head %= 100;
        head += 100;
        head %= 100;
        return total;
    }

    public void run(BufferedReader reader) throws IOException {
        int answer = 0;
        String line;
        while((line = reader.readLine()) != null) {
            answer += this.rotate(line);
        }
        System.out.println(String.format("answer is : %d", answer));
    }
    
    public int head() {
        return head;
    }
}
