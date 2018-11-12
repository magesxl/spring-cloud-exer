package com.example.exer.collection;

public class Solution {
    public static void main(String... args) {
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node7 = new Node<>(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        System.out.println(reLink(node1));
//        System.out.println(printListFromTailToHead(node1));
    }

    public static String printListFromTailToHead(Node<Integer> node) {
        StringBuilder list = new StringBuilder();
        while (node != null) {
            list.append(node.item);
            node = node.next;
        }
        list.reverse();
        return list.toString();
    }

    public static Node<Integer> reLink(Node<Integer> node) {
        if (node == null || node.next == null) return node;
        //定义新链表头结点
        Node<Integer> newHead = null;
        Node<Integer> now = node;
        while (now != null) {
            Node<Integer> next = now.next;  //获取下一个节点
            now.next = newHead;
            newHead = now;
            next = now;
        }
        return newHead;
    }
}
