package com.sd.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sd.lib.utils.FDurationFormatter;
import com.sd.lib.utils.FKeyboardUtil;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        final long targetTime = calendar.getTime().getTime();
        final String format = new FDurationFormatter(targetTime).format();
        Log.i(TAG, format);
    }

    @Override
    public void onClick(View v)
    {
        FKeyboardUtil.hide(this);
    }
}
