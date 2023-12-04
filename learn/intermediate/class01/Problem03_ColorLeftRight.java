package learn.intermediate.class01;

import java.util.Random;

/*
    牛牛有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。
    牛牛现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色，这个正方形的颜色将会被覆盖。
    牛牛的目标是在完成染色之后，每个红色 R 都比每个绿色 G 距离最左侧近。牛牛想知道他最少需要涂染几个正方形。
    样例所示：s＝RGRGR
    我们涂染之后变成 RRRGG 满足要求了，涂染的个数为2，没有比这个更好的涂染方案。
    
    从左向右遍历，左边全是R，右边全是G，满足条件
    
    优化 可以预处理数据，将其每个下标的 左边G 右边R 的数量提前拿出
 */
public class Problem03_ColorLeftRight {
    
    public static int minPaint1(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] chs = s.toCharArray();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < chs.length; i++) {
            int current = 0;
            for (int j = i - 1; j >= 0; j--) {
                if ('G' == chs[j]) {
                    current++;
                }
            }
            for (int j = i; j < chs.length; j++) {
                if ('R' == chs[j]) {
                    current++;
                }
            }
            min = Math.min(min, current);
        }
        return min;
    }
    
    public static int minPaint2(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] chs = s.toCharArray();
        int[] right = new int[chs.length];
        right[chs.length - 1] = chs[chs.length - 1] == 'R' ? 1 : 0;
        for (int i = chs.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] + (chs[i] == 'R' ? 1 : 0);
        }
        int res = right[0];
        int left = 0;
        for (int i = 0; i < chs.length - 1; i++) {
            left += chs[i] == 'G' ? 1 : 0;
            res = Math.min(res, left + right[i + 1]);
        }
        res = Math.min(res, left + (chs[chs.length - 1] == 'G' ? 1 : 0));
        return res;
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String s = generateString();
            int i1 = minPaint1(s);
            int i2 = minPaint2(s);
            if (i1 != i2) {
                System.out.println("Fuck!!!");
                System.out.println(s);
                System.out.println(i1);
                System.out.println(i2);
                break;
            }
        }
        
    }
    
    public static String generateString() {
        int length = new Random().nextInt(10);
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(new Random().nextInt(2) == 1 ? 'R' : 'G');
        }
        return sb.toString();
    }
    
}
