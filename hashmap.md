# HashMap源码解读 #

    ArrayList 底层是数组

    LinkedList 底层是双向链表



## hashMap  ##
----------


- HashMap不是线程安全的，如果想要线程安全的HashMap，可以通过Collections类的静态方法synchronizedMap获得线程安全的HashMap。 

    Map map = Collections.synchronizedMap(new HashMap())


- 在JDK8中，当链表长度达到8，会转化成红黑树，以提升它的查询、插入效率，它实现了Map<K,V>, Cloneable, Serializable接口。

**HashMap的底层主要是基于数组和链表来实现的。**

它之所以有相当快的查询速度主要是因为它是通过计算散列码来决定存储的位置。 HashMap中主要是通过key的hashCode来计算hash值的，只要hashCode相同，计算出来的hash值就一样。如果存储的对象对多了，就有可能不同的对象所算出来的hash值是相同的，这就出现了所谓的**hash冲突**。学过数据结构的同学都知道，解决hash冲突的方法有很多，HashMap底层是**通过链表来解决hash冲突的**。

## put方法 执行逻辑 ##


1.  根据key计算当前Node的hash值，用于定位对象在HashMap数组的哪个节点。 
2.  判断table有没有初始化，如果没有初始化，则调用resize（）方法为table初始化容量，以及threshold的值。 
3.  根据hash值定位该key 对应的数组索引，如果对应的数组索引位置无值，则调用newNode（）方法，为该索引创建Node节点 。
4.  如果根据hash值定位的数组索引有Node，并且Node中的key和需要新增的key相等，则将对应的value值更新。 
5.  如果在已有的table中根据hash找到Node，其中Node中的hash值和新增的hash相等，但是key值不相等的，那么创建新的Node，放到当前已存在的Node的链表尾部。 如果当前Node的长度大于8,则调用treeifyBin（）方法扩大table数组的容量，或者将当前索引的所有Node节点变成TreeNode节点，变成TreeNode节点的原因是由于TreeNode节点组成的链表索引元素会快很多。 
6.  将当前的key-value 数量标识size自增，然后和threshold对比，如果大于threshold的值，则调用resize（）方法，扩大当前HashMap对象的存储容量。 
7.  返回oldValue或者null。 put 方法比较经常使用的方法，主要功能是为HashMap对象添加一个Node 节点，如果Node存在则更新Node里面的内容。

上面调用到了一个resize方法， 我们来看看这个方法里面做了什么，实现逻辑如下： 
1. 如果当前数组为空，则初始化当前数组 。
2. 如果当前table数组不为空，则将当前的table数组扩大两倍，同时将阈值（threshold）扩大两倍 数组长度和阈值扩大成两倍之后，将之前table数组中的值全部放到新的table中去