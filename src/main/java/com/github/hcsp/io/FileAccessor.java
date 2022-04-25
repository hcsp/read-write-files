package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        return FileUtils.readLines(file, Charset.defaultCharset());

    }

    public static List<String> readFile2(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        List<String> list1 = new ArrayList<String>();
        List<Character> list = new ArrayList<Character>();
        while (true) {
            int i = is.read();
            if (i == -1) {
                break;
            }
            char c = (char) i;
            list.add(c);
            if (c == '\n') {
                StringBuilder sb = new StringBuilder();
                for (Character character: list) {
                    sb.append(character);

                }
                list1.add(sb.toString());
                list.clear();
            }
        }
        return list1;

    }

    public static List<String> readFile3(File file) throws IOException {
        List<String> readFileList = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        while (br.readLine() != null) {
            readFileList.add(br.readLine());
        }
        return readFileList;

    }
    public static List<String> readFile4(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
//        for (String s : lines) {
//            FileUtils.writeStringToFile(file, s, Charset.defaultCharset());
//        }
        FileUtils.writeLines(file, lines);
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {

        OutputStream os = new FileOutputStream(file);
        for (String s : lines) {
            byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
            os.write(bytes);
            os.write('\n');

        }


    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (String s : lines) {
            bw.write(s, 0, s.length());
//            bw.write(s);
            bw.write('\n');
        }
    }
    public static void writeLinesToFile4(List<String> lines, File file) throws IOException {
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
        System.out.println(readFile4(testFile));
    }
}
