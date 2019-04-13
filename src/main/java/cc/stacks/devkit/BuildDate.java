package cc.stacks.devkit;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期构建工具
 */
@SuppressWarnings("all")
public class BuildDate {

    // 格式化
    public static String Format(String Pattern) {
        SimpleDateFormat SDF = new SimpleDateFormat(Pattern);
        return SDF.format(new Date());
    }

    // 格式化
    public static String Format(String Pattern, long TimeStamp) {
        SimpleDateFormat SDF = new SimpleDateFormat(Pattern);
        return SDF.format(new Date(TimeStamp));
    }

    // 当前时间戳
    public static String TimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

}
