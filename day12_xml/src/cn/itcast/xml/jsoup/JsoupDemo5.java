package cn.itcast.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;


/*
选择器查询
 */
public class JsoupDemo5 {
    public static void main(String[] args) throws IOException {
        // 1. 通过xml文档获取Document对象
        // 1-1 先通过类加载器获取xml文档的路径
        String path = JsoupDemo5.class.getClassLoader().getResource("student.xml").getPath();
        // 1-2 解析xml文档，将文档加载进内存，获取dom树（Document对象）
        Document document = Jsoup.parse(new File(path), "utf-8");

        // 查询所有的name元素对象
        Elements elements = document.select("name");
        System.out.println(elements);
        System.out.println("==================");

        // 查询属性id值为itcast的元素对象
        Elements elements1 = document.select("#itcast");
        System.out.println(elements1);
        System.out.println("==================");

        // 查询student元素且属性number的值为heima_0001的age元素对象
        Elements elements2 = document.select("student[number=\"heima_0001\"] > age");
        System.out.println(elements2);
    }
}
