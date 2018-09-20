package edu.jhzhang.basejava.virtual.classloading;

/**
 * @author jhZhang
 * @date 2018/9/20
 */
public class SuperClass {

    static {
        System.out.println("SupperClass init!");
    }

    public static int value = 123;
    public static final int finalValue = 123;
}
