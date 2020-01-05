package cn.itcast.xml.jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;


/*
Xpath查询
 */
public class JsoupDemo6 {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        // 1. 通过xml文档获取Document对象
        // 1-1 先通过类加载器获取xml文档的路径
        String path = JsoupDemo6.class.getClassLoader().getResource("student.xml").getPath();
        // 1-2 解析xml文档，将文档加载进内存，获取dom树（Document对象）
        Document document = Jsoup.parse(new File(path), "utf-8");


        // 创建JXDocument对象
        JXDocument jxDocument = new JXDocument(document);

        // 查询所有的student元素
        List<JXNode> jxNodes = jxDocument.selN("//student");
        for (JXNode jxNode : jxNodes) {
            System.out.println(jxNode);
        }
        System.out.println("==============");

        // 查询所有student元素下的所有name元素
        List<JXNode> jxNodes1 = jxDocument.selN("//student/name");
        for (JXNode jxNode : jxNodes1) {
            System.out.println(jxNode);
        }
        System.out.println("==============");

        // 查询student元素下带属性id的name元素
        List<JXNode> jxNodes2 = jxDocument.selN("//student/name[@id]");
        for (JXNode jxNode : jxNodes2) {
            System.out.println(jxNode);
        }
        System.out.println("==============");

        // 查询student元素下属性id的值为itcast的name元素
        List<JXNode> jxNodes3 = jxDocument.selN("//student/name[@id='itcast']");
        for (JXNode jxNode : jxNodes3) {
            System.out.println(jxNode);
        }

    }
}
