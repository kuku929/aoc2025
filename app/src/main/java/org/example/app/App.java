package org.example.app;

import org.example.day04.Solution;
import java.io.*;

public class App {
    public static void main(String[] args) {
        InputStream in = App.class.getResourceAsStream("/input.txt");
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            Solution s = new Solution();
            s.run(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
