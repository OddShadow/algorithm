package learn.base.class05;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//求树的最大宽度
public class Code03_TreeMaxWidth {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        
        public Node(int data) {
            this.value = data;
        }
    }
    
    // 层序遍历
    public static void levelOrderTraversal(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.value + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
    
    public static int getMaxWidth(Node head) {
        if (head == null) {
            return 0;
        }
        int maxWidth = 0;
        int curWidth = 0;
        int curLevel = 0;
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(head);
        Node node = null;
        Node left = null;
        Node right = null;
        //当队列不为空时
        while (!queue.isEmpty()) {
            //队列推出一个节点Node
            node = queue.poll();
            left = node.left;
            right = node.right;
            //左节点不为空时，放入levelMap，同时放入队列
            if (left != null) {
                levelMap.put(left, levelMap.get(node) + 1);
                queue.add(left);
            }
            //右节点不为空时，放入levelMap，同时放入队列
            if (right != null) {
                levelMap.put(right, levelMap.get(node) + 1);
                queue.add(right);
            }
            //当当前节点的序号大于curLevel时，令curWidth为0，curLevel设置为当前节点的序号
            //否则curWidth++
            if (levelMap.get(node) > curLevel) {
                curWidth = 0;
                curLevel = levelMap.get(node);
            } else {
                curWidth++;
            }
            //和当前最大值比较获取最大值
            maxWidth = Math.max(maxWidth, curWidth);
        }
        return maxWidth;
    }
    
    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        System.out.println("层序遍历:");
        levelOrderTraversal(head);
        System.out.println("\n" + "=======获取最大宽度=======");
        int width = getMaxWidth(head);
        System.out.println(width);
    }
    
}
