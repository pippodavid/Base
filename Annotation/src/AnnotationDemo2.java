/*
* 预定义（内置）注解
* @Override 检测被注解标注的方法是否继承自父类（接口）
* @Deprecated 标识方法已经过时，不建议用，但仍然可以用
* @SuppressWarnings 压制方法或类中的警告
*/
@SuppressWarnings("all")  //需要传参数，此处压制所有警告
public class AnnotationDemo2 {
    @Override
    public String toString() {
        return super.toString();
    }

    @Deprecated
    public void show1() {
        //有缺陷，不再推荐使用
        System.out.println("This method is Deprecated.");
    }

    //@MyAnnotation
    public void show2() {
        //替代show1方法
        System.out.println("This method is replace last method");
    }

    public void test() {
        show1();
        show2();
    }
}
