### 前言

未来很长一段时间里都会更新这个算法日记系列，一是为了自己刷题巩固基础为进阶做准备，二是刷了部分题以后发现最好的学习理解方式是记录并把思路给别人说清楚。


### 题目描述

#### [113. 路径总和 II](https://leetcode-cn.com/problems/path-sum-ii/)

难度中等473收藏分享切换为英文接收动态反馈

给你二叉树的根节点 `root` 和一个整数目标和 `targetSum` ，找出所有 **从根节点到叶子节点** 路径总和等于给定目标和的路径。

**叶子节点** 是指没有子节点的节点。

 

**示例 1：**

![img](/Users/rmy/work/images/pathsumii1.jpg)

```
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：[[5,4,11,2],[5,8,4,5]]
```

**示例 2：**

![img](/Users/rmy/work/images/pathsum2.jpg)

```
输入：root = [1,2,3], targetSum = 5
输出：[]
```

**示例 3：**

```
输入：root = [1,2], targetSum = 0
输出：[]
```

 

**提示：**

- 树中节点总数在范围 `[0, 5000]` 内
- `-1000 <= Node.val <= 1000`
- `-1000 <= targetSum <= 1000`

### 分析&题解

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
    private List<List<Integer>> ans = new LinkedList();
    private LinkedList<Integer> path = new LinkedList();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        recur(root, targetSum);
        return ans;
    }

    public void recur(TreeNode root, int target) {
        if (root == null) return;
        path.add(root.val);
        target =  target - root.val;
        if (target == 0 && root.left == null && root.right == null) {
            ans.add(new LinkedList(path));
        }

        recur(root.left, target);
        recur(root.right, target);
        
        path.removeLast();
    }
}
```

