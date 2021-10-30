package com.github.hcsp.io;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    // 使用最原始的io读取
    public static List<String> readFile1(File file) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = fileInputStream.read()) != -1) {
                sb.append((char) c);
            }
            return Arrays.asList(sb.toString().split("\n"));
        }
    }

    //  使用nio的api读取，java7+后新增
    public static List<String> readFile2(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    //  使用IOUtils读取
    public static List<String> readFile3(File file) throws IOException {
        return IOUtils.readLines(new FileInputStream(file), "UTF-8");
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bytes = String.join("\n", lines).getBytes();
        for (byte aByte : bytes) {
            fileOutputStream.write(aByte);
        }
        fileOutputStream.close();
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(String.join("\n",lines).getBytes());
    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        IOUtils.write(String.join("\n", lines), new FileOutputStream(file),"UTF-8");
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
