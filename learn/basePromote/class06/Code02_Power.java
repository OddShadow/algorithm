package learn.basePromote.class06;

// 判断一个32位正数是不是2的幂，4的幂
public class Code02_Power {
    
    public static boolean is2Power1(int n) {
        return (n & (~n + 1)) == n; // 取最低位 1
    }
    
    public static boolean is2Power2(int n) {
        return (n & (n - 1)) == 0; // 抹去最低位 1
    }
    
    // 二进制表示的 4 的幂特征是只能在偶数位上出现
    // 首先是 2 的幂，即只有一个 1，且 这个 1 出现在奇数位置上 4^0=1 [1] 4^1=4 [100] 4^2=16 [10000]
    // 0x55555555即为0101010...1010101
    public static boolean is4Power(int n) {
        return is2Power1(n) && (n & 0x55555555) != 0;
    }
    
    public static void main(String[] args) {
        System.out.println(is2Power1(1024));
        System.out.println(is2Power2(1024));
        
        System.out.println(is4Power(16));
        System.out.println(is4Power(8));
    }
    
}
