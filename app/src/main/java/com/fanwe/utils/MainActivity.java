package com.fanwe.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fanwe.lib.utils.FIntentUtil;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = FIntentUtil.getIntentAppSetting(getPackageName());
        startActivity(intent);
    }
}
