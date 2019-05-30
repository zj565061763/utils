package com.sd.lib.utils.context;

import android.content.Context;
import android.location.LocationManager;

import com.sd.lib.context.FContext;

/**
 * 系统工具类
 */
public class FSystemUtil extends FContext
{
    private FSystemUtil()
    {
    }

    /**
     * 定位服务是否开启
     *
     * @return
     */
    public static boolean isLocationServiceEnabled()
    {
        LocationManager locationManager = (LocationManager) get().getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return isGpsEnable || isNetworkEnable;
    }
}
