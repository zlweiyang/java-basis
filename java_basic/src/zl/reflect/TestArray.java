package zl.reflect;

import java.lang.reflect.Array;

/**
 * @author zlCalma
 * @date 2019/1/4 22:37.
 */
public class TestArray {
    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName("java.lang.String");
        Object array = Array.newInstance(clazz,25);

        //往数组中添加内容

        Array.set(array,0,"hello");
        Array.set(array,1,"Java");
        Array.set(array,2,"fuck");

        //获取某一项的内容
        System.out.println(Array.get(array,1));

    }
}
