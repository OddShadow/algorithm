package learn.intermediate.class05;

/*
    给定一个数组 arr，如果通过调整可以做到 arr 中任意两个相邻的数字相乘是 4 的倍数
    返回 true 如果不能返回 false
 */
public class Problem03_NearMultiple4Times {
    
    public static boolean nearMultiple4Times(int[] arr) {
        int fourTimes = 0; // 是4的倍数的数有多少个
        int evenExpFourTimes = 0; // 是偶数但不是4的倍数的数有多少个
        int odd = 0; // 奇数有多少个
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & 1) != 0) {
                odd++;
            } else {
                if (arr[i] % 4 == 0) {
                    fourTimes++;
                } else {
                    evenExpFourTimes++;
                }
            }
        }
        return evenExpFourTimes == 0 ? (fourTimes + 1 >= odd) : (evenExpFourTimes >= odd);
    }
    
}