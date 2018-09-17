# Java内部类 #

1. 为什么使用内部类？
《Java编程思想》--使用内部类最吸引人的原因：
每个内部类都能独立的继承一个（接口的）实现。所以无论外围类是否继承了某个（接口的）实现，对于内部类都没有影响。

2. 使用内部类的优点

    使用内部类的最大优点就在于内部类能否解决多重继承的问题

 1）内部类可以用多个实例，每个实例都有自己的状态信息，并且与其他外围对象的信息相互独立。
 2）在单个外围类中，可以让多个内部类以不同的方式实现同一个接口，或者继承同一类。
 3）创建内部类对象的时刻并不依赖外部类对象的创建。
 4）内部类并没有令人迷惑的“is-a”关系，他就是一个独立的实体。
 5) 内部类提供了更好的封装，除了该外围类，其他类都不能访问。

代码实例：
    public class OuterClass {
    
    private String name;
    private int age;
    
    public String getName() {
    return name;
    }
    
    public void setName(String name) {
    this.name = name;
    }
    
    public int getAge() {
    return age;
    }
    
    public void setAge(int age) {
    this.age = age;
    }
    
    public class InnerClass{
    public InnerClass(){
    name = "chenssy";
    age = 23;
    }
    
    public void display(){
    System.out.println("name:" + getName() + " ;age" + getAge());
    }
    }
    public static void main(String[] args){
    OuterClass outerClass = new OuterClass();
    OuterClass.InnerClass innerClass = outerClass.new InnerClass();
    innerClass.display();
    }
    }
   从上面程序可以发现尽管外部类属性是private，内部类可以访问，主要是因为在内部类对象时，内部类对象必定会捕获一个指向那个外部类对象的引用。
   
   引用内部类需要指明这个对象类型：外部类的类名.内部类类名。同时创建内部类对象时必须引用外部类的对象通过.new来创建内部类：
    
   `OuterClass.InnerClass innerClass = outerClass.new InnerClass();`

如果需要生成对外部类对象的引用，可以使用OuterClassName.this。

    public class OuterClass2 {
    public void display(){
    System.out.println("OuterClass...");
    }
    public class InnerClass{
    public OuterClass2 getOuterClass(){
    return OuterClass2.this;
    }
    }
    
    public static void main(String[] args){
    OuterClass2 outerClass = new OuterClass2();
    OuterClass2.InnerClass innerClass = outerClass.new InnerClass();
    innerClass.getOuterClass().display();
    }
    }
   
内部类实际上是一个编译时的概念，编译成功后，内部类与外部类属于两个完全不同的类，会生成两个class文件:OuterClass.class和OuterClass$InnerClass.class。

3.Java中内部类主要分为成员内部类、局部内部类、匿名内部类、静态内部类。

1)成员内部类
成员内部类是最普通的内部类，它是外围类的一个成员，它可以无限制访问外部类的所有成员属性和方法。外围类要访问内部类的的成员属性和方法则需要通过内部类实例来访问。

注意:
1.成员内部类中不能存在任何static的变量和方法；
2.成员内部类是依附于外围内的，所以只有先创建外围类才能创建内部类。

代码实例：

    public class OuterClass3 {
    
    private String str;
    
    public void outerDisplay(){
    System.out.println("outerClass...");
    }
    
    public class InnerClass{
    public void innerDisplay(){
    //使用外围类的属性
    str = "hello...";
    System.out.println(str);
    outerDisplay();
    }
    }
    //推荐使用getxxx()来获取成员内部类，尤其在该内部类的构造函数无参数时
    public InnerClass getInnerClass(){
    return new InnerClass();
    }
    
    public static void main(String[] args){
    OuterClass3 outer  = new OuterClass3();
    OuterClass3.InnerClass inner = outer.getInnerClass();
    inner.innerDisplay();
    
    }
    }
    
2）局部内部类

嵌套于方法或作用域内，局部内部类的和成员内部类一样被编译。只是它的作用域发生了改变。它只能在该方法和属性中被使用，除了该方法和属性就会失效。

**定义在方法中：**

    public class Parcel5 {
    public Destionation destionation(String str){
    class PDestionation implements Destionation{
    private String label;
    private PDestionation(String whereTo){
    label = whereTo;
    }
    public String readLabel(){
    return label;
    }
    }
    return new PDestionation(str);
    }
    
    public static void main(String[] args) {
    Parcel5 parcel5 = new Parcel5();
    Destionation d = parcel5.destionation("chenssy");
    }
    }

**定义在作用域中**

    public class Parcel6 {
    private void internalTracking(boolean b){
    if(b){
    class TrackingSlip{
    private String id;
    TrackingSlip(String s) {
    id = s;
    }
    String getSlip(){
    return id;
    }
    }
    TrackingSlip ts = new TrackingSlip("chenssy");
    String string = ts.getSlip();
    }
    }
    
    public void track(){
    internalTracking(true);
    }
    
    public static void main(String[] args) {
    Parcel6 parcel6 = new Parcel6();
    parcel6.track();
    }
    }
3）匿名内部类
    public class OuterClass4 {
    
    public InnerClass getInnerClass(final int num, String str2){
    return new InnerClass(){
    int number = num + 3;
    public int getNumber(){
    return number;
    }
    };
    }
    
    interface InnerClass{
    int getNumber();
    }
    
    public static void main(String[] args){
    OuterClass4 out = new OuterClass4();
    InnerClass inner = out.getInnerClass(2,"hello");
    System.out.println(inner.getNumber());
    }
    }
    
   1.匿名内部类是没有访问修饰符的。
   2.new匿名内部类，这个类首先是要存在的。如果将接口InnerClass接口注释掉，就会出错。
   3.当匿名内部类所在方法的形参需要被匿名内部类使用时，那么这个形参必须为final。
   4.匿名内部类是没有构造方法的
4）静态内部类
使用static修饰的的内部类称之为静态内部类，非静态内部类在编译完成之后会隐含的保存一个引用，该引用指向创建它的外围类，但是静态内部类却没有这个引用。

1.它的创建是不需要依赖于外围类的。
2.它不能使用任何外围类的非static成员变量和方法。

    public class OuterClass5 {
    
    private String sex;
    public static String name = "hello";
    
    /**
     * 静态内部类
     */
    static class InnerClass1{
    //在静态内部类中可以存放静态变量
    
    public static String _name1 = "hello";
    
    public void display(){
    /**
     * 静态内部类只能访问外围类的成员变量和方法
     * 不能访问外围类的非静态成员变量和方法
     */
    
    System.out.println("OutClass name :" + name);
    }
    }
    /**
     * 非静态成员内部类
     */
    class InnerClass2{
    //非静态内部类不能存再静态成员
    
    public String _name2 = "hello";
    
    public void display(){
    System.out.println("OuterClass name:" + name);
    }
    }
    
    /**
     * 外围类方法
     */
    
    public void display(){
    //外围类访问静态内部类：内部类
    System.out.println(InnerClass1._name1);
    //静态内部类 可以直接创建实例不要依赖于外围类
    new InnerClass1().display();
    
    //非静态内部类对象的创建需要依赖于外围类
    OuterClass5.InnerClass2 inner2 = new OuterClass5().new InnerClass2();
    //非静态内部类成员使用非静态内部类的实例
    
    System.out.println(inner2._name2);
    inner2.display();
    }
    
    public static void main(String[] args){
    OuterClass5 outer = new OuterClass5();
    outer.display();
    }
    }
