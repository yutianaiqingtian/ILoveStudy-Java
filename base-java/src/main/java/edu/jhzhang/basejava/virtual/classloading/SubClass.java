package edu.jhzhang.basejava.virtual.classloading;

/**
 * @author jhZhang
 * @date 2018/9/20
 */
public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}
