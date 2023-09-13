package leetcode.s0003;


import java.util.HashMap;

// 01:00:00
// 计算长度同一用 j - i + 1
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int maxSize = 0;
        int point1 = 0;
        int point2 = 0;
        while (point2 < s.length()) {
            char c = s.charAt(point2);
            Integer index = map.get(c);
            if (index != null && index >= point1 && index <= point2) {
                point1 = index + 1;
            } else {
                maxSize = (point2 - point1 + 1) > maxSize ? (point2 - point1 + 1) : maxSize;
            }
            map.put(c, point2);
            point2++;
        }
        return maxSize;
    }
}