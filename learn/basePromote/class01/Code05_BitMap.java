package learn.basePromote.class01;

public class Code05_BitMap {
    
    public static void main(String[] args) {
        int a = 0; // a 32bit
        int[] arr = new int[6]; // 32bit * 6 = 192bit
        
        int i = 178;
        int numIndex = i / 32;
        int bitIndex = i % 32;
        System.out.println("numIndex=" + numIndex);
        System.out.println("bitIndex=" + bitIndex);
        
        // 这里的位数是从 0 开始数
        // 修改第 178 位状态为 1
        setBitOne(arr, numIndex, bitIndex);
        // 拿出第 178 位状态
        System.out.println(getBit(arr, numIndex, bitIndex));
    
        // 修改第 178 位状态为 0
        setBitZero(arr, numIndex, bitIndex);
        // 拿出第 178 位状态
        System.out.println(getBit(arr, numIndex, bitIndex));
        
        // 查看数组bit
        setBitOne(arr, numIndex, bitIndex);
        System.out.println("=========查看数组bit=========");
        for (int value : arr) {
            for (int k = 0; k < 32; k++) {
                System.out.print((value & (1 << k)) == 0 ? "0" : "1");
            }
            System.out.print(" ");
        }
    }
    
    private static void setBitOne(int[] arr, int numIndex, int bitIndex) {
        arr[numIndex] = arr[numIndex] | (1 << bitIndex);
    }
    
    private static void setBitZero(int[] arr, int numIndex, int bitIndex) {
        arr[numIndex] = arr[numIndex] & ~(1 << bitIndex);
    }
    
    private static int getBit(int[] arr, int numIndex, int bitIndex) {
        return (arr[numIndex] >> (bitIndex)) & 1;
    }
    
}
