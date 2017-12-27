package com.fanwe.lib.utils;

import java.util.Iterator;

/**
 * 遍历工具类
 */
public final class FIterateUtil
{
    private FIterateUtil()
    {
    }

    /**
     * 遍历
     *
     * @param iterator
     * @param callback
     * @param <T>
     * @return true-遍历中某一次遍历回调返回了true
     */
    public static <T> boolean iterate(Iterator<T> iterator, IterateCallback<T> callback)
    {
        if (iterator == null || callback == null)
        {
            return false;
        }

        int i = 0;
        while (iterator.hasNext())
        {
            T item = iterator.next();
            if (callback.next(i, item, iterator))
            {
                return true;
            }
            i++;
        }

        return false;
    }

    /**
     * 遍历
     *
     * @param count    遍历次数
     * @param callback
     * @return true-遍历中某一次遍历回调返回了true
     */
    public static boolean foreach(int count, SimpleIterateCallback callback)
    {
        if (count <= 0 || callback == null)
        {
            return false;
        }

        for (int i = 0; i < count; i++)
        {
            if (callback.next(i))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * 倒序遍历
     *
     * @param count
     * @param callback
     * @return true-遍历中某一次遍历回调返回了true
     */
    public static boolean foreachReverse(int count, SimpleIterateCallback callback)
    {
        if (count <= 0 || callback == null)
        {
            return false;
        }

        for (int i = count - 1; i >= 0; i--)
        {
            if (callback.next(i))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * 遍历回调
     *
     * @param <T>
     */
    public interface IterateCallback<T>
    {
        /**
         * 返回true，结束遍历
         *
         * @param i
         * @param item
         * @param it
         * @return
         */
        boolean next(int i, T item, Iterator<T> it);
    }

    /**
     * 遍历回调
     */
    public interface SimpleIterateCallback
    {
        /**
         * 返回true，结束遍历
         *
         * @param i
         * @return
         */
        boolean next(int i);
    }
}
