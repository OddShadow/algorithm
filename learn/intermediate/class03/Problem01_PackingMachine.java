package learn.intermediate.class03;

/*
    有n个打包机器从左到右一字排开，上方有一个自动装置会抓取一批放物品到每个打包机上，放到每个机器上的这些物品数量有多有少，
    由于物品数量不相同，需要工人将每个机器上的物品进行移动从而到达物品数量相等才能打包。
    拿出只能每次拿一个，接受每次可以接收多个
    每个物品重量太大、每次只能搬一个物品进行移动，为了省力，只在相邻的机器上移动。请计算在搬动最小轮数的前提下，
    使每个机器上的物品数量相等。如果不能使每个机器上的物品相同，返回-1。
    
    例如 [1 0 5] 表示有3个机器，每个机器上分别有1、0、5个物品，经过这些轮后：
    第一轮：1 0 << 5 => 1 1 4
    第二轮：1 << 1 << 4 => 2 1 3
    第三轮：2 1 << 3 => 2 2 2
    移动了 3 轮，每个机器上的物品相等，所以返回 3
    例如[2 2 3]表示有3个机器，每个机器上分别有2、2、3个物品
    这些物品不管怎么移动，都不能使三个机器上物品数量相等，返回—1
 */
public class Problem01_PackingMachine {
    
    public static int MinOps(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int size = arr.length;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }
        if (sum % size != 0) {
            return -1;
        }
        int avg = sum / size;
        int leftSum = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int L = i * avg - leftSum;
            int R = (size - i - 1) * avg - (sum - leftSum - arr[i]);
            if (L > 0 && R > 0) {
                ans = Math.max(ans, Math.abs(L) + Math.abs(R));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(L), Math.abs(R)));
            }
            leftSum += arr[i];
        }
        return ans;
    }
    
    public static void main(String[] args) {
    
    }
    
}
