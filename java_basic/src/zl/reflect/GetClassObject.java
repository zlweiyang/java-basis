package zl.reflect;

/**
 * @author zlCalma
 * @date 2019/1/4 15:09.
 */
public class GetClassObject {

    public static void main(String[] args) {

        //1.通过new产生一个Student对象和一个Class对象
        Student stu1 = new Student();
        Class stuClass = stu1.getClass();
        System.out.println(stuClass.getName());

        //2.直接获取一个Class对象
        Class stuClass2 = Student.class;
        System.out.println(stuClass == stuClass2);

        //通过Class的静态方法forName获取Class的对象
        try {
            Class stuClass3 = Class.forName("zl.reflect.Student");
            System.out.println(stuClass2 == stuClass3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
