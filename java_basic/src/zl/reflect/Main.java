package zl.reflect;

import java.lang.reflect.Method;

/**
 * @author zlCalma
 * @date 2019/1/4 22:12.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("zl.reflect.Student");

            //获取main方法,第一参数是方法名，第二个参数是方法形参的方法类型，第二个参数是String数组JDK1.4之前是数组，jdk1.5之后是可变参数
            Method methodMain = clazz.getMethod("main",String[].class);
            //调用main方法,第一个参数，对象类型，因为main方法是static所以可以使用null
            //methodMain.invoke(null,new String[]{"a","b","c"});

            //由于将new String[]{"a","b","c"}拆成三个对象，所以需要进行强制转化，主要有两种方法
            //方式1
            methodMain.invoke(null,new Object[]{new String[]{"a","b","c"}});
            //方式2
            methodMain.invoke(null,(Object)new String[]{"a","b","c"});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
