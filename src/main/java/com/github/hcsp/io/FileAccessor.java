package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] buf = new byte[100];
        fileInputStream.read(buf);
        String ABC = new String(buf);
        List<String> ABCList = Arrays.asList(ABC);
        return ABCList;
    }

    public static List<String> readFile2(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buf = new byte[100];
        IOUtils.read(fileInputStream,buf);
        String ABC = new String(buf);
        List<String> ABCList = Arrays.asList(ABC);
        return ABCList;
    }

    public static List<String> readFile3(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
        for (String line:lines) {
            fileOutputStream.write(line.getBytes());
        }
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
            new IOUtils().writeLines(lines,"",new FileOutputStream(file,true), Charset.defaultCharset());
    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        for(String line : lines) {
            Files.write(Paths.get(file.toString()),line.getBytes(), StandardOpenOption.APPEND);
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
