package learn.intermediate.class01;

import java.util.Arrays;
import java.util.Random;

/*
    给一个有序数组 arr，代表数轴上从左到右有 n 个点 arr[0] arr[1] arr[2] ... arr[n-1]
    给定一个正数 L，代表长度 L 的绳子，求绳子最多可以覆盖其中的几个点
 */
public class Problem01_CordCoverMaxPoint {
    
    // 方法一，让绳子头部在点上，尽量拉满
    // 时间复杂度 O(N^2)
    public static int solution1(int[] arr, int L) {
        int maxPoint = 1;
        for (int i = 0; i < arr.length; i++) {
            int currentPoint = 1;
            int point = L + arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] <= point) {
                    currentPoint++;
                }
            }
            maxPoint = Math.max(maxPoint, currentPoint);
        }
        return maxPoint;
    }
    
    // 方法二，让绳子尾部在点上，尽量拉满
    // 尾点对齐，起点，找 >= num 最左的数，这可以二分找，加速
    // 时间复杂度 O(NlogN)
    public static int solution2(int[] arr, int L) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr, i, arr[i] - L);
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }
    
    // 在arr[0..R]范围上，找满足>=value的最左位置
    public static int nearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }
    
    // 方法三，方法一的双指针实现的滑动窗口法，避免不必要的比较
    // 时间复杂度 O(N)
    public static int solution3(int[] arr, int L) {
        int maxPoint = 1;
        int i = 0;
        int j = 0;
        while (j < arr.length) {
            if (arr[j] - arr[i] <= L) {
                maxPoint = Math.max(maxPoint, j - i + 1);
            } else {
                i++;
            }
            j++;
        }
        return maxPoint;
    }
    
    // arr=[5,6,10,19]
    // L=9
    public static void main(String[] args) {
        boolean success = true;
        for (int i = 0; i < 10000; i++) {
            int[] arr = generateRandomArray(20, 10);
            int L = new Random().nextInt(30) + 1;
            int i1 = solution1(arr, L);
            int i2 = solution2(arr, L);
            int i3 = solution3(arr, L);
            if (i1 != i2 || i1 != i3) {
                success = false;
                System.out.println(Arrays.toString(arr));
                System.out.println(L);
                System.out.println(i1);
                System.out.println(i2);
                System.out.println(i3);
                break;
            }
        }
        if (success) {
            System.out.println("Nice!!!");
        } else {
            System.out.println("Fuck!!!");
        }

    }
    
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (maxSize * Math.random() + 1)];
        arr[0] = (int) (maxValue * Math.random() + 1);
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i-1] + (int) (maxValue * Math.random() + 1);
        }
        return arr;
    }
    
}
