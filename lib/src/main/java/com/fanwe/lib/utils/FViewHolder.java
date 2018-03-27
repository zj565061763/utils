package com.fanwe.lib.utils;

import android.util.SparseArray;
import android.view.View;

public class FViewHolder
{
    private FViewHolder()
    {
    }

    public static <T extends View> T get(int id, View convertView)
    {
        SparseArray<View> holder = (SparseArray<View>) convertView.getTag();
        if (holder == null)
        {
            holder = new SparseArray<>();
            convertView.setTag(holder);
        }
        View childView = holder.get(id);
        if (childView == null)
        {
            childView = convertView.findViewById(id);
            holder.put(id, childView);
        }
        return (T) childView;
    }
}
