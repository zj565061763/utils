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
     * @param iterable
     * @param callback
     * @param <T>
     */
    public static <T> void iterate(Iterable<T> iterable, IterateCallback<T> callback)
    {
        if (iterable == null || callback == null)
        {
            return;
        }
        Iterator<T> it = iterable.iterator();

        int i = 0;
        while (it.hasNext())
        {
            T item = it.next();
            if (callback.next(i, item, it))
            {
                return;
            }
            i++;
        }
    }

    /**
     * 遍历
     *
     * @param count    遍历次数
     * @param callback
     */
    public static void foreach(int count, SimpleIterateCallback callback)
    {
        if (count <= 0 || callback == null)
        {
            return;
        }
        for (int i = 0; i < count; i++)
        {
            if (callback.next(i))
            {
                return;
            }
        }
    }

    /**
     * 倒序遍历
     *
     * @param count    要遍历的次数
     * @param callback
     */
    public static void foreachReverse(int count, SimpleIterateCallback callback)
    {
        if (count <= 0 || callback == null)
        {
            return;
        }
        for (int i = count - 1; i >= 0; i--)
        {
            if (callback.next(i))
            {
                return;
            }
        }
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
