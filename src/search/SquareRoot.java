package search;

import java.math.BigDecimal;

public class SquareRoot {

    public static Double square(Double in, int precision) {

        if (in < 0) {
            throw new RuntimeException("输入不合法：" + in);
        }
        double out = 0.0;
        // 计算整数位 中间节点（(in + 0) / 2）只要大于2那么一定大于4，反之输入小于4整数位一定为1
        Integer inInt = in.intValue();
        if (inInt < 4) {
            out += 1;
        } else {
            // 二分法找到大致位置
            Double low = 0.0;
            Double high = inInt.doubleValue();
            Double mid = low + (high - low) / 2;
            while (mid * mid > inInt) {
                high = mid;
                mid = low + (high - low) / 2;
            }
            // 依次加1找到确定位置
            int midInt = mid.intValue();
            while (midInt * midInt < inInt) {
                midInt++;
            }
            midInt--;
            out += midInt;
        }
        // 计算小数位
        for (int i = 0; i < precision; i++) {
            Double add = 1 / Math.pow(10, i + 1);
            for (int j = 0; j < 10; j++) {
                Double comp = out + add * j;
                if (comp * comp > in) {
                    out = comp - add;
                    break;
                } else if (comp * comp == in) {
                    return comp;
                }
            }
        }
        return out;
    }

    public static BigDecimal square2(BigDecimal in, int precision) {

        if (in.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("输入不合法：" + in);
        }
        BigDecimal out = BigDecimal.ZERO;
        // 计算整数位 中间节点（(in + 0) / 2）只要大于2那么一定大于4，反之输入小于4整数位一定为1
        BigDecimal inInt = new BigDecimal(in.intValue());
        if (inInt.compareTo(new BigDecimal(4)) < 0) {
            out = out.add(new BigDecimal(1));
        } else {
            // 二分法找到大致位置
            BigDecimal low = BigDecimal.ZERO;
            BigDecimal high = inInt;
            BigDecimal mid = low.add((high.subtract(low)).divide(new BigDecimal(2), 10, BigDecimal.ROUND_HALF_UP));
            while (mid.multiply(mid).compareTo(inInt) > 0) {
                high = mid;
                mid = low.add((high.subtract(low)).divide(new BigDecimal(2), 10, BigDecimal.ROUND_HALF_UP));
            }
            // 依次加1找到确定位置
            BigDecimal midInt = new BigDecimal(mid.intValue());
            while (midInt.multiply(midInt).compareTo(inInt) < 0) {
                midInt = midInt.add(new BigDecimal(1));
            }
            midInt = midInt.subtract(new BigDecimal(1));
            out = out.add(midInt);
        }
        // 计算小数位
        for (int i = 0; i < precision; i++) {
            BigDecimal add = new BigDecimal(1 / Math.pow(10, i + 1));
            for (int j = 0; j < 10; j++) {
                BigDecimal comp = out.add(add.multiply(new BigDecimal(j)));
                if (comp.multiply(comp).compareTo(in) > 0) {
                    out = comp.subtract(add);
                    break;
                } else if (comp.multiply(comp).compareTo(in) == 0) {
                    return comp;
                }
            }
        }
        return out;
    }

    public static void main(String[] args) {
        System.out.println(square(500.0, 7));
        System.out.println(square2(new BigDecimal("500"), 7).toString());
    }
}
