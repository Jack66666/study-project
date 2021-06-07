package search;

import java.util.HashMap;
import java.util.Map;

public class StringMatch {

    public static int bf(char[] origin, char[] find) {
        int originLength = origin.length;
        int findLength = find.length;
        if (originLength < findLength) {
            return -1;
        }
        for (int i = 0; i < originLength - findLength + 1; i++) {
            int j = 0;
            for (; j < findLength; j++) {
                if (find[j] != origin[j + i]) {
                    break;
                }
            }
            if (j == findLength) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 将字符串转换为26进制形式计算
     */
    public static final Map<Character, Integer> codeMap = new HashMap<>();

    static {
        codeMap.put('a', 0);
        codeMap.put('b', 1);
        codeMap.put('c', 2);
        codeMap.put('d', 3);
        codeMap.put('e', 4);
        codeMap.put('f', 5);
        codeMap.put('g', 6);
        codeMap.put('h', 7);
        codeMap.put('i', 8);
        codeMap.put('j', 9);
    }

    /**
     * 初始化26进制多次幂计算
     */
    public static final int findMaxLength = 5;
    public static final int[] radix = new int[findMaxLength];

    static {
        int r = 1;
        for (int i = 0; i < radix.length; i++) {
            r = i == 0 ? 1 : r * 26;
            radix[i] = r;
        }
    }

    public static int rk(char[] origin, char[] find) {
        int originLength = origin.length;
        int findLength = find.length;
        if (originLength < findLength) {
            return -1;
        }
        if (findMaxLength < findLength) {
            throw new RuntimeException("查询字符串长度大于可查最大长度");
        }
        // 初始化主串hashCode
        int[] hashCode = new int[originLength - findLength + 1];
        for (int i = 0; i < originLength - findLength + 1; i++) {
            int code = 0;
            if (i == 0) {
                for (int j = 0; j < findLength; j++) {
                    code = code + codeMap.get(origin[j + i]) * radix[findLength - j - 1];
                }
            } else {
                int lastCode = hashCode[i - 1];
                code = (lastCode - codeMap.get(origin[i - 1]) * radix[findLength - 1]) * 26 + codeMap.get(origin[i + findLength - 1]);
            }
            hashCode[i] = code;
        }
        // 计算模式串hashCode
        int findCode = 0;
        for (int i = 0; i < findLength; i++) {
            findCode = findCode + codeMap.get(find[i]) * radix[findLength - i - 1];
        }
        // 查找位置
        for (int i = 0; i < hashCode.length; i++) {
            if (findCode == hashCode[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        char[] origin = new char[]{'a', 'b', 'f', 'b', 'd', 'c'};
        char[] find = new char[]{'d', 'c'};
        System.out.println(StringMatch.bf(origin, find));
        System.out.println(StringMatch.rk(origin, find));
    }
}
