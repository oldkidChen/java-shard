package com.shard.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chen_jinglong
 * @description: 正则表达式过滤文件
 * @date 2023/9/25 9:50
 */
public class RegexpFile {

    public static void main(String[] args) {
        Integer[] mallIds = {331144,331145,331146,331147,331148,331149,331150,331151,331152,331153,331154,331155,331156,331157,331158,331159,331160,331161,331162,331163,331164,331165,331166,331167,331168,331169,331170,331171,331172,331173,331174,331175,331176,331177,331178,331179,331180,331181,331182,331183,331184,331185,331186,331187,331188,331189,331190,331191,331192,331193,331194,331195,331196,331197,331198,331199,331200,331201,331202,331203,331204,331205,331206,331207,331208,331209,331210,331211,331212,331213,331214,331215,331216,331217,331218,331219,331220,331221,331222,331223,331224,331225,331226,331227,331228,331229,331230,331231,331232,331233,331234,331235,331236,331237,331238,331239,331240,331241,331242,331243,331244,331245,331246,331247,331248,331249,331250,331251,331252,331253,331254,331255,331256,331257,331258,331259,331260,331261,331262,331263,331264,331265,331266,331267,331268,331269,331270,331271,331272,331273,331274,331275,331276,331277,331278,331279,331280,331281,331282,331283,331284,331285,331286,331287,331288,331289,331290,331291,331292,331293,331294,331295,331296,331297,331298,331299,331300,331301,331302,331303,331304,331305,331306,331307,331308,331309,331310,331311,331312,331313,331314,331315,331316,331317,331318,331319,331320,331321,331322,331323,331324,331325,331326,331327,331328,331329,331330,331331,331332,331333,331334,331335,331336,331337,331338,331339,331340,331341,331342,331343,331344,331345,331346,331347,331348,331349,331350,331351,331352,331353,331354,331355,331356,331357,331358,331359,331360,331361,331362,331363,331364,331365,331366,331367,331368,331369,331370,331371,331372,331373,331374,331375,331376,331377,331378,331379,331380,331381,331382,331383,331384,331385,331386,331387,331388,331389,331390,331391,331392,331393,331394,331395,331396,331397,331398,331399,331400,331401,331402,331403,331404,331405,331406,331407,331408,331409,331410,331411,331412,331413};
        Integer[] saasIds = {331144,331145,331146,331147,331148,331149,331150,331151,331152,331153,331154,331155,331156,331157,331158,331159,331160,331161,331162,331163,331164,331165,331166,331167,331168,331169,331170,331171,331312,331313,331314,331315,331316,331317,331318,331319,331320,331321,331322,331323,331324,331325,331326,331327,331328,331329,331330,331331,331332,331333,331334,331335,331336,331337,331338,331339,331340,331341,331342,331343,331344,331345,331346,331347,331348,331349,331350,331351,331352,331353,331354,331355,331356,331357,331358,331359,331360,331361,331362,331363,331364,331365,331366,331367,331368,331369,331370,331371,331372,331373,331374,331375,331376,331377,331378,331379,331380,331381,331382,331383,331384,331385,331386,331387,331388,331389,331390,331391,331392,331393,331394,331395,331396,331397,331398,331399,331400,331401,331402,331403,331404,331405,331406,331407,331408,331409,331410,331411,331412,331413};

        // 找出两个数组中不同的元素，并放到新的数组中
        ArrayList<Integer> res = getDiff(mallIds, saasIds);
        System.out.println(res);
        String filePath = "D:\\备份\\9.16修复数据\\13.log";
        /** 创建自营订单 开始*/
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String logLine;
//            while ((logLine = reader.readLine()) != null) {
//                for (Integer id : res) {
//                    // 自营订单
//                    String regex = "\\b支付成功回调saas" + id + "#\\S+\\b";
//                    Pattern pattern = Pattern.compile(regex);
//                    Matcher matcher = pattern.matcher(logLine);
//                    if (matcher.find()) {
//                        String matchedText = matcher.group();
//                        System.out.println(matchedText);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        /** 创建自营订单 结束*/
        /** 自营订单支付回调 开始*/
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String logLine;
//            while ((logLine = reader.readLine()) != null) {
//                for (Integer id : res) {
//                    // 自营订单
//                    String regex = "\\b支付成功回调saas" + id;
//                    Pattern pattern = Pattern.compile(regex);
//                    Matcher matcher = pattern.matcher(logLine);
//                    if (matcher.find()) {
//                        String matchedText = matcher.group();
//                        System.out.println(matchedText);
//                    }
//                }
//            }
//        } catch(IOException e){
//            throw new RuntimeException(e);
//        }
        /** 自营订单支付回调 结束*/


        /** 创建三方订单 开始*/
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String logLine;
//            while ((logLine = reader.readLine()) != null) {
//                // 过滤时间,在查找三方订单时使用
//                if (checkTime(logLine)) {
//                    // 三方订单
//                    String regex = "\\b三方订单保存saas同步\\S+#\\{}";
//                    Pattern pattern = Pattern.compile(regex);
//                    Matcher matcher = pattern.matcher(logLine);
//                    if (matcher.find()) {
//                        String matchedText = matcher.group();
//                        System.out.println(matchedText);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        /** 创建三方订单 结束*/
        /** 三方订单状态更新 开始*/
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String logLine;
            while ((logLine = reader.readLine()) != null) {
                // 过滤时间,在查找三方订单时使用
                if (checkTime(logLine)) {
                    // 三方订单
                    String regex = "\\b三方订单保存saas同步\\[\\]#(.*)$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(logLine);
                    if (matcher.find()) {
                        String matchedText = matcher.group();
                        System.out.println(matchedText);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /** 三方订单状态更新 结束*/


    }
    public static ArrayList getDiff(Integer[] mallIds,Integer[] saasIds ){
        ArrayList<Integer> differentIds = new ArrayList<>();
        Arrays.sort(mallIds);
        Arrays.sort(saasIds);

        int i = 0, j = 0;
        while (i < mallIds.length && j < saasIds.length) {
            if (mallIds[i].equals(saasIds[j])) {
                i++;
                j++;
            } else if (mallIds[i] < saasIds[j]) {
                differentIds.add(mallIds[i]);
                i++;
            } else {
                differentIds.add(saasIds[j]);
                j++;
            }
        }

        while (i < mallIds.length) {
            differentIds.add(mallIds[i]);
            i++;
        }

        while (j < saasIds.length) {
            differentIds.add(saasIds[j]);
            j++;
        }

        return differentIds;
    }

    private static boolean checkTime(String logLine) {
        String regex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.find()) {
            String timeString = matcher.group();
            // 设置开始时间和结束时间
            String startTimeString = "2023-09-16 07:59:15.000";
            String endTimeString = "2023-09-16 16:54:59.999";

            return timeString.compareTo(startTimeString) >= 0 && timeString.compareTo(endTimeString) <= 0;
        } else {
            return false;
        }
    }
}
