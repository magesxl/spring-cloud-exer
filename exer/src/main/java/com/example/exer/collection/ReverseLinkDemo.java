package com.example.exer.collection;

/**
 * 单链表反转
 */
public class ReverseLinkDemo {

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
        printList(node1);
        Node<Integer> node  = reverseLink1(node1);
 //       Node<Integer> node = reverseLink3(node1);
        printList(node);

    }

    /**
     * 迭代法。先将下一节点纪录下来，然后让当前节点指向上一节点，再将当前节点纪录下来,再让下一节点变为当前节点
     *
     * @param head
     */
    public static Node<Integer> reverseLink1(Node<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        //定义新链表节点头
        Node<Integer> newHead = null;
        Node<Integer> now = head;
        while (now != null) {
            Node<Integer> next = now.next;  //记录下一个节点
            now.next = newHead;  //将新链表节点指向head节点上  第一次循环赋值为空  后续赋值为现在节点
            newHead = now; // 让newHead指向head   把现在节点赋值给  新链表节点
            now = next;  // 将head指向下一个节点   把下一个节点赋值给现在节点
        }
        return newHead;
    }
    /**
     * 递归方法2。先找到最后一个节点，然后从最后一个节点之前的那个节点的方法体中开始将下一个指向当前一个,
     * 然后当前节点反转时其后面的节点已经进行反转了，不需要管。最后返回原来的最后一个节点。
     *
     * @param node
     */
    public static Node<Integer> reverseLink3(Node<Integer> node) {
        if (node == null || node.next == null) return node;
        Node<Integer> next = node.next;  //下一个节点
        node.next = null;  //下一个节点设置为null
        Node<Integer> pre = reverseLink3(next);
        next.next = node;
        return pre;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
        }
    }

    /**
     * 打印链表
     *
     * @param head
     */
    public static void printList(Node<Integer> head) {
        if (head == null)
            return;
        while (head != null) {
            System.out.print(head.item + " ");
            head = head.next;
        }
    }
}
