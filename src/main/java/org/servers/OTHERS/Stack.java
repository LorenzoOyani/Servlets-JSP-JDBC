package org.servers.OTHERS;

public class Stack {
    int[] array;
    int capacity;
    int top;

    public Stack(int capacity) {
        this.array = new int[capacity];
        this.capacity = capacity;
        this.top = -1;
    }

    public void push(int data) {
        if (top == capacity - 1) {
            System.out.println("StackOverflow");
        }
        array[top++] = data;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("stack empty");
            return -1;
        }
        return array[top];

    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int pop(int data) { // accepting data after pop
        if (isEmpty()) {
            return -1;
        }
        int temp = array[top--]; // a deleted int;
        push(data); // replaced int on memory space
        return temp; // return deleted int

    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return array[top--];

    }

    public boolean isFull() {
        return top == capacity - 1;
    }

}
