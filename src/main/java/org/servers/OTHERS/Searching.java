package org.servers.OTHERS;

public class Searching {
    public static void main(String[] args) {
        int n, key;
        int[] arr = {0, 6, 12, 14, 19, 22, 48, 66, 79, 88, 104, 126};

        for (int i = 0; i < arr.length; i++) {
            System.out.println(STR."\{arr[i]} ");
        }

        n = arr.length;
        key = 66;
        int index = JumpSearch(arr, n, key);
        if (index > 0) {
            System.out.println("Key successfully found in the array");
        } else {
            System.out.println("key not found!");
        }

    }

    private static int JumpSearch(int[] arr, int n, int key) {
        int i = 0;
        int m;
        int k;
        m = (int) Math.sqrt(n);
        k = m;
        while (arr[m] <= key && m < n) {
            i = m;
            m += k;
            if (m > n - 1) {
                return -1;
            }
        }
        for (int j = i; j < arr.length; j++) {
            if (arr[j] == key) {
                return j;
            }

        }
        return -1;
    }
}
