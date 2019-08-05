package com.github.hcsp.io;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file)  {
        List<String> lines =null;
        try(FileInputStream fileInputStream = new FileInputStream(file);) {
            lines =  IOUtils.readLines(fileInputStream,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static List<String> readFile2(File file) {
        List<String> list = new ArrayList();
        try (FileInputStream fileInputStream = new FileInputStream(file);){
            byte[] bytes = new byte[1024];
            int len =-1;
            while ((len = fileInputStream.read(bytes)) != -1) {
                list.add(new String(bytes, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    public static List<String> readFile3(File file) {
        List<String> list = new ArrayList();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));){
            String line = null;
            line = bufferedReader.readLine();
            while (line != null){
                list.add(line);
                try {
                    line = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeLinesToFile1(List<String> lines, File file) {
        Iterator<String> iterator = lines.iterator();
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);) {
            while (iterator.hasNext()){
                try {
                    IOUtils.write(iterator.next(),fileOutputStream,"UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLinesToFile2(List<String> lines, File file) {
        Iterator<String> iterator = lines.iterator();
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);) {
            while (iterator.hasNext()){
                byte[] bytes = iterator.next().getBytes();
                try {
                    fileOutputStream.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLinesToFile3(List<String> lines, File file) {
        Iterator<String> iterator = lines.iterator();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));) {
            while (iterator.hasNext()){
                try {
                    bufferedWriter.write(iterator.next());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
