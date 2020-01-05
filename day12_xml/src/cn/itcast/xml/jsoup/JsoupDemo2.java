package cn.itcast.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;


/*
Jsoup对象的parse方法
 */
public class JsoupDemo2 {
    public static void main(String[] args) throws IOException {

        /*  parse​(File in, String charsetName)   解析xml或html文件*/
        /*// 1. 通过xml文档获取Document对象
        // 1-1 先通过类加载器获取xml文档的路径
        String path = JsoupDemo2.class.getClassLoader().getResource("student.xml").getPath();
        // 1-2 解析xml文档，将文档加载进内存，获取dom树（Document对象）
        Document document = Jsoup.parse(new File(path), "utf-8");

        // 2. 获取元素对象，比如获取name标签
        Elements elements = document.getElementsByTag("name");  // 返回name元素对象的集合
        System.out.println(elements.size());

        // 3-1 获取第一个name元素对象
        Element element = elements.get(0);

        // 3-2 获取数据
        String text = element.text();

        System.out.println(text);*/







        /*  parse​(String html)   解析xml或html字符串*/
        /*// 解析html或xml字符串
        String str = "<students>\n" +
                "\t<student number=\"heima_0001\">\n" +
                "\t\t<name>tom</name>\n" +
                "\t\t<age>18</age>\n" +
                "\t\t<sex>male</sex>\n" +
                "\t</student>\n" +
                "\n" +
                "\t<student number=\"heima_0002\">\n" +
                "\t\t<name>coco</name>\n" +
                "\t\t<age>18</age>\n" +
                "\t\t<sex>female</sex>\n" +
                "\t</student>\n" +
                "</students>";
        Document document = Jsoup.parse(str);
        //System.out.println(document);
        Elements elements = document.getElementsByTag("name");
        Element element = elements.get(0);
        String text = element.text();
        System.out.println(text);*/







        /*  parse​(URL url, int timeoutMillis)  通过网络路径解析指定的xml或html文件*/
        /*URL url = new URL("https://baike.baidu.com/item/jsoup");
        Document document = Jsoup.parse(url, 10000);
        System.out.println(document);*/
    }
}
