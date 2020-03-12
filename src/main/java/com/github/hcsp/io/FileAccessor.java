package com.github.hcsp.io;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        FileInputStream is = new FileInputStream(file);
        List<String> list = new ArrayList<>();
        while (true) {
            int b = is.read();
            list.add(String.valueOf((char) b));
            if (b == -1) {
                break;
            }
        }
        return list;
    }

    public static List<String> readFile2(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    public static List<String> readFile3(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        List<String> list = new ArrayList<>();

        list.add(IOUtils.toString(is, Charset.defaultCharset()));

        return list;
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        FileWriter writer;
        writer = new FileWriter(file);

        for (String line : lines) {
            writer.write(line);
            writer.flush();
        }
        writer.close();
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        byte[] bytes;

        FileOutputStream fos = new FileOutputStream(file);
        for (String line : lines) {
            bytes = line.getBytes();
            fos.write(bytes);
        }
        fos.close();
    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException { //通过传入File实例化Scanner类
        for (String line : lines) {
            Files.write(file.toPath(), line.getBytes());
        }
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
