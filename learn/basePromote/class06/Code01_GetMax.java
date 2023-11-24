package learn.basePromote.class06;

// 位运算的题目
// 给定两个有符号32位整数a和b，返回a和b中较大的
//【要求】
// 不用做任何比较判断
public class Code01_GetMax {
    
    // 取反 0=>1   1=>0
    public static int flip(int n) {
        return n ^ 1;
    }
    
    // n>=0 => 1   n<0 => 0
    public static int sign(int n) {
        return flip((n >> 31) & 1);
    }
    
    public static int getMax1(int a, int b) {
        int c = a - b; // !!!此处可能会溢出
        int scA = sign(c);
        int scB = flip(scA);
        return a * scA + b * scB;
    }
    
    // 解决getMax1的可能存在的溢出问题，溢出存在于 负数-正数 或者 正数-负数 两种情况
    public static int getMax2(int a, int b) {
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb; // 符号相异，可能溢出为 difSab=1
        int sameSab = flip(difSab);
        
        // 注意此时a和b如果符号相同，则a-b不会溢出
        // 返回a的情况：returnA=1,returnB=0
        //  1) a和b相同符号且a-b>=0;此时difSab=0，sameSab=1,则sc也必须为1即c必须a>b
        //  2) a和b符号不相同并且a>0;此时difSab=1，sameSab=0，则sc无所谓但sa必须为1即a必须>0
        
        // 返回b的情况:returnA=0,returnB=1
        //  1) a和b相同符号a-b<0;此时difSab=0，sameSab=1,sa无所谓，但sc必须为0即必须a<b
        //  2) a和b符号不相同并且a<0;此时difSab=1，sameSab=0，则sc无所谓但a必须<0
        
        int returnA = difSab * sa + sameSab * sc;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }
    
    public static void main(String[] args) {
        int a = -16;
        int b = 1;
        System.out.println(getMax1(a, b));
        System.out.println(getMax2(a, b));
        a = 2147483647;
        b = -2147480000;
        System.out.println(getMax1(a, b)); // wrong answer because of overflow
        System.out.println(getMax2(a, b));
    }
    
}
