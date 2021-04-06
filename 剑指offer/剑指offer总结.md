## 03 数组中重复的数字

在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

```java
方法一：遍历插入
    class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int repeat = -1;
        for (int num : nums) {
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }
}
```

## 04二维数组中的查找

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

![image-20210405213201700](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20210405213201700.png)

解题思路：

![image-20210405215453934](C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20210405215453934.png)

```java
//从右上角找,双指针
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        //二维数组的判空写法
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int i=0,j=matrix[0].length-1;
        while(j>=0&&i<matrix.length){
            int num = matrix[i][j];
            if(num==target)return true;
            else if(num < target) i++;
            else j--;
        }
        return false;
    }
}
```

## 05 替换空格

请实现一个函数，把字符串 `s` 中的每个空格替换成"%20"。

```java
//函数 s.replace("目标字符串"，"替换的内容");
//s.toCharArray() 转换为字符数组，s.charAt(i)遍历
//可以创建3倍大小的字符数组，最后  String newStr = new String(array字符数组, 0, size);复制就行
class Solution {
    public String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();
        for(Character c : s.toCharArray())
        {
            if(c == ' ') res.append("%20");
            else res.append(c);
        }
        return res.toString();
    }
}
```

## 06从尾到头打印链表

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

```java
java Stack方法：
// Stack<ListNode> stack = new Stack<ListNode>();
// push()栈顶插入、pop()栈顶删除并返回，peek()栈顶返回不删除、size()大小
//Object push（Object element）：将元素推送到堆栈顶部。
//Object pop（）：移除并返回堆栈的顶部元素。如果我们在调用堆栈为空时调用pop（）,则抛出’EmptyStackException’异常。
//Object peek（）：返回堆栈顶部的元素，但不删除它。
//boolean empty（）：如果堆栈顶部没有任何内容，则返回true。否则，返回false。
//int search（Object element）：确定对象是否存在于堆栈中。如果找到该元素，它将从堆栈顶部返回元素的位置。否则，它返回-1
```



```java
class Solution {
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;
    }
}
```

## 07重建二叉树

输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

![image-20210406162249040](../picture/image-20210406162249040.png)

```java
Java HashMap 操作：
//添加元素 put()
//访问元素 get(key)
//删除元素 remove(key)
//清空所有键值对 clear()
//计算大小 size()
//判断是否为空  isEmpty()
//检查 hashMap 中是否存在指定的 key 对应的映射关系。containsKey()
//检查 hashMap 中是否存在指定的 value 对应的映射关系 containsValue()
//替换 hashMap 中是指定的 key 对应的 value。如果 hashMap 中是否存在指定的 value 对应的映射关系返回 true，否则返回 false。
//replace(K key, V oldValue, V newValue)
//遍历
HashMap<Integer, String> Sites = new HashMap<Integer, String>();
        // 添加键值对
        Sites.put(1, "Google");
        Sites.put(2, "Runoob");
        // 输出 key 和 value
        for (Integer i : Sites.keySet()) {
            System.out.println("key: " + i + " value: " + Sites.get(i));
        }
        // 返回所有 value 值
        for(String value: Sites.values()) {
          // 输出每一个value
          System.out.print(value + ", ");
        }
    }
```



```java
//方法一 递归
//哈希表的初始化 
class Solution {
    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);
        
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }
}
```

## 09用两个栈实现队列

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

```java
//双端队列--Deque 
```

![image-20210406165444495](../picture/image-20210406165444495.png)

```java
//判断栈是否为空 不能 == null 要用isEmpty()
//官方用的是双端链表
class CQueue {
    Deque<Integer> stack1;
    Deque<Integer> stack2;
    public CQueue() {
        stack1 = new LinkedList<Integer>();
        stack2 = new LinkedList<Integer>();
    }
    public void appendTail(int value) {
        stack1.push(value);
    }
    public int deleteHead() {
        // 如果第二个栈为空
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        } 
        if (stack2.isEmpty()) {
            return -1;
        } else {
            int deleteItem = stack2.pop();
            return deleteItem;
        }
    }
}
//栈方法
class CQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public CQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }
    
    public void appendTail(int value) {
        stack1.push(value);
    }
    
    public int deleteHead() {
        int res = 0;
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if(stack2.isEmpty()){
             return -1;
        }else res = stack2.pop();

        return res;
    }
}
```

## 10 _1斐波那契数列

写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.

```java
//递归方法 出现 超出时间限制错误
class Solution {
    public int fib(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        return (fib(n-1)+fib(n-2))%1000000007;
    }
}
//动态规划
// 动态规划关键思路：dp 列表第 i 项只与第 i-1 和第 i-2 项有关
//dp[i + 1] = dp[i] + dp[i - 1]dp[i+1]=dp[i]+dp[i−1]
class Solution {
    public int fib(int n) {
        int a = 0, b = 1, sum;
        for(int i = 0; i < n; i++){
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
```

## 10_2青蛙跳台阶问题

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 `n` 级的台阶总共有多少种跳法。答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

动态规划思路：

![image-20210406173652537](../picture/image-20210406173652537.png)

```java
class Solution {
    public int numWays(int n) {
        if(n==0) return 1;
        int a=1,b=2,sum=0;
        for(int i=2;i<=n;i++){
            sum=(a+b)%1000000007;
            a=b;
            b=sum;
        }
        return a;
    }
}
```



