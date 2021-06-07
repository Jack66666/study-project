package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {

    /**
     * 分为10个桶，从后向前每次取一位顺序放入对应桶中，在将10个桶数据依次取出，循环即可的最后排序结果
     * @param array
     */
    public static void radixSort(String[] array) {

        int n = array.length;
        if (n <= 1) {
            return;
        }
        List<String> zero = new ArrayList<>();
        List<String> one = new ArrayList<>();
        List<String> two = new ArrayList<>();
        List<String> three = new ArrayList<>();
        List<String> four = new ArrayList<>();
        List<String> five = new ArrayList<>();
        List<String> six = new ArrayList<>();
        List<String> seven = new ArrayList<>();
        List<String> eight = new ArrayList<>();
        List<String> nine = new ArrayList<>();
        int len = array[0].length();
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (array[j].charAt(i) == '0') zero.add(array[j]);
                if (array[j].charAt(i) == '1') one.add(array[j]);
                if (array[j].charAt(i) == '2') two.add(array[j]);
                if (array[j].charAt(i) == '3') three.add(array[j]);
                if (array[j].charAt(i) == '4') four.add(array[j]);
                if (array[j].charAt(i) == '5') five.add(array[j]);
                if (array[j].charAt(i) == '6') six.add(array[j]);
                if (array[j].charAt(i) == '7') seven.add(array[j]);
                if (array[j].charAt(i) == '8') eight.add(array[j]);
                if (array[j].charAt(i) == '9') nine.add(array[j]);
            }
            int k = 0;
            for (String m : zero) array[k++] = m; zero.clear();
            for (String m : one) array[k++] = m; one.clear();
            for (String m : two) array[k++] = m; two.clear();
            for (String m : three) array[k++] = m; three.clear();
            for (String m : four) array[k++] = m; four.clear();
            for (String m : five) array[k++] = m; five.clear();
            for (String m : six) array[k++] = m; six.clear();
            for (String m : seven) array[k++] = m; seven.clear();
            for (String m : eight) array[k++] = m; eight.clear();
            for (String m : nine) array[k++] = m; nine.clear();
        }
    }

    public static void main(String[] args) {

        String[] array = new String[]{"14567", "98663", "78064", "23759", "78035"};
        System.out.println(Arrays.toString(array));
        radixSort(array);
        System.out.println(Arrays.toString(array));

    }
}
