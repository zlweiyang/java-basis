package zl.reflect;

/**
 * @author zlCalma
 * @date 2019/1/4 15:06.
 */
public class Student {

    private String name;
    private int age;
    private boolean n;
    private char sex;

    public Student(char sex) {
        //this.sex = sex;
        System.out.println("性别："+sex);
    }
    //    public Student(String str) {
//        //this.str = str;
//        System.out.println("构造方法s=" + str);
//    }

    //无参构造方法
    public Student() {
        System.out.println("公有无参构造方法");
    }

    //有一个参数构造方法
    public Student(String name) {
        //this.str = str;
        System.out.println("姓名：" + name);
    }
    //有多个参数的构造方法
    public Student(String name, int age) {
        //this.name = name;
        //this.age = age;
        System.out.println("姓名："+name+"年龄："+age);
    }
    //受保护的构造方法
    protected Student(boolean n) {
        //this.n = n;
        System.out.println("受保护的构造方法n="+n);
    }
    //私有构造方法
    private Student(int age) {
        //this.age = age;
        System.out.println("私有构造方法 年龄："+age);
    }

    public void show1(String s){
        System.out.println("调用了公有的String参数的show1():s= "+s);
    }

    public void show2(){
        System.out.println("调用了受保护的无参的show2()");
    }
    public void show3(){
        System.out.println("调用了默认的无参show3()");
    }
    private String show4(int age){
        System.out.println("调用了私有的，并且有返回值的,int参数的show4():age="+age);
        return "abcd";
    }

    public static void main(String[] args) {
        System.out.println("main方法执行了。。。。");
    }

}
