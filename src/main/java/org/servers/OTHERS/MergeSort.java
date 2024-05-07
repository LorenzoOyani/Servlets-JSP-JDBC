package org.servers.OTHERS;

public class MergeSort {
    private final int[] arr = new int[]{3, 6, 2, 8, 9, 0, 9, 7};

    private static void mergeSort(int[] list) {
        if (list.length > 1) {
            int firstHalfLength = list.length / 2;
            int[] firstHalf = new int[firstHalfLength];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);
            merge(firstHalf, secondHalf, list);
        }

    }

    private static void merge(int[] firstHalf, int[] secondHalf, int[] list) {
        int currentList1 = 0;
        int currentList2 = 0;
        int currentList3 = 0;

        while (currentList1 < firstHalf.length && currentList2 < secondHalf.length) {
            if (firstHalf[currentList1] < secondHalf[currentList2]) {
                list[currentList3] = firstHalf[currentList1];

            } else {
                list[currentList3] = secondHalf[currentList2];
            }
            currentList1++;
            currentList2++;
            currentList3++;
        }
        while (currentList1 < firstHalf.length) {
            list[currentList3++] = firstHalf[currentList1++];

        }
        while (currentList2 < secondHalf.length) {
            list[currentList3++] = secondHalf[currentList2++];
        }
    }


    void main() {
        mergeSort(arr);
        for (int arrays : arr) {
            System.out.println(STR."merged and sorted values \{arrays}");
        }


    }

}
