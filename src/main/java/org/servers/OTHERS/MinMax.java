package org.servers.OTHERS;

/* Use a divide and conquer algorithm to find the maximum and minimum value of array List */
public class MinMax {

    static class Pair {
        int max;
        int min;

        Pair() {
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 4, 8, 2, 6, 20, 67, 34, 29, 54, 46, 39, 23};
        int length = arr.length;
        for (int array : arr) {
            System.out.println(array);
        }
        Pair result = maxMinDivideAndConquer(arr, 0, length - 1);
        System.out.println(STR."The maximum number is \{result.max}");
        System.out.println(STR."The minimum number is \{result.min}");
    }

    /*
     * Divide the list into two left and right arrays;
     * provide condition for when there is one element on the list;
     * provide condition where there is more than one element on the list;
     * compare the two encountered element while in a loop
     * */
    private static Pair maxMinDivideAndConquer(int[] arr, int low, int high) {
        Pair result = new Pair();
        Pair left;
        Pair right;
        int mid;
        if (low == high) {
            result.max = arr[low];
            result.min = arr[low];
            return result;
        }
        if (low == high + 1) {
            if (arr[low] < arr[high]) {
                result.min = arr[low];
                result.max = arr[high];
            } else {
                result.min = arr[high];
                result.max = arr[low];
            }
            return result;
        }

        mid = (low + high) / 2;
        left = maxMinDivideAndConquer(arr, low, mid);
        right = maxMinDivideAndConquer(arr, mid + 1, high);

        result.max = Math.max(left.max, right.max);
        result.min = Math.min(left.min, right.min);

        return result;
    }
}
