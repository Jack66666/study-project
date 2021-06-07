package search;

import java.util.ArrayList;
import java.util.List;

public class LoopArraySearch {

    /**
     * 如果有序数组是一个循环有序数组，比如 4，5，6，1，2，3。针对这种情况，如何实现一个求“值等于给定值”的二分查找算法呢？
     * @param array
     * @param val
     * @return
     */
    public static List<Integer> search(int[] array, int val) {

        int len = array.length;
        if (len == 0) {
            return new ArrayList<>();
        }
        List<Integer> index = new ArrayList<>();
        int lastIndex = 0;
        // 确定端点坐标
        for (int i = 1; i < len; i++) {
            if (array[i - 1] > array[i]) {
                index.addAll(searchIndex(array, lastIndex, i - 1, val));
                lastIndex = i;
            } else if (i == len - 1) {
                index.addAll(searchIndex(array, lastIndex, len - 1, val));
            }
        }
        return index;
    }

    private static List<Integer> searchIndex(int[] array, int start, int end, int val) {
        List<Integer> index = new ArrayList<>();
        int low = start;
        int high = end;
        // 二分法查找相等值下标
        while (low <= high) {
            int mid = low + ((high - low) >> 2);
            if (array[mid] < val) {
                low = mid + 1;
            } else if (array[mid] > val) {
                high = mid - 1;
            } else {
                // 查找两边相等值
                int idx = mid;
                index.add(idx);
                while (--idx >= start) {
                    if (array[idx] == val) {
                        index.add(idx);
                    }
                }
                idx = mid;
                while (++idx <= end) {
                    if (array[idx] == val) {
                        index.add(idx);
                    }
                }
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,5,6,1,2,5,5};
        System.out.println(search(array, 5));
    }
}
