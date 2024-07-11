package leetcode.top_100_liked;

import java.util.Arrays;

class NumSquares {

    public static void main(String[] args) {

    }

    // dp[i][j] 1...i 得到 j 最少数量
    // dp[i][j] dp[i-1][j] dp[i][j-k*i*i]
    public int numSquares01(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // 1-i*i 之间取得 j 的最少数量
                dp[i][j] = dp[i - 1][j];
                if (j - i * i >= 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - i * i] + 1);
                }
            }
        }
        return dp[n][n];
    }

    public int numSquares02(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, 1, n+1, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            for (int j = i*i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j-i*i] + 1);
            }
        }
        return dp[n];
    }
}