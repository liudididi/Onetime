package com.liu.asus.yikezhong;

/**
 * Created by 地地 on 2017/11/14.
 * 邮箱：461211527@qq.com.
 */
import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.FeedbackCmdMessage;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.igexin.sdk.message.SetTagCmdMessage;
public class DemoIntentService extends GTIntentService  {

    public DemoIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        System.out.println("aaass ");
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        System.out.println("aaas ");
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        System.out.println("aaassss ");
        System.out.println("onReceiveClientId -> " + "clientid = " + clientid);
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        System.out.println("aaasss ");
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
    }

}
