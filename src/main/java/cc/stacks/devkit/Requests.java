package cc.stacks.devkit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;

/**
 * 请求工具
 */
public class Requests {

    public String Get(String Url) {
        // 创建Http请求客户端对象
        OkHttpClient Client = new OkHttpClient();
        // 创建请求对象
        Request RequestObj = new Request.Builder().url(Url).header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36").build();
        try {
            return AnalyticalResponse(Client.newCall(RequestObj).execute());
        } catch (IOException e) {
            return null;
        }
    }

    public String Get(String Url, String Referer) {
        // 创建Http请求客户端对象
        OkHttpClient Client = new OkHttpClient();
        // 创建请求对象
        Request RequestObj = new Request.Builder().url(Url).header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36").build();
        try {
            return AnalyticalResponse(Client.newCall(RequestObj).execute());
        } catch (IOException e) {
            return null;
        }
    }

    public String Post(String Url, String Type) {
        // 创建OkHttp请求客户端对象
        OkHttpClient Client = new OkHttpClient();
        // 创建媒体类型对象
        MediaType mediaType = MediaType.parse(Type);
        // 创建请求体对象
        RequestBody body = RequestBody.create(mediaType, "");
        // 创建请求对象
        Request request = new Request.Builder().url(Url).post(body).header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36").addHeader("Cache-Control", "no-cache").addHeader("Content-Type", Type).build();
        try {
            return AnalyticalResponse(Client.newCall(request).execute());
        } catch (IOException e) {
            return null;
        }
    }

    public String Post(String Url, String Type, String Content) {
        // 创建OkHttp请求客户端对象
        OkHttpClient Client = new OkHttpClient();
        // 创建媒体类型对象
        MediaType mediaType = MediaType.parse(Type);
        // 创建请求体对象
        RequestBody body = RequestBody.create(mediaType, Content);
        // 创建请求对象
        Request request = new Request.Builder().url(Url).post(body).header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36").addHeader("Cache-Control", "no-cache").addHeader("Content-Type", Type).build();
        try {
            return AnalyticalResponse(Client.newCall(request).execute());
        } catch (IOException e) {
            return null;
        }
    }

    private static String AnalyticalResponse(okhttp3.Response response) {
        try {
            // 判断请求状态
            if (!response.isSuccessful()) {
                // 输出错误信息
                BuildLogs.Error(200001,"Request Error: " + response,Requests.class);
                return null;
            }
            // 返回页面内容
            return response.body().string();
        } catch (Exception e) {
            BuildLogs.Error(200001,"Request Error: " + e,Requests.class);
            return null;
        }
    }

}
