package cc.stacks.devkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志构建工具
 */
@SuppressWarnings("all")
public class BuildLogs {

    // 调试
    public static int DEBUG = 1;

    // 信息
    public static int INFO = 2;

    // 警告
    public static int WARN = 3;

    // 错误
    public static int ERROR = 4;

    // 日志垫片对象
    private static Logger Log = LoggerFactory.getLogger("System");

    // 日志文本格式
    private static String LogText = "{} > {}";

    // 输出日志
    public static void Output(int Level, int Code, String Message) {
        switch (Level) {
            case 1:
                Log.debug(LogText, Code, Message);
                break;
            case 2:
                Log.info(LogText, Code, Message);
                break;
            case 3:
                Log.warn(LogText, Code, Message);
                break;
            case 4:
                Log.error(LogText, Code, Message);
                break;
            default:
                Log.info(LogText, Code, Message);
                break;
        }
    }

}
