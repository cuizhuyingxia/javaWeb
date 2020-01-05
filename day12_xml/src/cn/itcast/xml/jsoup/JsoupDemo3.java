package cn.itcast.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;


/*
Document对象
 */
public class JsoupDemo3 {
    public static void main(String[] args) throws IOException {
        // 1. 通过xml文档获取Document对象
        // 1-1 先通过类加载器获取xml文档的路径
        String path = JsoupDemo3.class.getClassLoader().getResource("student.xml").getPath();

        // 1-2 解析xml文档，将文档加载进内存，获取dom树（Document对象）
        Document document = Jsoup.parse(new File(path), "utf-8");

        // 获取所有的name元素对象
        Elements elements = document.getElementsByTag("name");  // 返回name元素对象的集合
        System.out.println(elements);
        System.out.println("==============");

        // 获取属性名为id的元素对象
        Elements elements1 = document.getElementsByAttribute("id");
        System.out.println(elements1);
        System.out.println("==============");

        // 获取属性名为number，属性值为heima_0002的元素对象
        Elements elements2 = document.getElementsByAttributeValue("number", "heima_0002");
        System.out.println(elements2);
        System.out.println("==============");

        // 获取id为itcast的元素对象
        Element elementById = document.getElementById("itcast");
        System.out.println(elementById);


    }
}
