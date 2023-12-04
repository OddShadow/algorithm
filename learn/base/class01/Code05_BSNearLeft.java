package learn.base.class01;

import java.util.Arrays;
import java.util.Random;

public class Code05_BSNearLeft {
    
    // 在arr上，找满足>=value的最左位置
    public static int nearestIndex(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        index = arr[L] >= value ? L : index; // !!!这里被坑爹了一下，没有这一句，上面条件需要改成 L<=R
        return index;
    }
    
    public static int nearestIndex2(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
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
    
    public static void main(String[] args) {
        for (int i = 0; i < 20000; i++) {
            int[] arr = generateRandomArray(20, 20);
            Arrays.sort(arr);
            int value = new Random().nextInt(arr.length);
            int result1 = nearestIndex(arr, value);
            int result2 = nearestIndex2(arr, value);
            if (result1 != result2) {
                System.out.println("Fuck!");
                break;
            }
        }
    }
    
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (maxSize * Math.random()) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }
    
}
