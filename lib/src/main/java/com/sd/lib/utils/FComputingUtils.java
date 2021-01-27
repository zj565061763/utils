package com.sd.lib.utils;

/**
 * 处理一些计算的逻辑
 */
public class FComputingUtils
{
    private FComputingUtils()
    {
    }

    /**
     * 例如：
     * <p>
     * input：[0-100]
     * output：[0-10]
     * inputValue：50
     * 则计算结果为：5
     *
     * @param inputMin
     * @param inputMax
     * @param outputMin
     * @param outputMax
     * @param inputValue
     * @return
     */
    private static float transformBoundsValue(float inputMin, float inputMax, float outputMin, float outputMax, float inputValue)
    {
        if (inputMin >= inputMax)
            throw new IllegalArgumentException("inputMin >= inputMin");

        if (outputMin >= outputMax)
            throw new IllegalArgumentException("outputMin >= outputMax");

        if (inputValue < inputMin)
            inputValue = inputMin;

        if (inputValue > inputMax)
            inputValue = inputMax;

        final float inputTotal = inputMax - inputMin;
        final float inputDelta = inputValue - inputMin;
        final float inputPercent = inputDelta / inputTotal;

        final float outTotal = outputMax - outputMin;
        final float outDelta = inputPercent * outTotal;
        final float result = outputMin + outDelta;
        return result;
    }
}
