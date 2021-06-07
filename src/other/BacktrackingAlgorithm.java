package other;

/**
 * 回溯算法
 */
public class BacktrackingAlgorithm {

    /**
     * 8皇后问题计算
     */
    // 全局或成员变量,下标表示行,值表示queen存储在哪一列
    // isOk方法为从下至上寻找对角线，所以回溯时不用更新后面值
    private int[] result = new int[8];
    public void cal8queens(int row) {
        if (row >= 8) {// 8个棋子都放置好了，打印结果
            printCal8queens();
            return;// 8行棋子都放好了，已经没法再往下递归了，所以就return
        }
        for (int col = 0; col < 8; col++) {// 每一行都有8中放法
            if (isOk(row, col)) {// 有些放法不满足要求
                result[row] = col;// 第row行的棋子放到了column列
                cal8queens(row + 1);// 考察下一行
            }
        }
    }

    private boolean isOk(int row, int col) {
        int left = col - 1;
        int right = col + 1;
        for (int r = row - 1; r >= 0; r--) {// 逐行往上考察每一行
            if (result[r] == col) {// 第i行的column列有棋子吗？
                return false;
            }
            if (left >= 0 && result[r] == left) { // 考察左上对角线：第i行leftup列有棋子吗？
                return false;
            }
            if (right < 8 && result[r] == right) { // 考察右上对角线：第i行rightup列有棋子吗？
                return false;
            }
            left--;
            right++;
        }
        return true;
    }

    private void printCal8queens() {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < 8; j++) {
                if (result[i] == j) {
                    System.out.print(" Q ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }

    /**
     * 存储背包中物品总重量的最大值
     */
    private int MAX_WEIGHT = Integer.MIN_VALUE;

    /**
     * 背包计算
     * @param goods 物品数组
     * @param bpWeight 背包可承受重量
     */
    public void backpack(int[] goods, int bpWeight) {
        calBp(goods, 0, 0, bpWeight);
    }

    /**
     * 背包递归计算
     * @param goods 物品数组
     * @param index 第几个物品
     * @param cw 当前已经装进去的物品重量和
     * @param bpWeight 背包可承受重量
     */
    private void calBp(int[] goods, int index, int cw, int bpWeight) {
        if (cw == bpWeight || index == goods.length) {// cw == w标识装满了，i==n标识已经考察完所有物品
            if (cw > MAX_WEIGHT) {
                MAX_WEIGHT = cw;
            }
            return;
        }
        calBp(goods, index + 1, cw, bpWeight);// 不进行装入
        if (cw + goods[index] <= bpWeight) {// 已经超过背包承受重量时，就不要再装了
            calBp(goods, index + 1, cw + goods[index], bpWeight);// 进行装入
        }
    }

    /**
     * 存储背包中物品最大价值
     */
    private int MAX_VALUE = Integer.MIN_VALUE;

    /**
     * 背包计算引入物品价值概念（装入不超重背包重量的最大价值物品）
     * @param goods 物品数组
     * @param values 物品价值
     * @param bpWeight 背包可承受重量
     */
    public void backpackValue(int[] goods, int values[], int bpWeight) {
        calBpValue(goods, values, 0, 0, 0, bpWeight);
    }

    private void calBpValue(int[] goods, int[] values, int index, int cw, int cv, int bpWeight) {
        if (cw == bpWeight || index == goods.length) {// cw == w标识装满了，i==n标识已经考察完所有物品
            if (cv > MAX_VALUE) {
                MAX_VALUE = cv;
            }
            return;
        }
        calBpValue(goods, values, index + 1, cw, cv, bpWeight);// 不进行装入
        if (cw + goods[index] <= bpWeight) {// 已经超过背包承受重量时，就不要再装了
            calBpValue(goods, values, index + 1, cw + goods[index], cv + values[index], bpWeight);// 进行装入
        }
    }

    /**
     * 是否已进行匹配，true是
     */
    private boolean match = false;

    /**
     * 正则匹配
     * 假设：“*”匹配任意多个（大于等于 0 个）任意字符，“?”匹配零个或者一个任意字符。
     * @param ri 正则匹配起始坐标
     * @param regular 正则表达式
     * @param ti 文本串匹配起始坐标
     * @param text 文本串
     */
    public void regularMatch(int ri, char[] regular, int ti, char[] text) {
        if (match) {// 如果已经匹配了，就不要继续递归了
            return;
        }
        if (ri == regular.length) {// 正则表达式到结尾了
            if (ti == text.length) {// 文本串也到结尾了
                match = true;
            }
            return;
        }
        if (regular[ri] == '*') {// “*”匹配任意多个（大于等于 0 个）任意字符
            for (int i = ti; i <= text.length; i++) {
                regularMatch(ri + 1, regular, i, text);
            }
        } else if (regular[ri] == '?') {// ?匹配0个或者1个字符
            regularMatch(ri + 1, regular, ti, text);
            regularMatch(ri + 1, regular, ti + 1, text);
        } else if (ti < text.length && regular[ri] == text[ti]) {// 纯字符匹配才行
            regularMatch(ri + 1, regular, ti + 1, text);
        }
    }

    public static void main(String[] args) {
        BacktrackingAlgorithm backtrackingAlgorithm = new BacktrackingAlgorithm();
        // 8皇后问题计算
        backtrackingAlgorithm.cal8queens(0);
        // 背包问题计算
        int[] goods = new int[]{1, 1, 1};
        int bpWeight = 3;
        backtrackingAlgorithm.backpack(goods, bpWeight);
        System.out.println("背包问题计算" + backtrackingAlgorithm.MAX_WEIGHT);
        // 背包问题计算（引入物品价值概念）
        int[] goodsVal = new int[]{1, 2, 3};
        int[] valuesVal = new int[]{1, 1, 4};
        int bpWeightVal = 3;
        backtrackingAlgorithm.backpackValue(goodsVal, valuesVal, bpWeightVal);
        System.out.println("背包问题计算（引入物品价值概念）" + backtrackingAlgorithm.MAX_VALUE);
        // 正在表达式
        char[] regular = new char[]{'*'};
        char[] text = new char[]{};
        backtrackingAlgorithm.regularMatch(0, regular, 0, text);
        System.out.println(backtrackingAlgorithm.match);
    }

}
