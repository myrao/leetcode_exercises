---
title: 算法日记：LeetCode#129. 求根节点到叶节点数字之和
date: 2021-04-27 22:54:47
tags: 树, BFS
---

### 前言

未来很长一段时间里都会更新这个算法日记系列，一是为了自己刷题巩固基础为进阶做准备，二是刷了部分题以后发现最好的学习理解方式是记录并把思路给别人说清楚。

### 题目描述

#### [129. 求根节点到叶节点数字之和](https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/)

难度中等350收藏分享切换为英文接收动态反馈

给你一个二叉树的根节点   

每条从根节点到叶节点的路径都代表一个数字：

- 例如，从根节点到叶节点的路径 `1 -> 2 -> 3` 表示数字 `123` 。

计算从根节点到叶节点生成的 **所有数字之和** 。

**叶节点** 是指没有子节点的节点。

 

**示例 1：**

![img](/Users/rmy/work/images/num1tree.jpg)

```
输入：root = [1,2,3]
输出：25
解释：
从根到叶子节点路径 1->2 代表数字 12
从根到叶子节点路径 1->3 代表数字 13
因此，数字总和 = 12 + 13 = 25
```

**示例 2：**

![img](/Users/rmy/work/images/num2tree.jpg)

```
输入：root = [4,9,0,5,1]
输出：1026
解释：
从根到叶子节点路径 4->9->5 代表数字 495
从根到叶子节点路径 4->9->1 代表数字 491
从根到叶子节点路径 4->0 代表数字 40
因此，数字总和 = 495 + 491 + 40 = 1026
```

 

**提示：**

- 树中节点的数目在范围 `[1, 1000]` 内
- `0 <= Node.val <= 9`
- 树的深度不超过 `10`

### 分析&题解

1. 实际上是树的层序遍历，遍历过程中对 Node.left/right 分别进行 path 的记录；
2. 获取所有 path 后，对 path 的数字解析求和即可。

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
    public int sumNumbers(TreeNode root) {
        List<String> paths = new ArrayList();
        if (root == null) return -1;

        LinkedList<TreeNode> nodeQueue = new LinkedList();
        LinkedList<String> pathQueue = new LinkedList(); 
        
        nodeQueue.add(root);
        pathQueue.add(String.valueOf(root.val));
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.removeFirst();
            String path = pathQueue.removeFirst();
            if (node.left == null && node.right == null) {
                paths.add(path);
            } else {
                if (node.left != null) {
                    nodeQueue.add(node.left);
                    pathQueue.add(path + node.left.val);
                } 
                if (node.right != null) {
                    nodeQueue.add(node.right);
                    pathQueue.add(path + node.right.val);
                }
            }
        }

        int ans = 0;
        for (String numStr : paths) {
            ans += Integer.parseInt(numStr);
        }
        return ans;
    }
}
```

- 时间复杂度： O(N^2)
- 空间复杂度： O(N^2)

### 相关题型

[257. 二叉树的所有路径](https://leetcode-cn.com/problems/binary-tree-paths/)

