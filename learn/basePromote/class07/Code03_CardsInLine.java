package learn.basePromote.class07;

public class Code03_CardsInLine {
    
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
    }
    
    public static int first(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        return Math.max(arr[i] + second(arr, i + 1, j), arr[j] + second(arr, i, j - 1));
    }
    
    public static int second(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        return Math.min(first(arr, i + 1, j), first(arr, i, j - 1));
    }
    
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 9, 1};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        
    }
    
}
