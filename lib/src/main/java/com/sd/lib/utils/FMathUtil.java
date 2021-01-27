package com.sd.lib.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FMathUtil
{
    private FMathUtil()
    {
    }

    //----------加减乘除 start----------

    /**
     * 加法
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double add(double value1, double value2)
    {
        return new BigDecimal(String.valueOf(value1))
                .add(new BigDecimal(String.valueOf(value2))).doubleValue();
    }

    /**
     * 减法
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double subtract(double value1, double value2)
    {
        return new BigDecimal(String.valueOf(value1))
                .subtract(new BigDecimal(String.valueOf(value2))).doubleValue();
    }

    /**
     * 乘法
     *
     * @param value1
     * @param value2
     * @return
     */
    public static double multiply(double value1, double value2)
    {
        return new BigDecimal(String.valueOf(value1))
                .multiply(new BigDecimal(String.valueOf(value2))).doubleValue();
    }

    /**
     * 除法
     *
     * @param value1       被除数
     * @param value2       除数
     * @param scale        保留几位小数
     * @param roundingMode 保留小数的规则(如：四舍五入等)
     * @return
     */
    public static double divide(double value1, double value2, int scale, RoundingMode roundingMode)
    {
        return new BigDecimal(String.valueOf(value1))
                .divide(new BigDecimal(String.valueOf(value2)), scale, roundingMode).doubleValue();
    }

    /**
     * 除法（最终结果四舍五入）
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale  保留几位小数
     * @return
     */
    public static double divide(double value1, double value2, int scale)
    {
        return divide(value1, value2, scale, RoundingMode.HALF_UP);
    }

    //----------加减乘除 end----------

    /**
     * 保留小数位（四舍五入模式）
     *
     * @param value 需要格式化的值
     * @param scale 保留几位小数
     * @return
     */
    public static double scaleHalfUp(double value, int scale)
    {
        return scale(value, scale, RoundingMode.HALF_UP);
    }

    /**
     * 保留小数位
     *
     * @param value 需要格式化的值
     * @param scale 保留几位小数
     * @param mode  保留模式
     * @return
     */
    public static double scale(double value, int scale, RoundingMode mode)
    {
        return new BigDecimal(String.valueOf(value)).setScale(scale, mode).doubleValue();
    }

    /**
     * 计算经纬度之间的距离
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2)
    {
        double theta = lon1 - lon2;
        double dist = Math.sin(degree2Radian(lat1)) * Math.sin(degree2Radian(lat2)) + Math.cos(degree2Radian(lat1)) * Math.cos(degree2Radian(lat2))
                * Math.cos(degree2Radian(theta));
        dist = Math.acos(dist);
        dist = radian2Degree(dist);
        double miles = dist * 60 * 1.1515 * 1.609344 * 1000;
        return miles;
    }

    /**
     * 角度转换为弧度
     *
     * @param degree
     * @return
     */
    public static double degree2Radian(double degree)
    {
        return degree / 180 * Math.PI;
    }

    /**
     * 弧度转换为角度
     *
     * @param radian
     * @return
     */
    public static double radian2Degree(double radian)
    {
        return radian * 180 / Math.PI;
    }

    /**
     * {@link #transformBoundsValue(double, double, double, double, double, int)}
     */
    public static double transformBoundsValue(double inputMin, double inputMax, double outputMin, double outputMax, double inputValue)
    {
        return transformBoundsValue(inputMin, inputMax, outputMin, outputMax, inputValue, 2);
    }

    /**
     * 例如：
     * <p>
     * input：[0-100]
     * output：[0-10]
     * inputValue：50
     * 则计算结果为：5
     *
     * @param inputMin   输入最小值
     * @param inputMax   输入最大值
     * @param outputMin  输出最小值
     * @param outputMax  输出最大值
     * @param inputValue 输入值
     * @param scale      最多保留几位小数
     * @return 输出值
     */
    public static double transformBoundsValue(double inputMin, double inputMax, double outputMin, double outputMax, double inputValue, int scale)
    {
        if (inputMin >= inputMax)
            throw new IllegalArgumentException("inputMin >= inputMin");

        if (outputMin >= outputMax)
            throw new IllegalArgumentException("outputMin >= outputMax");

        if (inputValue < inputMin)
            inputValue = inputMin;

        if (inputValue > inputMax)
            inputValue = inputMax;

        final double inputTotal = subtract(inputMax, inputMin);
        final double inputDelta = subtract(inputValue, inputMin);
        final double inputPercent = divide(inputDelta, inputTotal, 5);

        final double outTotal = subtract(outputMax, outputMin);
        final double outDelta = multiply(outTotal, inputPercent);
        final double result = add(outputMin, outDelta);
        return scaleHalfUp(result, scale);
    }
}
