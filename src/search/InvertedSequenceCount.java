package search;

/**
 * 求出一组数据的有序对个数或者逆序对个数（使用归并排序思想）
 * 例如：
 * 2, 4, 3, 1, 5, 6 逆序对个数：4
 * (2, 1) (4, 3) (4, 1) (3, 1)
 */
public class InvertedSequenceCount {

    public static int count(int[] array) {

        return mergeCount(array, 0, array.length - 1);
    }

    public static int mergeCount(int[] array, int p, int q) {

        if (p >= q) {
            return 0;
        }
        int count = 0;
        int r = (q + p) / 2;
        count += mergeCount(array, p, r);
        count += mergeCount(array, r + 1, q);
        count += merge(array, p, r, q);
        return count;
    }

    public static int merge(int[] array, int p, int r, int q) {
        if (p >= r || r >= q) {
            return 0;
        }
        int i = p;
        int j = r + 1;
        int t = 0;
        int count = 0;
        int tmp[] = new int[q - p + 1];
        while (i <= r && j <= q) {
            if (array[i] < array[j]) {
                tmp[t++] = array[i];
                i++;
            } else {
                tmp[t++] = array[j];
                count += r - i + 1;
                j++;
            }
        }
        while (i <= r) {
            tmp[t++] = array[i++];
        }
        while (j <= q) {
            tmp[t++] = array[j++];
        }
        for (int k = 0; k < tmp.length; k++) {
            array[p + k] = tmp[k];
        }
        return count;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 4, 3, 1, 5, 6};
        System.out.println(count(array));
    }
}
