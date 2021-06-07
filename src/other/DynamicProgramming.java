package other;

/**
 * 动态规划
 */
public class DynamicProgramming {

    public int backpack(int[] goods, int bpWeight) {
        int n = goods.length;
        boolean[][] status = new boolean[n][bpWeight + 1];
        status[0][0] = true;
        if (goods[0] <= bpWeight) {
            status[0][goods[0]] = true;
        }
        for (int g = 1; g < n; g++) {
            for (int j = 0; j <= bpWeight; j++) {
                if (status[g - 1][j]) {
                    status[g][j] = true;
                }
            }
            for (int j = bpWeight - goods[g]; j >= 0 ; j--) {// 此处也可写成for (int j = 0; j <= bpWeight - goods[g] ; j++)由于if (status[g - 1][j]) 里用的g - 1，所以不会影响到本行之后数据
                if (status[g - 1][j]) {
                    status[g][j + goods[g]] = true;
                }
            }
        }
        int maxWeight = 0;
        for (int i = bpWeight; i > 0; i--) {
            if (status[n - 1][i]) {
                maxWeight = i;
                break;
            }
        }
        return maxWeight;
    }

    public int backpack1(int[] goods, int bpWeight) {
        int n = goods.length;
        boolean[] status = new boolean[bpWeight + 1];
        status[0] = true;
        if (goods[0] <= bpWeight) {
            status[goods[0]] = true;
        }
        for (int g = 1; g < n; g++) {
            for (int j = bpWeight - goods[g]; j >= 0; j--) {// 此处由大到小这样写，否则会影响到本行之后数据
                if (status[j]) {
                    status[j + goods[g]] = true;
                }
            }
        }
        int maxWeight = 0;
        for (int i = bpWeight; i > 0; i--) {
            if (status[i]) {
                maxWeight = i;
                break;
            }
        }
        return maxWeight;
    }

    public int backpackValue(int[] goods, int[] values, int bpWeight) {
        int n = goods.length;
        int[][] status = new int[n][bpWeight + 1];
        for (int i = 0; i < status.length; i++) {
            for (int j = 0; j < status[i].length; j++) {
                status[i][j] = -1;
            }
        }
        status[0][0] = 0;
        if (goods[0] <= bpWeight) {
            status[0][goods[0]] = values[0];
        }
        for (int g = 1; g < n; g++) {
            for (int j = 0; j <= bpWeight; j++) {
                if (status[g - 1][j] > -1) {
                    status[g][j] = status[g - 1][j];
                }
            }
            for (int j = 0; j <= bpWeight - goods[g]; j++) {
                if (status[g - 1][j] > -1) {
                    int val = status[g - 1][j] + values[g];
                    if (val > status[g][j + goods[g]]) {
                        status[g][j + goods[g]] = val;
                    }
                }
            }
        }
        int maxValue = -1;
        for (int i = bpWeight; i >=0; i--) {
            if (status[n - 1][i] > -1) {
                maxValue = status[n - 1][i];
                break;
            }
        }
        return maxValue;
    }

//    public int backpackValue1(int[] goods, int[] values, int bpWeight) {
//        int n = goods.length;
//        int[] status = new int[bpWeight + 1];
//        for (int i = 0; i < status.length; i++) {
//            status[i] = -1;
//        }
//        status[0] = 0;
//        if (goods[0] <= bpWeight) {
//            status[goods[0]] = values[0];
//        }
//        for (int g = 1; g < n; g++) {
//            for (int j = 0; j <= bpWeight - goods[g]; j++) {
//                if (status[g - 1][j] > -1) {
//                    int val = status[g - 1][j] + values[g];
//                    if (val > status[g][j + goods[g]]) {
//                        status[g][j + goods[g]] = val;
//                    }
//                }
//            }
//        }
//        int maxValue = -1;
//        for (int i = bpWeight; i >=0; i--) {
//            if (status[n - 1][i] > -1) {
//                maxValue = status[n - 1][i];
//                break;
//            }
//        }
//        return maxValue;
//    }

    public static void main(String[] args) {
        DynamicProgramming dynamicProgramming = new DynamicProgramming();
        // 背包问题计算
        int[] goods = new int[]{1, 1, 1};
        int bpWeight = 3;
        int maxWeight;
        maxWeight = dynamicProgramming.backpack(goods, bpWeight);
        System.out.println("背包问题计算：" + maxWeight);
        maxWeight = dynamicProgramming.backpack1(goods, bpWeight);
        System.out.println("背包问题计算1：" + maxWeight);
        int maxValue;
        // 背包问题计算（引入物品价值概念）
        int[] goodsVal = new int[]{1, 2, 3};
        int[] valuesVal = new int[]{1, 1, 4};
        int bpWeightVal = 3;
        maxValue = dynamicProgramming.backpackValue(goodsVal, valuesVal, bpWeightVal);
        System.out.println("背包问题计算（引入物品价值概念）：" + maxValue);
    }
}
