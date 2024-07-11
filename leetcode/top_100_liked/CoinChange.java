package leetcode.top_100_liked;

import java.util.Arrays;

// https://leetcode.cn/problems/coin-change/description/?envType=study-plan-v2&envId=top-100-liked
public class CoinChange {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        int[] coins = new int[]{1,2,5};
        int amount = 11;
        System.out.println(coinChange02(coins, amount));
    }

    // 完全背包问题，int[i][j] 表示 coins数组可取 0～i，组成 j 的最小数量
    // 两种情况
    // 1. 不用 coins[i] 的数据，即 int[i-1][j] 的数据（这里可能是无法组成）
    // 2. 用 coins[i] 的数据，即 int[i][j-coins[i]] + 1，需要的金额减去，数量要加 1 （经常忘记+1）
    // 3. 同时，这里有一个特殊情况，情况1和情况2，都是不存在，需要特殊判断，不然+1越界
    // 边界条件
    // 1. amount=0，最小数量是1
    // 2. coins只取 coins[0]，可以得到初始条件
    public static int coinChange01(int[] coins, int amount) {
         int m = coins.length;
         int[][] dp = new int[m][amount+1];
         for (int i = 0; i < m; i++) {
             Arrays.fill(dp[i], Integer.MAX_VALUE);
         }
         for (int i = 0; i < m; i++) {
             dp[i][0] = 0;
         }
         for (int i = 1; i < amount+1; i++) {
             if (i % coins[0] == 0) {
                 dp[0][i] = i / coins[0];
             }
         }
         for (int i = 1; i < m; i++) {
             for (int j = 1; j < amount+1; j++) {
                 dp[i][j] = dp[i-1][j];
                 if (j-coins[i] >= 0 && dp[i][j-coins[i]] != Integer.MAX_VALUE) {
                     dp[i][j] = Math.min(dp[i][j], dp[i][j-coins[i]] + 1);
                 }
             }
         }
         return dp[m-1][amount] == Integer.MAX_VALUE ? -1 : dp[m-1][amount];
    }

    // 空间压缩，从上至下，从左到右
    public static int coinChange02(int[] coins, int amount) {
        int m = coins.length;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < amount+1; i++) {
            if (i % coins[0] == 0) dp[i] = i / coins[0];
        }
        for (int i = 1; i < m; i++) {
            // 这个开始条件直接从上面 j-coins[i] >= 0 得到，含义是从 coins[i] 前面的结果和上一行一样，没必要动
            for (int j = coins[i]; j < amount+1; j++) {
                if (dp[j-coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

}
