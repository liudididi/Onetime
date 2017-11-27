package com.liu.asus.yikezhong;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.igexin.sdk.PushManager;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static utils.L.isDebug;

/**
 * Created by 地地 on 2017/11/14.
 * 邮箱：461211527@qq.com.
 */

public class Myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        AutoLayoutConifg.getInstance().useDeviceSize();
        //个推
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
           //工具
        if (LeakCanary.isInAnalyzerProcess(this)) {
                        return;
                   }
         LeakCanary.install(this);
        //友盟
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType. E_UM_NORMAL );

        MobclickAgent. startWithConfigure( new MobclickAgent.UMAnalyticsConfig(this,"5a0a59f1b27b0a2ed80002ce",""));

        CrashReport.initCrashReport(getApplicationContext(), "636455fdc3", true);
        Context context = getApplicationContext();

// 获取当前包名
        String packageName = context.getPackageName();
// 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
// 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
// 初始化Bugly
        CrashReport.initCrashReport(context, "636455fdc3", true, strategy);

    }
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
