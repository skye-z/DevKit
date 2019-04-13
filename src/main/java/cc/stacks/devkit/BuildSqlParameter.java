package cc.stacks.devkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL参数构建工具
 */
@SuppressWarnings("all")
public class BuildSqlParameter {

    // And连接符
    public static List<String> AndConnect() {
        // 创建连接符集合
        List<String> Connect = new ArrayList<>();
        // 插入And
        Connect.add("AND");
        // 返回连接符集合
        return Connect;
    }

    // Or连接符
    public static List<String> OrConnect() {
        // 创建连接符集合
        List<String> Connect = new ArrayList<>();
        // 插入And
        Connect.add("OR");
        // 返回连接符集合
        return Connect;
    }

    // 构建SQL参数
    public static String Build(Map<String, String> Parameters, List<String> Connect) {
        // 创建参数构建对象
        StringBuilder Screen = new StringBuilder("WHERE ");
        // 连接符数
        int ConnectSize = 0;
        // 遍历参数
        for (Map.Entry<String, String> Parameter : Parameters.entrySet()) {
            // 判断连接符数量
            if (Connect.size() == 1)
                // 构建SQL参数
                Screen.append("`").append(Parameter.getKey()).append("`='").append(Parameter.getValue()).append("' ").append(Connect.get(0)).append(" ");
            else if (Connect.size() == Parameters.size())
                // 构建SQL参数
                Screen.append("`").append(Parameter.getKey()).append("`='").append(Parameter.getValue()).append("' ").append(Connect.get(ConnectSize)).append(" ");
            else
                // 构建SQL参数
                Screen.reverse();
            // 连接符数加一
            ConnectSize++;
        }
        // 返回SQL参数
        return Screen.toString().substring(0, Screen.length() - 4).trim();
    }

    public static void main(String[] args) {
        Map<String, String> Parameters = new HashMap<>();
        Parameters.put("Id","1");
        Parameters.put("Name","测试");
        System.out.println("测试SQL构建: "+BuildSqlParameter.Build(Parameters,BuildSqlParameter.AndConnect()));
    }

}