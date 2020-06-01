package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    //使用Java 7+的NIO引入的Files.readAllLines()方法
    public static List<String> readFile1(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    //使用第三方库Apache Commons IO读取文件
    public static List<String> readFile2(File file) throws IOException {
        return FileUtils.readLines(file);
    }

    //使用BufferReader读取文件
    public static List<String> readFile3(File file) throws IOException {
        List<String> list = new ArrayList<>();

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String str = "";
        while ((str = bufferedReader.readLine()) != null) {
            list.add(str);
        }
        return list;
    }


    //使用Java 7+的NIO引入的Files.write()写入文件
    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        String s = "";
        for (String a : lines) {
            s += a;
            s += "\n";

        }
        Files.write(file.toPath(), s.getBytes());
    }

    //使用BufferWriter写入文件
    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < lines.size(); i++) {
            bufferedWriter.write(lines.get(i));
            bufferedWriter.newLine();
        }
    }

    //使用第三方库Apache Commons IO写入文件
    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        FileUtils.writeLines(file, lines);
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
