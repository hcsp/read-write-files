package com.github.hcsp.io;

import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file) {
        try {
            FileInputStream is = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            List text = new ArrayList();
            while (bufferedReader.ready()) {
                text.add(bufferedReader.readLine());
            }
            bufferedReader.close();
            is.close();
            return text;
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            throw new RuntimeException("unexisting");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> readFile2(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            List text = new ArrayList();
            while (bufferedReader.ready()) {
                text.add(bufferedReader.readLine());
            }
            return text;
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            throw new RuntimeException("unexisting");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> readFile3(File file) throws IOException {
        return Files.readAllLines(Paths.get(file.getPath()));
    }

    public static void writeLinesToFile1(List<String> lines, File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));
            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLinesToFile2(List<String> lines, File file) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLinesToFile3(List<String> lines, File file) {
        try {
            Files.write(Paths.get(file.getPath()), lines);
        } catch (IOException e) {
            e.printStackTrace();
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
