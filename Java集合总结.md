# Java集合框架总结及源码分析 #

## ArrayList ##

    public class ArrayList<E> extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable
ArrayList继承AbstractList抽象父类，实现类List接口，RandomAceess(可随机访问),Cloneable(可拷贝)、Serializable(可序列化)

ArrayList是List接口的可变数字组的实现，允许包括null在内的所有元素。每个ArrayList实例都有一个容量，随着向ArrayList中添加元素，其容量也自动增长。ArrayList实现是不同步的。
## ArrayList源码分析 ##

**其底层采用数组保存所有元素**
 

    transient Object[] elementData; 

**构造方法**

ArrayList提供三种方式的构造器

1.构造一个默认初始容量为10的空列表


       /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
2.构造一个指定初始化容量的空列表

    public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
    this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
    this.elementData = EMPTY_ELEMENTDATA;
    } else {
    throw new IllegalArgumentException("Illegal Capacity: "+
       initialCapacity);
    }
    }
3.构造一个包含指定collection元素的列表

    public ArrayList(Collection<? extends E> c) {
    elementData = c.toArray();
    if ((size = elementData.length) != 0) {
    if (elementData.getClass() != Object[].class)
    elementData = Arrays.copyOf(elementData, size, Object[].class);
    } else {
    this.elementData = EMPTY_ELEMENTDATA;
    }
    }
## ArrayList的常用方法 ##

*替换指定位置的元素*
public E set(int index, E element)

*将元素添加到列表的尾部*
public boolean add(E e) 

*将元素添加到指定位置*
public void add(int index, E element)

*获取指定位置的元素*
public E get(int index)

*删除指定位置元素*
public E remove(int index)

*移除首次出现的元素*
public boolean remove(Object o)

**注意：从数组移除元素的操作，也会导致被移除的元素后面所有元素向左移动一个位置。
**
## ArrayList容量调整 ##
在向数组中添加元素时，都要检查添加后元素个数是否超出当前数组的长度，如果超出需要调用ensureCapacity来实现扩容。

    public void ensureCapacity(int minCapacity) {  
    modCount++;  
    int oldCapacity = elementData.length;  
    if (minCapacity > oldCapacity) {  
    Object oldData[] = elementData;  
    int newCapacity = (oldCapacity * 3)/2 + 1;  
    if (newCapacity < minCapacity)  
    newCapacity = minCapacity;  
      elementData = Arrays.copyOf(elementData, newCapacity);  
    }  
    }  

数组扩容的时候，会将老数组的元素重新拷贝一份到新的数组中，每次数组容量的增长为其原容量的1.5倍,该操作代价大。

# LinkedList #
LinkedList底层是采用双向链表结构，其实现不同步，允许包括null在内的所有元素
其查找分为两半查找。

    public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
LinkedList继承抽象类AbstractSequentialList，实现List接口，双端队列接口(Deque),可复制(Cloneable)，可序列化(Java提供的一种保存对象的机制，主要用于讲对象保存到文件或者数据库)

## LinkedList的构造方法 ##

    public LinkedList() {
    }

     public LinkedList(Collection<? extends E> c) {
    this();
    addAll(c);
    }
调用无参构造，将Collection元素全部添加到列表。
## LinkedList常用方法 ##

*在链表尾部添加元素*
public boolean add(E e)

node函数在查找时，结点在前的前半段从头开始遍历，在后半段则从尾开始遍历。
*public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }*

# HashMap #

HashMap是基于哈希表的Map接口的非同步实现，允许使用null值和null键。但此类不保证映射的顺序。
HashMap不是线程安全的，如果想要线程安全的HashMap，可以通过Collections类的静态方法synchronizedMap获得线程安全的HashMap。

Map map = Collections.synchronizedMap(new HashMap())

在JDK8中，当链表长度达到8，会转化成红黑树，以提升它的查询、插入效率，它实现了Map<K,V>, Cloneable, Serializable接口。

HashMap的底层主要是基于数组和链表来实现的，当链表长度大于一定阈值时，链表转变为红黑树。

它之所以有相当快的查询速度主要是因为它是通过计算散列码来决定存储的位置。 HashMap中主要是通过key的hashCode来计算hash值的，只要hashCode相同，计算出来的hash值就一样。如果存储的对象对多了，就有可能不同的对象所算出来的hash值是相同的，这就出现了所谓的hash冲突。学过数据结构的同学都知道，解决hash冲突的方法有很多，HashMap底层是通过链表来解决hash冲突的。

put方法 执行逻辑
1. 根据key计算当前Node的hash值，用于定位对象在HashMap数组的哪个节点。

2. 判断table有没有初始化，如果没有初始化，则调用resize（）方法为table初始化容量，以及threshold的值。

3. 根据hash值定位该key 对应的数组索引，如果对应的数组索引位置无值，则调用newNode（）方法，为该索引创建Node节点 。

4. 如果根据hash值定位的数组索引有Node，并且Node中的key和需要新增的key相等，则将对应的value值更新。

5. 如果在已有的table中根据hash找到Node，其中Node中的hash值和新增的hash相等，但是key值不相等的，那么创建新的Node，放到当前已存在的Node的链表尾部。 如果当前Node的长度大于8,则调用treeifyBin（）方法扩大table数组的容量，或者将当前索引的所有Node节点变成TreeNode节点，变成TreeNode节点的原因是由于TreeNode节点组成的链表索引元素会快很多。

6. 将当前的key-value 数量标识size自增，然后和threshold对比，如果大于threshold的值，则调用resize（）方法，扩大当前HashMap对象的存储容量。

7.  返回oldValue或者null。 put 方法比较经常使用的方法，主要功能是为HashMap对象添加一个Node 节点，如果Node存在则更新Node里面的内容。
上面调用到了一个resize方法， 我们来看看这个方法里面做了什么，实现逻辑如下：

如果当前数组为空，则初始化当前数组 。
如果当前table数组不为空，则将当前的table数组扩大两倍，同时将阈值（threshold）扩大两倍 数组长度和阈值扩大成两倍之后，将之前table数组中的值全部放到新的table中去

# LinkedHashMap #
LinkedHashMap继承于HashMap，底层使用哈希表和双向链表来保存元素，且非同步，允许使用null值和null键

LinkedHashMap与HashMap的不同在于维护着一个运行于所有条目的双重链接列表。

# Hashtable #
Hashtable是基于哈希表的Map接口的**同步实现**，**不允许使用null值和null键。**
底层使用数组来实现，数组中的每一项是个但列表，即数组和链表的结合体。

Synchronized是针对整张Hash表的，即每次锁住整张表让线程独占。

# ConcurrentHashMap #
- 允许多个修改操作并发进行。
- 使用多个锁来控制对hash表的不同段进行修改，每个段其实就是一个小的hashtable，每个段都有自己的锁，只要多个并发发生在不同的段上，就可以并发进行。
- ConcurrentHashMap在底层将key-value当做一个整体进行处理，这个整体就是一个Entry对象。Hashtable底层采用一个Entry[]数组来保存一个键值对，当需要存储一个Entry对象时，会根据key的hash算法来解决其在数组中的位置，再根据equals方法决定其在链表中的存储位置，当需要一个Entry时，也会根据key的hash算法找到其在数组中的存储位置，再根据equals方法从该位置上的链表中取出该Entry
- ConcurrentHashMap完全允许多个读操作并发进行。

# HashSet #
HashSet实现Set接口，有哈希表（实际上是HashMap实例）支持。不保证Set顺序，允许null元素。
对于HashSet中保存的对象，需要正确重写equals和hashCode方法。保证放入对象的唯一性。

# LinkedHashSet #
LinkedHashSet与HashSet的不同在与其维护着一个运行于所有条目的双重链接列表，并定义了迭代顺序，该实现不同步。

LinkedHashset继承于HashSet，又基于LinkedHashMap来实现。