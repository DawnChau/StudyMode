package com.lxy.studymode;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.List;

/**
 * Created by DawnChau_ZFX on 2016/2/11.
 */
public class MonitorService extends Service {

    boolean threadDisable;
    boolean isAppOnForeground;

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            public void run() {
                while (!threadDisable) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {

                    }
                    //执行业务逻辑
                    if(!isAppOnForeground()) {
                        Log.v("MonitorService", "No");
                        Intent start = new Intent(getBaseContext(), MainActivity.class);
                        start.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(start);
                    } else
                        Log.v("MonitorService", "Yes");
                }
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.threadDisable = true;
    }

    //判断当前app是否运行在前台
    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }
}
