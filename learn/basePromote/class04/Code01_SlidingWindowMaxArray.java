package learn.basePromote.class04;

import java.util.LinkedList;

// 由一个代表题目，引出一种结构
//【题目】
// 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次 向右边滑
// 一个位置。
// 例如，数组为[4,3,5,4,3,3,6,7]，窗口大小为3时:
//[4 3 5]4 3 3 6 7
// 4[3 5 4]3 3 6 7
// 4 3[5 4 3]3 6 7
// 4 3 5[4 3 3]6 7
// 4 3 5 4[3 3 6]7
// 4 3 5 4 3[3 6 7]
// 窗口中最大值为5 窗口中最大值为5 窗口中最大值为5 窗口中最大值为4 窗口中最大值为6
// 窗口中最大值为7
// 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。
// 请实现一个函数。 输入:整型数组arr，窗口大小为w。
// 输出:一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的 以本题为例，结果应该
// 返回{5,5,5,4,6,7}
public class Code01_SlidingWindowMaxArray {
    
    public static class WindowMax {
        private int l;
        private int r;
        private int[] arr;
        private LinkedList<Integer> queue;
        public WindowMax(int[] arr) {
            this.arr = arr;
            this.l = -1;
            this.r = 0;
            this.queue = new LinkedList<>();
        }
        public void addNumFromRight() {
            if (r == arr.length) {
                return;
            }
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[r]) {
                queue.pollLast();
            }
            queue.addLast(r++);
        }
        public void removeNumFromLeft() {
            if (l >= r - 1) {
                return;
            }
            l++;
            if (queue.peekFirst() == l) {
                queue.pollFirst();
            }
        }
        public Integer getMax() {
            if (!queue.isEmpty()) {
                return arr[queue.pollFirst()];
            }
            return null;
        }
    }
    
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (queue.peekFirst() == i - w) {
                queue.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[queue.peekFirst()];
            }
        }
        return res;
    }
    
    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int w = 3;
        printArray(getMaxWindow(arr, w));
    }
    
}
