package sort;

import java.util.Arrays;

public class Sort2 {


    /**
     * 归并排序
     * @param array
     */
    public static void mergeSort(int[] array) {

        int n = array.length;
        if (n <= 1) {
            return;
        }

        // n - 1 保证坐标为闭区间[0, n - 1]
        mergeSort(array, 0, n - 1);

    }

    /**
     * 切分一半数组再合并
     * @param array
     * @param first
     * @param end
     */
    private static void mergeSort(int[] array, int first, int end) {

        if (end <= first) {
            return;
        }

        int middle = (first + end) / 2;
        // middle + 1 保证切分第二个数组和第一个数组没有交集
        mergeSort(array, first, middle);
        mergeSort(array, middle + 1, end);

        // 对两个切分数组进行合并
        mergeSort(array, first, middle, end);
    }

    private static void mergeSort(int[] array, int first, int middle, int end) {
        int[] tmp = new int[end - first + 1];
        // 第一个数组坐标为[first, middle]
        int i = first;
        // 第二个数组坐标为[middle + 1, end]
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= end) {
            if (array[i] < array[j]) {
                tmp[k] = array[i];
                i++;
            } else {
                tmp[k] = array[j];
                j++;
            }
            k++;
        }

        for (; i <= middle; i++) {
            tmp[k] = array[i];
            k++;
        }

        for (; j <= end; j++) {
            tmp[k] = array[j];
            k++;
        }

        for (int m = 0; m < tmp.length; m++) {
            array[first] = tmp[m];
            first++;
        }
    }


    /**
     * 快速排序
     * @param array
     */
    public static void quickSort(int[] array) {

        int n = array.length;
        if (n <= 1) {
            return;
        }

        sort(array, 0, n - 1);
    }

    /**
     * 1、确定最后一个元素位置为标准点
     * 2、排序依次即可找到标准点位置
     * 3、将数组[first, 标准点坐标 - 1]，[标准点坐标 + 1, end]进行排序
     * @param array
     * @param first
     * @param end
     */
    private static void sort(int[] array, int first, int end) {
        if (end - first <= 0) {
            return;
        }
        // i为指定大于array[end]（标准点）元素位置，j用来循环整个数组。循环整个数组后保证>=i之后元素大于array[end]（标准点），之后交换array[i]与array[end]
        int i = first;
        int j = first;
        int point = array[end];
        for (; j < end; j++) {
            if (array[j] < point) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;

                // 优化，i == j 不需要自己交换自己，注意：i不可能大于j
//                if (i == j) {
//                    i++;
//                } else if (i < j){
//                    int tmp = array[i];
//                    array[i] = array[j];
//                    array[j] = tmp;
//                    i++;
//                }
            }
        }
        int tmp = array[i];
        array[i] = point;
        array[end] = tmp;

        // 由于array[end]（标准点）已经找到位置，所以去掉交换后的标准点元素
        sort(array, first, i - 1);
        sort(array, i + 1, end);

    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 4, 3, 1, 5, 9, 7, 8};
//        mergeSort(array);
        quickSort(array);
        System.out.println(Arrays.toString(array));

    }
}
