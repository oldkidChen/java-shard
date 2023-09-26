package com.shard.util;

/**
 * @author chen_jinglong
 * @description:
 * @date 2023/8/14 15:31
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CompareFiles {
    public static Map<String, String> extractStatuses(String filePath) throws IOException {
        Map<String, String> statuses = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length >= 2) {
                    statuses.put(columns[0].trim(), columns[1].trim());
                }
            }
        }
        return statuses;
    }

    public static void main(String[] args) {
        String file1Path = "C:/Users/bqmcj/Desktop/file1.txt";
        String file2Path = "C:/Users/bqmcj/Desktop/file2.txt";

        try {
            Map<String, String> statusesFile1 = extractStatuses(file1Path);
            Map<String, String> statusesFile2 = extractStatuses(file2Path);

            System.out.println("不相同的状态值:");
            for (String id : statusesFile1.keySet()) {
                if (!statusesFile2.containsKey(id) || !statusesFile2.get(id).equals(statusesFile1.get(id))) {
                    System.out.println("ID: " + id + ", 文件1状态值: " + statusesFile1.get(id) + ", 文件2状态值: " + statusesFile2.getOrDefault(id, "不存在"));
                }
            }

            for (String id : statusesFile2.keySet()) {
                if (!statusesFile1.containsKey(id)) {
                    System.out.println("ID: " + id + ", 文件1状态值: 不存在, 文件2状态值: " + statusesFile2.get(id));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

