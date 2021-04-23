import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

class Solution {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s == "") {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        // 条件：
        // 1.没有重复的字符
        // 2.最长的子串
        // 3.长度<1的返回 0
        // 4.长度==1的返回 1
        // 5.子串中遍历的时候只要循环到子串中含有的字符则命中一个子串
        char[] charArray = s.toCharArray();
        Set<Character> subString = new LinkedHashSet();
        String target = "";
        String nextTarget = "";
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (subString.add(c)) {
                nextTarget += c;
            } else {
                if (target.length() < nextTarget.length()) {
                    target = nextTarget;
                    subString.clear();
                    subString.add(c);
                    nextTarget = target.substring(target.indexOf(c + "") + 1) + c;
                }
            }
        }

        System.out.println("nextTarget: " + nextTarget + " , target: " + target);
        if (target.length() < nextTarget.length()) {
            return nextTarget.length();
        } else
            return target.length();
    }

    public static void main(String[] args) {
        lengthOfLongestSubstring("abcabcbb");
    }
}