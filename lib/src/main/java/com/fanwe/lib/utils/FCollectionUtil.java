package com.fanwe.lib.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class FCollectionUtil
{
    public static boolean isEmpty(Collection<?> list)
    {
        if (list != null && !list.isEmpty())
        {
            return false;
        } else
        {
            return true;
        }
    }

    /**
     * index是否合法
     *
     * @param list  集合
     * @param index
     * @return
     */
    public static boolean isIndexLegal(Collection<?> list, int index)
    {
        if (isEmpty(list))
        {
            return false;
        }
        final int size = list.size();

        return isIndexLegal(size, index);
    }

    /**
     * index是否合法
     *
     * @param size  最大数量
     * @param index
     * @return true-index合法
     */
    public static boolean isIndexLegal(int size, int index)
    {
        if (index >= 0 && index < size)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public static <T> T get(List<T> list, int index)
    {
        if (!isIndexLegal(list, index))
        {
            return null;
        }
        return list.get(index);
    }

    public static <T> T getLast(List<T> list, int index)
    {
        if (!isIndexLegal(list, index))
        {
            return null;
        }
        index = list.size() - 1 - index;
        return list.get(index);
    }

    /**
     * 截取list
     *
     * @param list
     * @param start 截取的开始位置，闭区间
     * @param end   截取的结束为止，开区间
     * @param <T>
     * @return
     */
    public static <T> List<T> subList(List<T> list, int start, int end)
    {
        if (isEmpty(list) || !isIndexLegal(list, start) || !isIndexLegal(list, end))
        {
            return null;
        }
        return list.subList(start, end);
    }

    /**
     * 拷贝list
     *
     * @param list
     * @param start 拷贝的开始位置，闭区间
     * @param end   拷贝的结束为止，开区间
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List<T> list, int start, int end)
    {
        if (isEmpty(list) || !isIndexLegal(list, start) || !isIndexLegal(list, end) || start > end)
        {
            return null;
        }

        List<T> listResult = new ArrayList<>();
        for (int i = start; i < end; i++)
        {
            listResult.add(list.get(i));
        }
        return listResult;
    }

    public static <T> List<List<T>> splitList(List<T> list, int countPerList)
    {
        if (isEmpty(list) || countPerList <= 0)
        {
            return null;
        }

        List<List<T>> listGroup = new ArrayList<>();
        List<T> listPage = new ArrayList<>();

        for (int i = 0; i < list.size(); i++)
        {
            listPage.add(list.get(i));
            if (i != 0)
            {
                if ((i + 1) % (countPerList) == 0)
                {
                    listGroup.add(listPage);
                    listPage = new ArrayList<T>();
                }
            }
        }
        if (listPage.size() > 0)
        {
            listGroup.add(listPage);
        }

        return listGroup;
    }

    public static <T> List<List<T>> splitListLinked(List<T> list, int countPerList)
    {
        if (isEmpty(list) || countPerList <= 0)
        {
            return null;
        }

        List<List<T>> listGroup = new ArrayList<>();
        List<T> listPage = new ArrayList<>();

        boolean needBackIndex = false;
        for (int i = 0; i < list.size(); i++)
        {
            if (needBackIndex)
            {
                needBackIndex = false;
                listPage.add(list.get(i - 1));
            } else
            {
                listPage.add(list.get(i));
            }

            if (i != 0)
            {
                if ((i + 1) % (countPerList) == 0)
                {
                    needBackIndex = true;
                    listGroup.add(listPage);
                    listPage = new ArrayList<T>();
                }
            }
        }
        if (listPage.size() > 0)
        {
            listGroup.add(listPage);
        }

        return listGroup;
    }

    public static <T> void removeList(List<T> list, int start, int end)
    {
        if (end >= start && isIndexLegal(list, start) && isIndexLegal(list, end))
        {
            Iterator<T> it = list.iterator();
            int i = 0;
            while (it.hasNext())
            {
                if (i >= start)
                {
                    if (i <= end)
                    {
                        it.next();
                        it.remove();
                    } else
                    {
                        break;
                    }
                }
                i++;
            }
        }
    }
}
