package com.fanwe.lib.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射工具类
 */
public class FReflectUtil
{
    private FReflectUtil()
    {
    }

    public static Type[] getType(Class<?> clazz)
    {
        Type type = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        return types;
    }

    public static Type getType(Class<?> clazz, int index)
    {
        try
        {
            Type[] types = getType(clazz);
            return types[index];
        } catch (Exception e)
        {
            return null;
        }
    }
}
