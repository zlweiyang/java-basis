package zl.reflect;

import java.lang.reflect.Method;

/**
 * @author zlCalma
 * @date 2019/1/4 21:51.
 */
public class MethodClass {

    public static void main(String[] args) throws Exception{
        //获取Class对象
        Class stuClass = Class.forName("zl.reflect.Student");

        //获取所有的公有方法
        System.out.println("***********获取所有的公有方法************");
        stuClass.getMethods();
        Method[] methodArray = stuClass.getMethods();
        for(Method m:methodArray){
            System.out.println(m);
        }

        //获取所有方法，包括私有的
        System.out.println("************获取所有方法，包括私有的");
        methodArray = stuClass.getDeclaredMethods();
        for(Method m:methodArray){
            System.out.println(m);
        }

        System.out.println("************获取公有的show1()方法*************");
        Method m = stuClass.getMethod("show1", String.class);
        System.out.println(m);
        //实例化一个Student对象
        Object obj = stuClass.getConstructor().newInstance();
        m.invoke(obj,"刘德华");

        System.out.println("****************获取私有的show4()方法*************");
        m = stuClass.getDeclaredMethod("show4", int.class);
        System.out.println(m);
        m.setAccessible(true);
        Object result = m.invoke(obj,20);
        System.out.println("返回值：" + result);
    }

}
