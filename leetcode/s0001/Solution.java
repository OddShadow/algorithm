package leetcode.s0001;

import java.util.HashMap;

// 11:26
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return new int[]{0, 0};
        }
        HashMap<Integer, Integer> map = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            int j = target - nums[i];
            boolean flag = map.containsKey(j);
            if (flag) {
                return new int[]{map.get(j), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{0, 0};
    }
}