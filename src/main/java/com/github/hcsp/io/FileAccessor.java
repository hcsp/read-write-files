package com.github.hcsp.io;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file) throws IOException {
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        return lines;
    }
//
    public static List<String> readFile2(File file) throws IOException {
        FileInputStream fin=new FileInputStream(file);
        int i=0;
        StringBuilder result = new StringBuilder();
        while((i=fin.read())!=-1){
            System.out.print((char)i);
            result.append((char) i);
        }
        List<String> list = new ArrayList<String>(Arrays.asList(result.toString().split("\n")));

        fin.close();
        return list;
    }
//
    public static List<String> readFile3(File file) throws IOException {

        FileReader fr=new FileReader(file);
        BufferedReader br=new BufferedReader(fr);

        StringBuilder result = new StringBuilder();
        int i;
        while((i=br.read())!=-1){
            result.append((char) i);
        }
        List<String> list = new ArrayList<String>(Arrays.asList(result.toString().split("\n")));

        br.close();
        fr.close();



        return list;
    }

    public static void writeLinesToFile1(List<String> lines, File file) throws IOException {
        StringBuilder result = new StringBuilder();
        for (String line : lines) {
            result.append(line).append("\n");
        }
        byte[] test = result.toString().getBytes(StandardCharsets.UTF_8);
        FileUtils.writeByteArrayToFile(file, test);
    }

    public static void writeLinesToFile2(List<String> lines, File file) throws IOException {
        FileOutputStream fout = new FileOutputStream(file);
        StringBuilder result = new StringBuilder();
        for (String line : lines) {
            result.append(line).append("\n");
        }
        byte[] test = result.toString().getBytes(StandardCharsets.UTF_8);
        fout.write(test);
        fout.close();
    }

    public static void writeLinesToFile3(List<String> lines, File file) throws IOException {
        // Creates a FileWriter
        FileWriter fileWriter = new FileWriter(file);

        // Creates a BufferedWriter
        BufferedWriter output = new BufferedWriter(fileWriter);

        // Writes the string to the file
        StringBuilder result = new StringBuilder();
        for (String line : lines) {
            result.append(line).append("\n");
        }
//        byte[] test = result.toString().getBytes(StandardCharsets.UTF_8);


        output.write(String.valueOf(result));

        // Closes the writer
        output.close();
    }


    // 1 使用 Apache commons IO
    // 2 使用 fileinputstream/fileoutstream
    // 3 使用 bufferReader/bufferwriter
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
