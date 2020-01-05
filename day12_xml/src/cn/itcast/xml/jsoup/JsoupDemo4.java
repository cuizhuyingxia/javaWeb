package cn.itcast.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;


/*
Element对象
 */
public class JsoupDemo4 {
    public static void main(String[] args) throws IOException {
        // 1. 通过xml文档获取Document对象
        // 1-1 先通过类加载器获取xml文档的路径
        String path = JsoupDemo4.class.getClassLoader().getResource("student.xml").getPath();
        // 1-2 解析xml文档，将文档加载进内存，获取dom树（Document对象）
        Document document = Jsoup.parse(new File(path), "utf-8");

        // 获取所有的name元素对象
        Elements elements = document.getElementsByTag("name");
        System.out.println(elements.size());
        System.out.println("=================");

        // 获取第一个student元素
        Element ele_student = document.getElementsByTag("student").get(0);
        // 获取name元素
        Elements ele_name = ele_student.getElementsByTag("name");
        System.out.println(ele_name);
        System.out.println("=================");
        // 获取属性number的值
        String number = ele_student.attr("number");
        System.out.println(number);
        System.out.println("=================");
        // 获取name元素的文本内容
        String text = ele_name.text();  // 返回子标签的纯文本内容
        String html = ele_name.html();  // 返回子标签的html格式的文本内容
        System.out.println(text);
        System.out.println("=================");
        System.out.println(html);


    }
}
