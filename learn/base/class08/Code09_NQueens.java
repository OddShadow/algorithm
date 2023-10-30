package learn.base.class08;

public class Code09_NQueens {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n);
    }

    // i 第几行 0 ~ n-1
    // 所有皇后位置 record[0]=0 表示，0行皇后在0列
    // n 表示有几个皇后，n*n 的棋盘
    // 返回值表示多少种摆放方式
    public static int process1(int i, int[] record, int n) {
        if (i == n) { // 终止行
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // limit 皇后数的位表示，8 皇后即 0000...0000 1111 1111
    // colLim 列限制 0000 0010 即表示右边第二位不可以放置
    // leftDiaLim  左对角线限制 0000 0100 即表示右边第三位不可以放置
    // rightDiaLim 右对角线限制 0000 0001 即表示右边第一位不可以放置
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim)); // 净化超出位置为0，1表示可以放置位置
        int res = 0;
        while (pos != 0) {
            int mostRightOne = pos & (~pos + 1); // 取最右可放置位
            pos = pos - mostRightOne;
            res += process2(limit,
                    colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 4;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
