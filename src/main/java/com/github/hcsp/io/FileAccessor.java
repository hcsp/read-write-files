package com.github.hcsp.io;


import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        return Files.readAllLines(file.toPath(),Charset.defaultCharset());

    }

    public static List<String> readFile2(File file) throws IOException {
       return FileUtils.readLines(file,Charset.defaultCharset());

    }

    public static List<String> readFile3(File file) throws IOException {
        List<String> readList=new ArrayList<>();
        BufferedReader br=new BufferedReader(new FileReader(file));
        String s = "";
        while ((s=br.readLine())!=null){
            readList.add(s);

        }
        br.close();
        return readList;


    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {


        Files.write(file.toPath(), lines, Charset.defaultCharset());


    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        //Iterator迭代器，每次调用都会得到一个新的迭代器对象
        Iterator<String> iterator = lines.iterator();
        //hasNext 判断是否有下一个元素
        while (iterator.hasNext()) {
            os.write(iterator.next().getBytes());
            os.write("\r\n".getBytes());
        }
        os.close();


    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        FileUtils.writeLines(file,lines);
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
