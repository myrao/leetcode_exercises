\---

title: '算法日记：LeetCode#257.二叉树的所有路径'

date: 2021-04-27 22:47:00

tags: 树, 二叉树

\---

### 前言

未来很长一段时间里都会更新这个算法日记系列，一是为了自己刷题巩固基础为进阶做准备，二是刷了部分题以后发现最好的学习理解方式是记录并把思路给别人说清楚。

### 题目描述

#### [257. 二叉树的所有路径](https://leetcode-cn.com/problems/binary-tree-paths/)

难度 简单

给定一个二叉树，返回所有从根节点到叶子节点的路径。

**说明:** 叶子节点是指没有子节点的节点。

**示例:**

```java
输入:

   1
 /   \
2     3
 \
  5

输出: ["1->2->5", "1->3"]

解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
```

### 分析&题解

实际上是树的层序遍历，遍历过程中对 Node.left/right 分别进行 path 的记录。

可以用递归，也可以使用迭代，下面是迭代实现：

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList();
        if (root == null) return paths;
        LinkedList<TreeNode> nodeQueue = new LinkedList();
        LinkedList<String> pathQueue = new LinkedList();
        pathQueue.add(String.valueOf(root.val));
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.removeFirst();
            String path = pathQueue.removeFirst();
            if (node.left == null && node.right == null) {
                paths.add(path);
            } else {
                if (node.left != null) {
                    nodeQueue.add(node.left);
                    pathQueue.add((path + "->" + node.left.val));
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                    pathQueue.add((path + "->" + node.right.val));
                }
            }
        }
        return paths;   
    }
}
```

- 时间复杂度： O(N^2)
- 空间复杂度： O(N^2)

### 相关题型

[129. 求根节点到叶节点数字之和](https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/)