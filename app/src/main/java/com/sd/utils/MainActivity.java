package com.sd.utils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.sd.lib.utils.FDurationFormatter;
import com.sd.lib.utils.FKeyboardUtil;
import com.sd.lib.utils.FMathUtil;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testDurationFormatter();
        testTransformBoundsValue();
    }

    @Override
    public void onClick(View v)
    {
        FKeyboardUtil.hide(this);
    }

    private void testDurationFormatter()
    {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        final long targetTime = calendar.getTime().getTime();

        final String format = new FDurationFormatter(targetTime).format();
        Log.i(TAG, format);
    }

    private void testTransformBoundsValue()
    {
        final double value = FMathUtil.transformBoundsValue(0, 100, 0, 1, 50);
        Log.i(TAG, "testTransformBoundsValue:" + value);
    }
}
