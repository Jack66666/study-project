package other;

/**
 * “如何把 n 个数据的所有排列都找出来”，这就是全排列的问题。
 * 我来举个例子。比如，1，2，3 这样 3 个数据，有下面这几种不同的排列：
 * <p>
 * 1, 2, 3
 * 1, 3, 2
 * 2, 1, 3
 * 2, 3, 1
 * 3, 1, 2
 * 3, 2, 1
 */
public class PrintPermutations {

    /**
     * 假设数组中存储的是1，2， 3...n。
     * <p>
     * f(1,2,...n) = {最后一位是1, f(n-1)} + {最后一位是2, f(n-1)} +...+{最后一位是n, f(n-1)}。
     * <p>
     * 调用方式：
     * int[]a = a={1, 2, 3, 4}; printPermutations(a, 4, 4);
     * k表示要处理的子数组的数据个数
     */
    public static void printPermutations(int[] data, int n, int k) {
        if (k == 1) {
            for (int i = 0; i < n; ++i) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }

        // 每次循环都保持数组最后元素不动，最后元素之前的作为子数组递归。递归方法完成后将数组还原
        for (int i = 0; i < k; ++i) {
            int tmp = data[i];
            data[i] = data[k - 1];
            data[k - 1] = tmp;

            printPermutations(data, n, k - 1);

            tmp = data[i];
            data[i] = data[k - 1];
            data[k - 1] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        printPermutations(a, 3, 3);
    }
}
