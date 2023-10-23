package learn.base.class05;

import java.util.LinkedList;
import java.util.Stack;

// 判断是否是二叉排序树
// 左节点小于根节点，右节点大于根节点
public class Code04_IsBST {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        
        public Node(int data) {
            this.value = data;
        }
    }
    
    private static int preValue1 = Integer.MIN_VALUE;
    
    // 1. 递归中序遍历 动态检查
    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        if (!isBST1(head.left)) {
            return false;
        }
        if (head.value > preValue1) {
            preValue1 = head.value;
        } else {
            return false;
        }
        return isBST1(head.right);
    }
    
    // 2. 非递归中序遍历 动态检查
    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        int preValue2 = Integer.MIN_VALUE;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.value > preValue2) {
                    preValue2 = head.value;
                } else {
                    return false;
                }
                head = head.right;
            }
        }
        return true;
    }
    
    // 3. 傻白甜代码
    public static boolean isBST3(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> inOrderList = new LinkedList<>();
        process(head, inOrderList);
        int pre = Integer.MIN_VALUE;
        for (Node cur : inOrderList) {
            if (pre >= cur.value) {
                return false;
            }
            pre = cur.value;
        }
        return true;
    }
    
    // 4. 套路解 找特征每个节点都要返回 是否二叉搜索树，最大值，最小值
    public static ReturnType isBST4(Node head) {
        if (head == null) {
            return new ReturnType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        ReturnType leftData = isBST4(head.left);
        ReturnType rightData = isBST4(head.right);
        boolean flag = (leftData.flag && rightData.flag) && head.value > leftData.max && head.value < rightData.min;
        int max = Math.max(leftData.max, head.value);
        int min = Math.min(rightData.min, head.value);
        return new ReturnType(flag, max, min);
    }
    
    public static class ReturnType {
        public boolean flag;
        public int max;
        public int min;
    
        public ReturnType(boolean flag, int max, int min) {
            this.flag = flag;
            this.max = max;
            this.min = min;
        }
    }
    
    // 中序递归遍历
    public static void process(Node node, LinkedList<Node> inOrderList) {
        if (node == null) {
            return;
        }
        process(node.left, inOrderList);
        inOrderList.add(node);
        process(node.right, inOrderList);
    }
    
    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);
        
        boolean isBST1 = isBST1(head);
        System.out.println("isBST1:" + isBST1);
        boolean isBST2 = isBST2(head);
        System.out.println("isBST2:" + isBST2);
        boolean isBST3 = isBST3(head);
        System.out.println("isBST3:" + isBST3);
        boolean isBST4 = isBST4(head).flag;
        System.out.println("isBST4:" + isBST4);
    }
    
}
