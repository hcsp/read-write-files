package com.github.hcsp.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccessor {
    public static List<String> readFile1(File file) {
        List<String> list = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            while (true) {
                int read = fis.read();
                if (read == -1) {
                    break;
                }
                list.add("" + (char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static List<String> readFile2(File file) {
        List<String> list = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while (true) {
                String s = br.readLine();
                if (s == null) {
                    break;
                }
                list.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static List<String> readFile3(File file) {
        List<String> list = null;
        try {
            list = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeLinesToFile1(List<String> lines, File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            for (String str : lines) {
                fos.write(str.getBytes(StandardCharsets.UTF_8));
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeLinesToFile2(List<String> lines, File file) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (String str : lines) {
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeLinesToFile3(List<String> lines, File file) {
        for (String str : lines) {
            try {
                Files.write(file.toPath(), str.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        File testFile = new File(projectDir, "target/test.txt");
        List<String> lines = Arrays.asList("AAA", "BBB", "CCC");
//        writeLinesToFile1(lines, testFile);
        writeLinesToFile2(lines, testFile);
        writeLinesToFile3(lines, testFile);

//        System.out.println(readFile1(testFile));
//        System.out.println(readFile2(testFile));
        System.out.println(readFile3(testFile));
    }
}
