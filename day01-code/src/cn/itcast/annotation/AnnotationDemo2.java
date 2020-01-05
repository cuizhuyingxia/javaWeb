package cn.itcast.annotation;

/**
 * JDk中预定义的一些注解
 */
@SuppressWarnings("all")    // 压制该注解标注的内容中的所有警告
public class AnnotationDemo2 {

    @Override   // 检测被该注解标注的方法是否是继承自父类（接口）的
    public String toString() {
        return super.toString();
    }

    @Deprecated // 该注解标注的内容，表示已过时，不推荐使用
    public void show1() {
        // 有缺陷
    }

    @MyAnnotation(value = 18, show1 = 19, p1 = Person.p2, anno2 = @MyVnnotation2, strs = {"abc", "efg"})
    public void show2() {
        // 代替show1方法
    }
}
