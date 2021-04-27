\---

title: '算法日记：LeetCode#5.最长回文子串'

date: 2021-04-27 22:47:00

tags: 字符串

\---

### 前言

未来很长一段时间里都会更新这个算法日记系列，一是为了自己刷题巩固基础为进阶做准备，二是刷了部分题以后发现最好的学习理解方式是记录并把思路给别人说清楚。

### 题目描述

#### [5. 最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)

难度 中等

给你一个字符串 `s`，找到 `s` 中最长的回文子串。

 

**示例 1：**

```
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
```

**示例 2：**

```
输入：s = "cbbd"
输出："bb"
```

**示例 3：**

```
输入：s = "a"
输出："a"
```

**示例 4：**

```
输入：s = "ac"
输出："a"
```

 

**提示：**

- `1 <= s.length <= 1000`
- `s` 仅由数字和英文字母（大写和/或小写）组成

### 分析&题解

之前面试遇到这道题，没解出来…😭

官方题解有三种解法：

- 暴力
- 中心扩展法
- 马拉车

以下使用中心扩展法实现：

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
          	// 字符串长度可能是奇数或偶数
            int len1 = helper(s, i, i);
            int len2 = helper(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
```

- 时间复杂度： O(N^2)
- 空间复杂度： O(N)
