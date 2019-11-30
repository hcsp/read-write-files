package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        List<String> lines1 = IOUtils.readLines(new FileInputStream(file), "UTF-8");
        return lines1;
    }

    public static List<String> readFile2(File file) throws IOException {
        List<String> lines2 = Files.readAllLines(file.toPath());
        return lines2;
    }

    public static List<String> readFile3(File file) throws IOException {
        ArrayList<String> lines3 = new ArrayList<>();
        FileReader fr = new FileReader(file);//创建输入流对象
        BufferedReader br = new BufferedReader(fr);//创建上一层输入流对象
        String string = null;//声明一个字符串为空
        while ((string = br.readLine()) != null) {
            lines3.add(string);
        }//读出字符串不为空的循环，打印字符串
        br.close();//关闭上层输入流
        fr.close();//关闭下层输入流
        return lines3;
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        FileUtils.writeLines(file, "UTF-8", lines);
    }
    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        Files.write(file.toPath(),lines);
    }


    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
//        BufferedWriter asd=new BufferedWriter(new FileWriter(file.getName()));
//        asd.write(lines.get(0));
//        asd.write(lines.get(1));
//        asd.write(lines.get(2));

        FileWriter asd = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(asd);
        for (String str : lines) {
            bw.write(str);
            bw.newLine();
        }
        bw.flush();
        asd.close();//关闭上一层输出流
        bw.close();//关闭输出流

//        Files.newBufferedWriter(file.toPath(),lines,);
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
