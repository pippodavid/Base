import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

/*简单测试框架的效果
* 当主方法执行后，会自动执行被检测的方法
 */
public class TestCheck {
    public static void main(String[] args) throws IOException {
        // 生成计算器对象
        Calculator calculator = new Calculator();
        //获取字节码文件对象
        Class cls = calculator.getClass();
        //获取所有方法
        Method[] methods = cls.getMethods();

        //出现异常
        int number = 0;
        BufferedWriter bw = new BufferedWriter(new FileWriter("bug.txt"));

        for (Method method : methods) {
            //判断Check注解是否出现在method方法上
            if (method.isAnnotationPresent(Check.class)) {
                try {
                    method.invoke(calculator);
                } catch (Exception e) {
                    number++;

                    bw.write(method.getName() + "方法异常了");
                    bw.newLine();
                    bw.write("异常的名称" + e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常的原因" + e.getCause().getMessage());
                    bw.newLine();
                    bw.write("--------");
                }
            }
        }
        bw.write("本次测试一共出现" + number + "次异常");

        bw.flush();   //强制刷出缓冲池中的数据
        bw.close();
    }
}
