package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    public static List readFile1(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    public static List<String> readFile2(File file) throws IOException {
        List<String> stringList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = new FileInputStream(file);
        while (true) {
            int ch = inputStream.read();
            if (ch == -1) {
                break;
            } else if (ch != ('\n') && ch != ('\r')) {
                stringBuilder.append((char) ch);
            } else if (ch == '\n') {
                stringList.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
        }
        return stringList;
    }


    public static List<String> readFile3(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> list3 = new ArrayList<>();
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            list3.add(line);
        }
        return list3;
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        FileUtils.writeLines(file, Charset.defaultCharset().name(), lines);
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        for(String line : lines){
            os.write(line.getBytes(Charset.defaultCharset()));
            String lineSeparator = System.getProperty("line.separator");
            os.write(lineSeparator.getBytes());
        }
    }


    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter output = new BufferedWriter(fileWriter);
        for (String line : lines) {
            output.write(line);
            output.newLine();
        }
        output.close();
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
