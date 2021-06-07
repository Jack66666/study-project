package test;

public class Test {

    public static void main(String[] args) {
        int a = -10;
        System.out.println(Integer.toBinaryString(a));
        a = a << 2;
        System.out.println(Integer.toBinaryString(a));
        a = a >> 2;
        System.out.println(Integer.toBinaryString(a));
        a = a >>> 1;
        System.out.println(Integer.toBinaryString(a));

        a = 10;
        System.out.println(Integer.toBinaryString(a));
        a = a << 2;
        System.out.println(Integer.toBinaryString(a));
        a = a >> 2;
        System.out.println(Integer.toBinaryString(a));
        a = a >>> 1;
        System.out.println(Integer.toBinaryString(a));
    }
}
