package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.io.FileUtils.writeStringToFile;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        List<String> readFileList;
        readFileList = FileUtils.readLines(file, Charset.defaultCharset());
        return readFileList;
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
                list1.add(list.toArray().toString());
                list.clear();
            }
         }
        return list1;

    }

    public static List<String> readFile3(File file) throws IOException {
        List<String> readFileList = null;
        BufferedReader br = new BufferedReader(new FileReader(file));
        while (br.readLine() != null) {
            readFileList.add(br.readLine());
        }
        return readFileList;

    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        for (String s : lines) {
            writeStringToFile(file, s, Charset.defaultCharset());
        }
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {

        OutputStream os = new FileOutputStream(file);
        for (String s : lines) {
            byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
            os.write(bytes);

        }


    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (String s : lines) {
            bw.write(s, 0, s.length());
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
