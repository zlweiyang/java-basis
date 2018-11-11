# Java和C++的区别 #

## 一、总体区别 ##
1. Java是纯面向对象语言，所有对象都继承自java.lang.Object，C++兼容C即支持面向对象也支持面向过程。
2. Java通过虚拟机从而实现跨平台，但C++依赖于特定平台。
3. Java没有指针，它的引用可以理解为安全指针，而C++具有和C一样的指针。
4. Java支持自动垃圾回收，而C++需要手动回收。(C++11中引入智能指针，使用引用计数法进行垃圾回收)。
5. Java不支持多重继承，只能通过实现多个接口来达到相同目的，而C++支持多重继承。
6. Java内置线程支持，而C++需要依靠第三方库。
7. Java中的goto是保留字，但是不可用，C++可以使用goto。

## 二、数据类型 ##

 C++的基本数据类型：int、unsigned int、long、unsigned long、short、unsigned short、char、unsigned char、bool、float 和 double。

Java有8种数据类型：int,char,byte,short,long, float,double,boolean。

c++的char是8比特无符号整数，可以表示ASCII字符；Java的char是16比特，天生就可以表示宽字符集的字符。

C++中的long在不同系统上其长度不一样，可能是32位，也可能是64位，而Java的long表示64位整数。

## 三、基本语法 ##

C++中存在全局函数，Java中不允许，不过Java中用静态成员函数。