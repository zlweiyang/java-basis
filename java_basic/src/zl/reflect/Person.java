package zl.reflect;

/**
 * @author zlCalma
 * @date 2019/1/4 17:27.
 */
public class Person {

    public Person() {
    }

    public String name;
    protected int age;
    char sex;
    private String phoneNum;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
