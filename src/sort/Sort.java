package sort;

import java.util.Arrays;

public class Sort {

    /**
     * 冒泡排序（稳定）
     * @param array
     */
    public static void bubbleSort(int[] array) {
        int n = array.length;
        if (n == 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            // 是否进行过值交换
            boolean changeFlag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int val = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = val;

                    changeFlag = true;
                }
            }
            // 没有进行过值交换说明数组已经有序，所以可以break
            if (!changeFlag) {
                break;
            }
        }
    }

    /**
     * 冒泡排序优化：记录循环最后一次交换值位置
     * @param array
     */
    public static void bubbleSort2(int[] array) {
        int n = array.length;
        if (n == 1) {
            return;
        }
        // 最后交换值位置，大于此位置元素已经有序，不需要在进行交换值排序操作
        int lastChangeIndex = n - 1;
        for (int i = 0; i < n; i++) {
            // 是否进行过值交换
            boolean changeFlag = false;
            int j = 0;
            for (; j < lastChangeIndex; j++) {
                if (array[j] > array[j + 1]) {
                    int val = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = val;

                    changeFlag = true;
                }
            }
            // 没有进行过值交换说明数组已经有序，所以可以break
            if (!changeFlag) {
                break;
            } else {
                lastChangeIndex = j;
            }
        }
    }

    /**
     * 插入排序（稳定，使用最多）
     * @param array
     */
    public static void insertSort(int[] array) {
        int n = array.length;
        if (n == 1) {
            return;
        }
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            int val = array[i];
            for (; j >= 0; j--) {
                // 循环比较有两个作用1.将值依次向后移动2.确定插入位置即为j+1
                if (array[j] > val) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            // 如果没有值需要插入改变，那么内循环直接break，也就是自己替换自己值（因为插入排序假定i位置前部分以为从小到大有序，一开始j最大值都小于当前i位置值，那也就不用插入了）
            array[j + 1] = val;
        }
    }

    /**
     * 选择排序（不稳定）
     * @param array
     */
    public static void selectSort(int[] array) {
        int n = array.length;
        if (n == 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            int min = array[i];
            int minIndex = i;
            for (; j < n; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            array[minIndex] = array[i];
            array[i] = min;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
//        bubbleSort(array);
//        bubbleSort2(array);
//        insertSort(array);
        selectSort(array);
        System.out.println(Arrays.toString(array));

    }
}
