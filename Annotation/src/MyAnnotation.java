//注解本质是一个接口，默认的继承自java.lang.annotation.Annotation
public @interface MyAnnotation {
    /*注解中定义的方法称为注解的属性
    * 属性的返回值类型必须是基本类型，String, 枚举，注解，或者这些类型的数组
    */
    //public abstract String show();

    int show1();    //基本类型
    //String show2();   //String类型
    Person per();     //枚举
    BasicAnnaotaion anno();   //注解

    //int[] showArray1();
    String[] showArray2();
    //Person[] showArray3();
    //BasicAnnaotaion[] showArray4();
}
