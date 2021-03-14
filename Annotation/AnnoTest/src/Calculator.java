public class Calculator {
    @Check
    public void add() {
        System.out.println("1 + 0 =" + (1 + 0));
    }

    @Check
    public void sub() {
        System.out.println("1 - 0 =" + (1 - 0));
    }

    @Check
    public void multi() {
        System.out.println("1 * 0 =" + (1 * 0));
    }

    @Check
    public void div() {
        System.out.println("1 / 0 =" + (1 / 0));
    }

    @Check
    public String toString() {
        String str = null;
        return str.toString();
    }

    public void show() {
        System.out.println("There is no bug.");
    }
}
