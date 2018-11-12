package com.example.exer.collection;

public class Node<E> {
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
