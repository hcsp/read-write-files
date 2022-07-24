package com.github.hcsp.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;

public class FileAccessor {
    //使用FileInputStream
    public static List<String> readFile1(File file) throws IOException {
        FileInputStream f1 = new FileInputStream(file);
        int n = 0;
        List<String> readFileToString = Arrays.asList();
        while (n != -1) {

            n = f1.read();

            char by = (char) n;
            readFileToString.add(String.valueOf(by));

        }
        return readFileToString;
    }

    //使用bufferedreader
    public static List<String> readFile2(File file) throws IOException {
        FileReader fr2 = new FileReader(file);
        BufferedReader br = new BufferedReader(fr2);
        List<String> ls = new ArrayList<>();
        while ((br.readLine()) != null) {
            String s = br.readLine();
            ls.add(s);
        }
        return ls;
    }

    //使用NIO
    public static List<String> readFile3(File file) throws IOException {
        List<String> nioReadText = new ArrayList<>();
        String s = "";
        while (s != null) {
            s = String.valueOf(readAllLines(get(String.valueOf(file))));
            nioReadText.add(s);

        }
        return nioReadText;
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        for (String line : lines) {
            fos.write(line.getBytes(StandardCharsets.UTF_8));
            fos.write('\n');
        }
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        Files.write(file.toPath(), lines);
    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (String line : lines) {
            bw.write(line);
            bw.newLine();

        }
        bw.close();
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
