package com.fanwe.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fanwe.lib.utils.extend.BitmapLruCache;

public class MainActivity extends AppCompatActivity
{
    public static final BitmapLruCache BITMAP_CACHE = new BitmapLruCache();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
