package cn.itcast.test;

import cn.itcast.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JacksonTest {

    // 使用Jackson解析器，将Java对象转换为JSON字符串
    @Test
    public void test1() throws IOException {
        // 1. 创建Person对象
        Person person = new Person();
        person.setName("coco");
        person.setAge(18);
        person.setGender("female");

        // 创建Jackson的核心对象
        ObjectMapper objectMapper = new ObjectMapper();

        // 将person转换为JSON字符串
        /*
        转换方法：
            objectMapper.write(参数1,java对象);
                参数1的取值有：
                    File            将java对象转换为JSON字符串，并保存到指定文件中
                    Writer          将java对象转换为JSON字符串，并将json数据填充到字符输出流中
                    outputStream    将java对象转换为JSON字符串，并将json数据填充到字节输出流中
                    
            objectMapper.writeValueAsString(java对象);
                                    将java对象转换为JSON字符串
         */

        // 将Person对象转换为JSON字符串
        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);   // {"name":"coco","age":18,"gender":"female"}

        // 将Person对象转换为JSON字符串，并保存到指定文件中
        objectMapper.writeValue(new File("d://a.txt"), person);

        // 将Person对象转换为JSON字符串，并将json数据填充到字符输出流中，然后再保存到指定文件中
        objectMapper.writeValue(new FileWriter(new File("d://b.txt")), person);
    }





    // 使用Jackson的注解
    @Test
    public void test2() throws IOException {
        // 1. 创建Person对象
        Person person = new Person();
        person.setName("coco");
        person.setAge(18);
        person.setGender("female");
        person.setBirthday(new Date());

        // 创建Jackson的核心对象
        ObjectMapper objectMapper = new ObjectMapper();

        // 将Person对象转换为JSON字符串
        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);   // {"name":"coco","age":18,"gender":"female","birthday":1567303327718}

        // 因为Person对象的birthday属性为日期格式，所以Jackson会默认将其解析为毫秒值格式的
        // 而毫秒值并不是我们想要的，有两种解决办法：
        // 1. 在Person类中对该属性加上Jackson注解@JsonIgnore，那么Jackson就会跳过对该属性的解析
        // 2. 在Person类中对该属性加上Jackson注解@JsonFormat，并指定解析的格式，然后Jackson会按照指定的格式解析该属性
    }




    // 将List集合转换为JSON字符串
    @Test
    public void test3() throws IOException {
        // 1. 创建Person对象
        Person person1 = new Person();
        person1.setName("coco");
        person1.setAge(18);
        person1.setGender("female");

        Person person2 = new Person();
        person2.setName("coco");
        person2.setAge(18);
        person2.setGender("female");

        Person person3 = new Person();
        person3.setName("coco");
        person3.setAge(18);
        person3.setGender("female");

        // 2. 创建List集合
        List<Person> list = new ArrayList<Person>();
        list.add(person1);
        list.add(person2);
        list.add(person3);

        // 创建Jackson的核心对象
        ObjectMapper objectMapper = new ObjectMapper();

        // 将List集合转换为JSON字符串
        String json = objectMapper.writeValueAsString(list);
        System.out.println(json);
        // [{"name":"coco","age":18,"gender":"female"},{"name":"coco","age":18,"gender":"female"},{"name":"coco","age":18,"gender":"female"}]

    }





    // 将Map集合转换为JSON字符串
    @Test
    public void test4() throws IOException {
        // 1. 创建Map集合
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "mumu");
        map.put("age", 20);
        map.put("gender", "male");

        // 创建Jackson的核心对象
        ObjectMapper objectMapper = new ObjectMapper();

        // 将List对象转换为JSON字符串
        String json = objectMapper.writeValueAsString(map);
        System.out.println(json);
        System.out.println(json.getClass());
        // {"gender":"male","name":"mumu","age":20}

    }




    // 将JSON字符串转换为Java对象
    @Test
    public void test5() throws IOException {
        // 1. 创建JSON字符串
        String json = "{\"gender\":\"male\",\"name\":\"mumu\",\"age\":20}";
        
        // 2. 创建Jackson的核心对象
        ObjectMapper objectMapper = new ObjectMapper();

        // 3. 将JSON字符串转换person对象
        Person person = objectMapper.readValue(json, Person.class);
        System.out.println(person);
        // Person{name='mumu', age=20, gender='male', birthday=null}

    }



}
