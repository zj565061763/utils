package com.fanwe.lib.utils.extend;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.fanwe.lib.utils.encrypt.FMD5Util;

public class BitmapLruCache
{
    private static final int DEFAULT_MEMORY_CACHE_SIZE = 4 * 1024 * 1024;

    private LruCache<String, Bitmap> mCache;

    public BitmapLruCache()
    {
        this(DEFAULT_MEMORY_CACHE_SIZE);
    }

    /**
     * @param maxSize 缓存的最大值
     */
    public BitmapLruCache(int maxSize)
    {
        mCache = new LruCache<String, Bitmap>(maxSize)
        {
            @Override
            protected int sizeOf(String key, Bitmap value)
            {
                return value.getByteCount();
            }
        };
    }

    private String createKey(String url)
    {
        return FMD5Util.MD5(url);
    }

    public boolean put(String url, Bitmap bitmap)
    {
        try
        {
            mCache.put(createKey(url), bitmap);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public Bitmap get(String url)
    {
        try
        {
            return mCache.get(createKey(url));
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void clear()
    {
        mCache.evictAll();
    }
}
