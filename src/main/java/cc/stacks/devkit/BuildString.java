package cc.stacks.devkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串构建工具
 */
@SuppressWarnings("all")
public class BuildString {

    public static String MAIL = "^[a-zA-Z_-]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}$";

    private static String Data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    // 生成随机值
    public static String Random(int Length) {
        StringBuffer CharacterBulid = new StringBuffer();
        int len = Data.length();
        for (int i = 0; i < Length; i++) {
            CharacterBulid.append(Data.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return CharacterBulid.toString();
    }

    public static boolean Verification(String Type,String Data){
        Pattern pattern = Pattern.compile(Type);
        Matcher matcher = pattern.matcher(Data);
        return matcher.matches();
    }

}
