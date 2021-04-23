#### [剑指 Offer 09. 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

难度简单

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 `appendTail` 和 `deleteHead` ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，`deleteHead` 操作返回 -1 )

**示例 1：**

```
输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
```

**示例 2：**

```
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
```

**提示：**

- `1 <= values <= 10000`
- `最多会对 appendTail、deleteHead 进行 10000 次调用`

##### 题解：

```java
class CQueue {
    Stack<Integer> stackTail = null;
    Stack<Integer> stackHead = null;

    public CQueue() {
        stackTail = new Stack();
        stackHead = new Stack();
    }
    
    public void appendTail(int value) {
        stackTail.push(value);
    }
    
    public int deleteHead() {
        // 1. 我自己的版本，每次都要重复两个栈的完整数据操作，时间复杂度 O(2N)
        // while (!stackTail.isEmpty()) {
        //     stackHead.push(stackTail.pop());
        // }
        // int result = stackHead.pop();
        // while (!stackHead.isEmpty()) {
        //     stackTail.push(stackHead.pop());
        // }
        // return result;
        // 2. 只用判断出栈的一方
        if (stackHead.isEmpty()) {
            while (!stackTail.isEmpty()) {
            stackHead.push(stackTail.pop());
            }
        }
        // 如果经过上面的入栈还为空，则证明没有数据，否则直接弹出的值就是 head
        if (stackHead.isEmpty()) {
            return -1;
        } else {
            int result = stackHead.pop();
            return result;
        }
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
```

**执行结果：通过**

执行用时：57 ms, 在所有 Java 提交中击败了69.93%的用户

内存消耗：46.5 MB, 在所有 Java 提交中击败了78.28%的用户

---



#### [146. LRU 缓存机制](https://leetcode-cn.com/problems/lru-cache/)

难度中等

运用你所掌握的数据结构，设计和实现一个 [LRU (最近最少使用) 缓存机制](https://baike.baidu.com/item/LRU) 。

实现 `LRUCache` 类：

- `LRUCache(int capacity)` 以正整数作为容量 `capacity` 初始化 LRU 缓存
- `int get(int key)` 如果关键字 `key` 存在于缓存中，则返回关键字的值，否则返回 `-1` 。
- `void put(int key, int value)` 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

**进阶**：你是否可以在 `O(1)` 时间复杂度内完成这两种操作？

**示例：**

```java
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
```

**提示：**

- `1 <= capacity <= 3000`
- `0 <= key <= 3000`
- `0 <= value <= 104`
- 最多调用 `3 * 104` 次 `get` 和 `put`

**题解**

```java
class LRUCache {

    DLinkNode head;
    DLinkNode tail;
    LinkedHashMap<Integer, DLinkNode> cache;
    int capacity;
    int size; //没有考虑到自己维护大小的情况

    // 一开始命名就错，应该是双向链表，而不是队列
    class DLinkNode {
        int key;
        int val;
        DLinkNode prev;
        DLinkNode next;
    }

    public LRUCache(int capacity) {
        cache = new LinkedHashMap(capacity);
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkNode();
        tail = new DLinkNode();
        // 没考虑到先将哑结点进行连接成一条正常链表
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        DLinkNode node =  cache.get(key);
        if (node == null) {
            return -1;
        } 
        moveToFirst(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        DLinkNode node = cache.get(key);
        if (node != null) {
            node.val = value;
            // 没考虑到赋值后需要移动到前面
            moveToFirst(node);
        } else {
            DLinkNode newNode = new DLinkNode();
            newNode.key = key;
            newNode.val = value;
            cache.put(key, newNode);
            size++;
            addToFirst(newNode);
            // 不应该 >=，等于的时候不需要移除尾端
            if (size > capacity) {
                DLinkNode tail = removeLast();
                cache.remove(tail.key);
                size--;
            }
        }
    }

    private void addToFirst(DLinkNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // 移除尾端应该返回尾端的 node，因为 hashMap 需要同步移除对应 key 的数据
    private DLinkNode removeLast() {
        DLinkNode last = tail.prev;
        removeNode(last);
        return last;
    }
    
    // 先移除再添加到头部
    private void moveToFirst(DLinkNode node) {
        removeNode(node);
        addToFirst(node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

**执行结果**：

通过

执行用时：19 ms, 在所有 Java 提交中击败了65.45%的用户

内存消耗：46.4 MB, 在所有 Java 提交中击败了63.95%的用户