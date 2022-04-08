package com.github.hcsp.io;

import com.google.common.base.Joiner;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    // 使用 commons-io 库读取文件
    public static List<String> readFile1(File file) throws IOException {
        return FileUtils.readLines(file, "UTF-8");
    }

    // 使用FileInputStream读取文件(不推荐)
    public static List<String> readFile2(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        List<String> lines = new ArrayList<>();
        List<Character> characters = new ArrayList<>();
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                break;
            } else if (read == '\n') {
                lines.add(Joiner.on("").join(characters));
                characters.clear();
            } else {
                characters.add((char) read);
            }
        }
        inputStream.close();
        return lines;
    }

    // 使用BufferReader读取文件
    public static List<String> readFile3(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<>();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                bufferedReader.close();
                return lines;
            }
            lines.add(line);
        }
    }

    // 使用NIO的Files类读取文件
    public static List<String> readFile4(File file) throws IOException {
        return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
    }

    // 使用Apache commons io 库写入文件

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        FileUtils.writeLines(file, "UTF-8", lines);
    }

    // 使用FileOutputStream写入文件
    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);
        for (String line : lines) {
            outputStream.write(line.getBytes());
            outputStream.write('\n');
        }
        outputStream.close();
    }

    // 使用NIO的Files类写入文件
    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        Files.write(file.toPath(), lines, StandardCharsets.UTF_8);
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
        System.out.println(readFile4(testFile));
    }
}
