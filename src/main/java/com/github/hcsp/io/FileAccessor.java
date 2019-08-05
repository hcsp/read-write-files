package com.github.hcsp.io;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileAccessor {
    //  common io
    public static List<String> readFile1(File file)  {
        FileInputStream fileInputStream = null;
        List<String> lines =null;
        try {
            fileInputStream = new FileInputStream(file);
            lines =  IOUtils.readLines(fileInputStream,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }

    //  最原始FileInputStream/FileOutputStream，一个字符一个字符的读取
    public static List<String> readFile2(File file) throws IOException {
        List<String> list = new ArrayList();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[1024];
        int len =-1;
        while ((len = fileInputStream.read(bytes)) != -1) {
            list.add(new String(bytes, 0, len));
        }
        fileInputStream.close();
        return list;

    }

    //使用BufferReader/BufferedWriter，一行一行地读写文件
    public static List<String> readFile3(File file) {
        List<String> list = new ArrayList();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null){
            list.add(line);
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // common io 写文件
    public static void writeLinesToFile1(List<String> lines, File file) {
        Iterator<String> iterator = lines.iterator();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (iterator.hasNext()){
            try {
                IOUtils.write(iterator.next(),fileOutputStream,"UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 最原始FileInputStream/FileOutputStream，一个字符一个字符的读取
    public static void writeLinesToFile2(List<String> lines, File file) {
        Iterator<String> iterator = lines.iterator();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (iterator.hasNext()){
            byte[] bytes = iterator.next().getBytes();
            try {
                fileOutputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 使用BufferReader/BufferedWriter，一行一行地读写文件
    public static void writeLinesToFile3(List<String> lines, File file) {
        BufferedWriter bufferedWriter =null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()){
            try {
                bufferedWriter.write(iterator.next());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedWriter.close();
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
