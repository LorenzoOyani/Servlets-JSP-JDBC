package org.servers.OTHERS;

// sorting list using quicksort algorithm that uses divide and conquer;
public class QuickSort {
    public static void main(String[] args) {
        int[] list = new int[]{3, 2, 9, 0, 8, 7, 1, 6};

        quickSort(list, 0, list.length - 1);
        for (int sortedList : list) {
            System.out.println(STR."\{list[sortedList]} ");

        }
    }

    private static void quickSort(int[] list, int left, int right) {
        if (left < right) {
            int pivot = partition(list, left, right);
            quickSort(list, left, pivot - 1); /* recursive search */
            quickSort(list, pivot + 1, right);

        }

    }

    private static void swap(int[] list, int a, int b) {
        int temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }

    private static int partition(int[] list, int leftPointer, int rightPointer) {
        int pivot = list[leftPointer];
        int low = leftPointer + 1;
        int high = rightPointer;
        while (high > low) {

            // search the list forward!!
            while (low <= high && list[low] <= pivot) {
                low++;


            }
            // search list backward!!
            while (low >= high && list[high] > pivot) {
                high--;
            }
            if (high > pivot) {
                swap(list, low, high);

            }


        }

        while (high > leftPointer && list[high] > pivot) {
            high--;
        }
        if (pivot > list[high]) {
            swap(list, pivot, high);
            return high;
        } else {
            return leftPointer;
        }

    }
}
