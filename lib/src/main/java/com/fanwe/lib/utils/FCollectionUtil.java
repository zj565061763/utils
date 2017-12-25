package com.fanwe.lib.utils;

import java.lang.reflect.Array;
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

    public static <T> List<T> subListToSize(List<T> list, int size)
    {
        List<T> listReturn = null;
        if (!isEmpty(list) && list.size() >= size && size > 0)
        {
            listReturn = new ArrayList<T>();
            for (int i = 0; i < size; i++)
            {
                T t = list.get(i);
                listReturn.add(t);
            }
        }
        return listReturn;
    }

    public static <T> List<T> subListToSizeAvailable(List<T> list, int size)
    {
        List<T> listReturn = null;
        if (!isEmpty(list) && size > 0)
        {
            int loopCount = 0;
            int listSize = list.size();
            if (size <= listSize)
            {
                loopCount = size;
            } else
            {
                loopCount = listSize;
            }
            listReturn = new ArrayList<T>();
            for (int i = 0; i < loopCount; i++)
            {
                T t = list.get(i);
                listReturn.add(t);
            }
        }
        return listReturn;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(List<T> list)
    {
        T[] arr = null;
        if (!isEmpty(list))
        {
            T item = list.get(0);
            if (item != null)
            {
                Class<?> clazzItem = item.getClass();
                arr = (T[]) Array.newInstance(clazzItem, list.size());
                list.toArray(arr);
            }
        }
        return arr;
    }

    public static <T> List<List<T>> splitList(List<T> listModel, int countPerList)
    {
        List<List<T>> listGroupModel = new ArrayList<List<T>>();
        List<T> listPageModel = new ArrayList<T>();

        if (listModel != null && !listModel.isEmpty())
        {
            for (int i = 0; i < listModel.size(); i++)
            {
                listPageModel.add(listModel.get(i));
                if (i != 0)
                {
                    if ((i + 1) % (countPerList) == 0)
                    {
                        listGroupModel.add(listPageModel);
                        listPageModel = new ArrayList<T>();
                    }
                }
            }

            if (listPageModel.size() > 0)
            {
                listGroupModel.add(listPageModel);
            }
        }
        return listGroupModel;
    }

    public static <T> List<List<T>> splitListLinked(List<T> listModel, int countPerList)
    {
        List<List<T>> listGroupModel = new ArrayList<List<T>>();
        List<T> listPageModel = new ArrayList<T>();

        if (listModel != null && !listModel.isEmpty())
        {
            boolean needBackIndex = false;
            for (int i = 0; i < listModel.size(); i++)
            {
                if (needBackIndex)
                {
                    needBackIndex = false;
                    listPageModel.add(listModel.get(i - 1));
                } else
                {
                    listPageModel.add(listModel.get(i));
                }

                if (i != 0)
                {
                    if ((i + 1) % (countPerList) == 0)
                    {
                        needBackIndex = true;
                        listGroupModel.add(listPageModel);
                        listPageModel = new ArrayList<T>();
                    }
                }
            }

            if (listPageModel.size() > 0)
            {
                listGroupModel.add(listPageModel);
            }
        }
        return listGroupModel;
    }

    public static <T> List<T> subList(List<T> list, int start, int end)
    {
        List<T> listReturn = null;
        if (end >= start && isIndexLegal(list, start) && isIndexLegal(list, end))
        {
            listReturn = new ArrayList<T>();
            for (int i = start; i <= end; i++)
            {
                T t = list.get(i);
                listReturn.add(t);
            }
        }
        return listReturn;
    }

    public static <T> List<T> subList(List<T> list, int start)
    {
        List<T> listReturn = null;
        if (isIndexLegal(list, start))
        {
            listReturn = new ArrayList<T>();
            for (int i = start; i < list.size(); i++)
            {
                T t = list.get(i);
                listReturn.add(t);
            }
        }
        return listReturn;
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
}
