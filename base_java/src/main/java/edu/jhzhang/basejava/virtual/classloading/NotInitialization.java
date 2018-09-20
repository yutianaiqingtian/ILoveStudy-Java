package edu.jhzhang.basejava.virtual.classloading;

/**
 * 非主动使用类字段演示: (可通过 -XX:TraceClassLoading 命令来观察具体的加载过程)
 * <p>
 * 被动使用类字段的演示，通过子类引用父类的静态字段，不会导致子类的初始化
 * </p>
 *
 * @author jhZhang
 * @date 2018/9/20
 */
public class NotInitialization {
    public static void main(String[] args) {
        // 被动引用一： 子类访问父类的静态变量，子类不会进行初始化
//        System.out.println(SubClass.value);
        // 被动引用二： 通过数据进行 newArray 操作，不会导致类进行初始化
//        SuperClass[] sca = new SuperClass[10];
        // 被动引用三: 访问类的静态常量.
        System.out.println(SubClass.finalValue);
    }
}
