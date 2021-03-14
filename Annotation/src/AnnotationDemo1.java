/*
* 注解也叫元数据，一种代码级别的说明
* 注解三个作用：
* 1）编写文档：通过代码里标识的注解生成文档（本例的javadoc演示）
* 2）代码分析：通过代码里标识的注解对代码进行分析（利用反射）
* 3）编译检查：通过代码里的注解让编译器能实现基本的编译检查（例如@Override）
* 注解javadoc演示
* @since java 1.5
*/
public class AnnotationDemo1 {
    /*
    *计算两数的和
    * @param a 整数
    * @param b 整数
    * @return 两数之和
     */
    public int add(int a, int b) {
        return a + b;
    }
}
