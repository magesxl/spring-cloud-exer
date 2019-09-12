package com.example.exer.collection;

import java.util.HashMap;
import java.util.Map;

public class LinkLopDemo {
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
        node7.next = node4;  //构造一个带环的链表,去除此句表示不带环

        System.out.println(hasLoop(node1));
        System.out.println(hasLoopMap(node1));
    }

    /**
     * 判断是否存在环
     *
     * @param node
     * @return
     */
    public static boolean hasLoop(Node<Integer> node) {
        Node<Integer> nodeA = node;
        Node<Integer> nodeB = node.next;
        while (nodeB != null) {
            int dA = nodeA.item;
            int dB = nodeB.item;
            if (dA == dB) return true;  //当两个指针重逢时，说明存在环，否则不存在。
            nodeA = nodeA.next;  //走一步
            nodeB = nodeB.next.next;
        }
        //只有一个元素
        return true;
    }

    /**
     * 将每次走过的节点保存到hash表中，如果节点在hash表中，则表示存在环
     */
    public static boolean hasLoopMap(Node<Integer> node) {
        Node<Integer> temp = node;
        Map<Node<Integer>, Node<Integer>> map = new HashMap<>();
        while (node != null) {
            if (map.get(temp) != null) {
                return true;
            } else {
                map.put(temp, temp);
            }
            temp = temp.next;
            if (temp == null) return false;
        }
        return true;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(E element) {
            this.item = element;
        }

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
