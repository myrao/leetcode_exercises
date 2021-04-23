### 前言
未来很长一段时间里都会更新这个算法日记系列，一是为了自己刷题巩固基础为进阶做准备，二是刷了部分题以后发现最好的学习理解方式是记录并把思路给别人说清楚。


### 题目描述
#### [1. 两数之和](https://leetcode-cn.com/problems/two-sum/)

难度 简单

给定一个整数数组 `nums` 和一个整数目标值 `target`，请你在该数组中找出 **和为目标值** 的那 **两个** 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

**示例 1：**

```
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
```

**示例 2：**

```
输入：nums = [3,2,4], target = 6
输出：[1,2]
```

**示例 3：**

```
输入：nums = [3,3], target = 6
输出：[0,1]
```

**提示：**

- `2 <= nums.length <= 103`
- `-109 <= nums[i] <= 109`
- `-109 <= target <= 109`
- **只会存在一个有效答案**

### 分析&题解

这道题是无数想学习刷题的人梦开始和结束的地方。

#### **方法一：暴力枚举**

```java
public class Solution {
     public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) return nums;
        // 暴力枚举：
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }
}
```

- 时间复杂度： O(N²)
- 空间复杂度： O(1)

#### **方法二：哈希表**

```java
public class Solution {
     public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) return nums;
        // 哈希表
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap();
        for (int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            if (map.containsKey(key)) {
                return new int[]{map.get(key), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
```

- 时间复杂度：O(N)
- 空间复杂度：O(N)

