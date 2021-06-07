package search;

import java.util.Arrays;

public class Heap {

    /**
     * 数组，从下标1开始存储数据
     */
    private int[] data;

    /**
     * 堆可以存储的最大数据个数
     */
    private int maxNum;

    /**
     * 堆中已经存储的数据个数，注：此值也等于当前数组最后一个元素下标
     */
    private int hasNum;

    /**
     * 往堆中插入一个元素
     *
     * @param val
     */
    public void insert(int val) {
        if (hasNum == maxNum) {
            return;
        }

        // +1相当插入后元素下标
        int curIndex = hasNum + 1;
        int fatherIndex = curIndex / 2;
        while (val > data[fatherIndex]) {// 自下往上堆化
            data[curIndex] = data[fatherIndex];
            curIndex = fatherIndex;
            fatherIndex = fatherIndex / 2;
            // 除2到1后已经为根节点，1 / 2 = 0即可退出循环
            if (fatherIndex == 0) {
                break;
            }
        }
        data[curIndex] = val;
        hasNum++;

    }

    /**
     * 往堆中插入一个元素（从下向上堆化）
     *
     * @param val
     */
    public void insert2(int val) {
        if (hasNum == maxNum) {
            return;
        }
        data[++hasNum] = val;
        int index = hasNum;
        while (data[index] > data[index / 2] && index > 0) {
            swap(data, index / 2, index);
            // 下次循环从“父节点”重新开始堆化
            index = index / 2;
        }
    }

    /**
     * 交换数组值
     *
     * @param data
     * @param a
     * @param b
     */
    private static void swap(int[] data, int a, int b) {
        int tmp = data[a];
        data[a] = data[b];
        data[b] = tmp;
    }

    /**
     * 删除堆顶元素
     */
    public void delTop() {
        if (hasNum < 1) {
            return;
        }
        data[1] = data[hasNum];
        data[hasNum] = 0;
        upToDownChange(data, 1, hasNum);
        hasNum--;
    }

    /**
     * 建堆
     *
     * @param data
     * @param curIndex
     */
    public void createHeap(int[] data, int curIndex) {
        int fatherIndex = curIndex / 2;
        for (int i = fatherIndex; i > 0; i--) {
            upToDownChange(data, i, curIndex);
        }
        this.data = data;
        this.hasNum = curIndex;
        this.maxNum = data.length - 1;
    }

    /**
     * 从上到下堆化
     */
    private static void upToDownChange(int[] data, int index, int hasNum) {
        while (true) {
            int maxIndex = index;
            if (index * 2 <= hasNum && data[index] < data[index * 2]) {
                maxIndex = index * 2;
            }
            if (index * 2 + 1 <= hasNum && data[maxIndex] < data[index * 2 + 1]) {
                maxIndex = index * 2 + 1;
            }
            // 相等说明没有进行交换，也就说明可以进行退出
            if (maxIndex == index) {
                break;
            }
            swap(data, index, maxIndex);
            // 下次循环从“子节点”重新开始堆化
            index = maxIndex;
        }
    }

    /**
     * 堆排序
     *
     * @param data
     * @param curIndex
     */
    public void orderHeap(int[] data, int curIndex) {
        createHeap(data, curIndex);
        System.out.println(Arrays.toString(this.data));
        while (curIndex > 0) {
            swap(data, 1, curIndex);
            curIndex--;
            upToDownChange(data, 1, curIndex);
        }
        System.out.println(Arrays.toString(this.data));
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        int[] data = new int[]{0, 7, 5, 19, 8, 4, 1, 20, 13, 16, 0, 0};
//        heap.createHeap(data, 9);
//        System.out.println(Arrays.toString(heap.data));
//        heap.insert2(18);
//        System.out.println(Arrays.toString(heap.data));
//        heap.insert2(2);
//        System.out.println(Arrays.toString(heap.data));
//        heap.insert2(1);
//        System.out.println(Arrays.toString(heap.data));
//        heap.delTop();
//        System.out.println(Arrays.toString(heap.data));
//        heap.delTop();
//        System.out.println(Arrays.toString(heap.data));
        heap.orderHeap(data, 9);

    }
}
