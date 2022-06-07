package com.github.hcsp.io;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        List<String> readList = new ArrayList<>();
        byte[] bytes = Files.readAllBytes(file.toPath());
        String strings = new String(bytes);
        readList.add(strings);
        return readList;

    }

    public static List<String> readFile2(File file) throws IOException {
        List<String> readList = new ArrayList<>();
        InputStream io = new FileInputStream(file);
        byte[] buff = new byte[1024];
        int len;
        while ((len = io.read(buff)) != -1) {
            String s = new String(buff, 0, len);
            readList.add(s);
        }
        io.close();

        return readList;

    }

    public static List<String> readFile3(File file) throws IOException {

        List<String> readList = new ArrayList<>();
        //字符流
        //br缓冲流
        BufferedReader br = new BufferedReader(new FileReader(file));
        //存取读取字符的字符数组
        char[] cbuf = new char[1024];
        int len;
        while ((len = br.read(cbuf)) != -1) {
            String s = String.valueOf(cbuf, 0, len);
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
            os.write("\n".getBytes());
        }
        os.close();


    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()) {
            bw.write(iterator.next());
            bw.write("\n");

        }
        bw.close();
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
