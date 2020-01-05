package cn.itcast.servletContext;


import java.util.Base64;
import java.util.Base64.Encoder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/*
根据不同浏览器，设置中文文件名的编码格式
 */
public class DownLoadUtils {

    public static String getFileName(String agent, String filename) throws UnsupportedEncodingException {
        if (agent.contains("MSIE")) {
            // IE浏览器：使用URL编码
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");  // IE浏览器比较特殊，需要将 +  替换为空格
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器：使用Base64编码
            Encoder base64Encoder = Base64.getEncoder();
            filename = "=?utf-8?B?" + base64Encoder.encodeToString(filename.getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器：使用URL编码
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
}
