package cn.itcast.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;


/*
Jsoup入门
 */
public class JsoupDemo1 {
    public static void main(String[] args) throws IOException {
        // 1. 通过xml文档获取Document对象
        // 1-1 先通过类加载器获取xml文档的路径
        String path = JsoupDemo1.class.getClassLoader().getResource("student.xml").getPath();
        // 1-2 解析xml文档，将文档加载进内存，获取dom树（Document对象）
        Document document = Jsoup.parse(new File(path), "utf-8");

        // 2. 获取元素对象，比如获取name标签
        Elements elements = document.getElementsByTag("name");  // 返回name元素对象的集合
        System.out.println(elements.size());

        // 3-1 获取第一个name元素对象
        Element element = elements.get(0);

        // 3-2 获取数据
        String nameValue = element.text();

        System.out.println(nameValue);
    }
}
