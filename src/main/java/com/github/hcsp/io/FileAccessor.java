package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(file.toPath());
        List<String> ans = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        for (byte aByte : bytes) {
            if (aByte != "\n".getBytes()[0]) {
                str.append((char) aByte);
            } else {
                ans.add(str.toString());
                str.delete(0, str.length());
            }
        }
        return ans;
    }

    public static List<String> readFile2(File file) throws IOException {
        List<String> ans = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        while ((s = br.readLine()) != null) {
            ans.add(s);
        }
        br.close();
        return ans;
    }

    public static List<String> readFile3(File file) throws IOException {
        String[] s = FileUtils.readFileToString(file, Charset.defaultCharset()).split("\n");
        return new ArrayList<>(Arrays.asList(s));
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        FileUtils.writeLines(file, lines);
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (String str :
                lines) {
            bw.write(str);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        Files.write(file.toPath(), lines);
    }

    public static void main(String[] args) throws IOException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        File testFile = new File(projectDir, "target/test.txt");
        List<String> lines = Arrays.asList("AAA", "BBB", "CCC");
        writeLinesToFile1(lines, testFile);
        writeLinesToFile2(lines, testFile);
        writeLinesToFile3(lines, testFile);

        System.out.println(readFile1(testFile));
        System.out.println(readFile2(testFile));
        System.out.println(readFile3(testFile));
    }
}
