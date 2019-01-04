package zl.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author zlCalma
 * @date 2019/1/4 15:39.
 */
public class Constructors {

    public static void main(String[] args) throws Exception {

        //1.加载class对象，静态方法最常用
        Class clazz = Class.forName("zl.reflect.Student");

        //2.获取所有公有构造方法
        System.out.println("***所有公有构造方法*******");
        Constructor[] conArray = clazz.getConstructors();
        for(Constructor c:conArray){
            System.out.println(c);
        }

        System.out.println("********所有构造方法包含私有，公有。受保护");
        conArray =  clazz.getDeclaredConstructors();
        for(Constructor c:conArray){
            System.out.println(c);
        }

        System.out.println("***********获取公有无参构造方法");
        Constructor con = clazz.getConstructor();
        System.out.println(con);

        Constructor con2 = clazz.getConstructor(String.class);
        //Object object = con.newInstance("zlcalma");

        //调用构造方法
        Object obj = con.newInstance();

        System.out.println("获取私有构造方法，并调用");
        con = clazz.getDeclaredConstructor(char.class);
        System.out.println(con);

        con.setAccessible(true);
        obj = con.newInstance('男');
        obj = con2.newInstance("zlcalma");

    }
}
