import java.lang.reflect.Method;

//使用注解达到和配置文件相同的效果
@Pro(className = "Demo1", methodName = "show")
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        Class<ReflectTest> reflectTestClass = ReflectTest.class;

        Pro anno = reflectTestClass.getAnnotation(Pro.class);
        /*   使用注解后相当于生成了一个该注解接口的子类实现对象，类似如下的代码
        public class ProImpl implements Pro {
            public String calssName() {
                return "Demo1";
            }

            public String methodName() {
                return "show";
            }
        }
        * */

        String className = anno.className();
        String methodName = anno.methodName();

        System.out.println(className);
        System.out.println(methodName);

        //加载该类进内存，找不到会抛ClassNotFoundException
        Class cls = Class.forName(className);
        //创建对象，创建失败会抛InstantiationException
        Object obj = cls.newInstance();
        //获取方法对象，找不到会抛NoSuchMethodException
        Method method = cls.getMethod(methodName);
        //执行方法,执行不了抛InvocationTargetException
        method.invoke(obj);

    }


}
