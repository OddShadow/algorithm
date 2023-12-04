package learn.intermediate.class02;

/*
    n 份青草放一堆
    先手-first 后手-second 都聪明
    每次只能吃 4 的 n 次方份草
    谁最后把草吃完谁赢
 */
public class Problem06_Eat {
    
    public static String solution1(int n) {
        if (n <= 4) {
            return (n == 0 || n == 2) ? "second" : "first";
        }
        int base = 1; // 1 4 16 64 ...
        while (base <= n) {
            if (solution1(n - base).equals("second")) {
                return "first";
            }
            if (base > n / 4) { // 防止数据越界写法
                break;
            }
            base *= 4;
        }
        return "second";
    }
    
    public static String solution2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "second";
        } else {
            return "first";
        }
    }
    
    public static void main(String[] args) {
        getRegular();
        
        for (int i = 0; i < 60; i++) {
            if (!solution1(i).equals(solution2(i))) {
                System.out.println("Fuck!!!");
                System.out.println(i);
                break;
            }
        }
    }
    
    public static void getRegular() {
        for (int i = 0; i < 60; i++) {
            System.out.printf("%3d::%s\r\n", i, solution1(i));
        }
    }
    
}
