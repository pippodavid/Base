import java.util.Scanner;

public class AnonymousTest {
    public static void main(String[] args) {
        Scanner sc = methodReturn();
        int num = sc.nextInt();
        System.out.println("输入的是：" + num);
    }

    public static Scanner methodReturn() {
        System.out.println("请输入一个整数：");
        return new Scanner(System.in);
    }
}
