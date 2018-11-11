# java
##本仓库主要用于积累java的基础知识。
主要目录包括：

1.关键字：static,final。

static一般是不可以修饰类的，只能修饰内部类，1.只有静态内部类才能定义静态成员和方法，普通内部类不能定义静态成员变量和方法。2.非静态内部类可以随意访问外部类的成员变量与成员方法，而静态类只能访问外部类的静态成员变量和方法。3.在创建静态内部类时不需要将静态内部类的实例绑定到外部类的实例上。

2.面向对象特点（封装、继承、多态）。

3.抽象类，接口，Object类。

4.java引用数据类型及其装箱、拆箱机制。

5.集合框架以及String

#ArrayList 底层是数组

#LinkedList 底层是双向链表

#hashMap
HashMap不是线程安全的，如果想要线程安全的HashMap，可以通过Collections类的静态方法synchronizedMap获得线程安全的HashMap。
Map map = Collections.synchronizedMap(new HashMap())

在JDK8中，当链表长度达到8，会转化成红黑树，以提升它的查询、插入效率，它实现了Map<K,V>, Cloneable, Serializable接口。

HashMap的底层主要是基于数组和链表来实现的，它之所以有相当快的查询速度主要是因为它是通过计算散列码来决定存储的位置。
HashMap中主要是通过key的hashCode来计算hash值的，只要hashCode相同，计算出来的hash值就一样。如果存储的对象对多了，就有可能不同的对象所算出来的hash值是相同的，这就出现了所谓的hash冲突。学过数据结构的同学都知道，解决hash冲突的方法有很多，HashMap底层是通过链表来解决hash冲突的。

put方法
执行逻辑：
1）根据key计算当前Node的hash值，用于定位对象在HashMap数组的哪个节点。
2）判断table有没有初始化，如果没有初始化，则调用resize（）方法为table初始化容量，以及threshold的值。
3）根据hash值定位该key 对应的数组索引，如果对应的数组索引位置无值，则调用newNode（）方法，为该索引创建Node节点
4）如果根据hash值定位的数组索引有Node，并且Node中的key和需要新增的key相等，则将对应的value值更新。
5）如果在已有的table中根据hash找到Node，其中Node中的hash值和新增的hash相等，但是key值不相等的，那么创建新的Node，放到当前已存在的Node的链表尾部。
      如果当前Node的长度大于8,则调用treeifyBin（）方法扩大table数组的容量，或者将当前索引的所有Node节点变成TreeNode节点，变成TreeNode节点的原因是由于TreeNode节点组成的链表索引元素会快很多。
5）将当前的key-value 数量标识size自增，然后和threshold对比，如果大于threshold的值，则调用resize（）方法，扩大当前HashMap对象的存储容量。
6）返回oldValue或者null。
put 方法比较经常使用的方法，主要功能是为HashMap对象添加一个Node 节点，如果Node存在则更新Node里面的内容。


上面调用到了一个resize方法， 我们来看看这个方法里面做了什么，实现逻辑如下：
1）如果当前数组为空，则初始化当前数组
2）如果当前table数组不为空，则将当前的table数组扩大两倍，同时将阈值（threshold）扩大两倍
      数组长度和阈值扩大成两倍之后，将之前table数组中的值全部放到新的table中去

6.反射

7.NIO

## 8.String（字符串常量）与StringBuffer(字符串变量、线程安全)、StringBuilder(字符串变量非线程安全) ##

String类型是final类型，在堆中分配的内存地址不变，其底层是final修饰的char[]数组，数组的内存地址同样不可变。对String对象的任何改变都不影响到原对象，相关的任何改变操作都会生成新的对象。

    String str1 = "hello world"//编译器生成字符常量和符号引用，在运行时JVM会去查找常量池中是否有"hello world"。
    String str2 = new String("hello world")//通过new关键字在堆生成对象，不会检测对象是否存在。

下面代码生成10000个对象

    public class Main {
 
    public static void main(String[] args) {
        String string = "";
        for(int i=0;i<10000;i++){
            string += "hello";
        }
    }


    }
将String替换成StringBuilder就不会生成10000个对象，而是在原有对象基础上操作10000次。StringBuffer与StringBuilder区别仅仅是在类的成员方法前加入一个synchronized关键字，在多线程访问时起到保护作用。

## 9.String中的hashCode和equal ##

- String有重写Object的hascode和toString方法。
- 当equal方法被重写时，通常必须重写hashCode方法。1.两个对象相等，其hashCode必相等；2.两对象的hashCode不相等时，两对象肯定不相等；3.两对象的hashCode相等，这两个对象不一定相等。 
- 重写equals不重写hashcode会出现问题：在存储散列集合时，如果两个对象拥有不同hashCode，则在集合中将存储两个值相同的对象，从而导致混淆，所以重写equals方法时，必须重写hashCode。


## 10 Java面向对象的三大特性 ##

继承、封装、多态。

**继承**
Java中继承只能单继承，但是可以通过内部类继承其他类来实现多继承。

**封装**
封装主要是通过访问控制符保护类中的信息。

**多态**
多态一般份分为两种，一种实现重写，一种是重载。

重写：子类重写父类同名方法，重载：同一个类中同名方法可以有不同参数列表，从jvm角度看，重写又叫运行时多态。