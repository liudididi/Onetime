package com.liu.asus.yikezhong;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapter.LiaoTianAdapter;
import bean.LiaoTianBean;
import mybase.BaseActivity;
import mybase.Basepresent;

public class LiaoTianActivity extends BaseActivity implements EMMessageListener {
    // 聊天信息输入框
    private EditText mInputEdit;
    // 发送按钮
    private Button mSendBtn;

    // 显示内容的 TextView
    private TextView mContentText;

    // 消息监听器
    private EMMessageListener mMessageListener;
    // 当前聊天的 ID
    private String mChatId;
    // 当前会话对象
    private EMConversation mConversation;
    private List<LiaoTianBean> list;
    private ListView lt_list;
    private String dicon;

    private LiaoTianAdapter adapter;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_liao_tian;
    }

    @Override
    public void init() {

        mChatId = getIntent().getStringExtra("mobile");
        dicon = getIntent().getStringExtra("dicon");
        mMessageListener = this;
        list = new ArrayList<>();

        initView();
        initConversation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.clear();
        list=null;
    }

    private void initView() {
        lt_list = findViewById(R.id.lt_list);
        mInputEdit = (EditText) findViewById(R.id.ec_edit_message_input);
        mSendBtn = (Button) findViewById(R.id.ec_btn_send);


        // 设置发送按钮的点击事件
        mSendBtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                String content = mInputEdit.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    mInputEdit.setText("");
                    // 创建一条新消息，第一个参数为消息内容，第二个为接受者username
                    EMMessage message = EMMessage.createTxtSendMessage(content, mChatId);
                    message.setChatType(EMMessage.ChatType.Chat);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Long l = Long.valueOf(mConversation.getLastMessage().getMsgTime());
                    String timeString = sdf.format(new Date(l));
                    // 将新的消息内容和时间加入到下边
                   LiaoTianBean liaoTianBean=new LiaoTianBean(0,content,timeString);
                    if(adapter==null){
                        adapter= new LiaoTianAdapter(LiaoTianActivity.this,list,dicon);
                        lt_list.setAdapter(adapter);
                    }else {
                        adapter.Refresh(liaoTianBean);
                    }

                    EMClient.getInstance().chatManager().sendMessage(message);
                    // 为消息设置回调
                    message.setMessageStatusCallback(new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            // 消息发送成功，打印下日志，正常操作应该去刷新ui
                            Log.i("lzan13", "send message on success");
                        }

                        @Override
                        public void onError(int i, String s) {
                            // 消息发送失败，打印下失败的信息，正常操作应该去刷新ui
                            Log.i("lzan13", "send message on error " + i + " - " + s);
                        }

                        @Override
                        public void onProgress(int i, String s) {
                            // 消息发送进度，一般只有在发送图片和文件等消息才会有回调，txt不回调
                        }
                    });
                }
            }
        });
    }

    /**
     * 初始化会话对象，并且根据需要加载更多消息
     */
    private void initConversation() {

        /**
         * 初始化会话对象，这里有三个参数么，
         * 第一个表示会话的当前聊天的 useranme 或者 groupid
         * 第二个是绘画类型可以为空
         * 第三个表示如果会话不存在是否创建
         */
        mConversation = EMClient.getInstance().chatManager().getConversation(mChatId, null, true);
        // 设置当前会话未读数为 0
        mConversation.markAllMessagesAsRead();
        int count = mConversation.getAllMessages().size();
        if (count < mConversation.getAllMsgCount() && count < 20) {
            // 获取已经在列表中的最上边的一条消息id
            String msgId = mConversation.getAllMessages().get(0).getMsgId();
            // 分页加载更多消息，需要传递已经加载的消息的最上边一条消息的id，以及需要加载的消息的条数
            mConversation.loadMoreMsgFromDB(msgId, 20 - count);
        }
        // 打开聊天界面获取最后一条消息内容并显示
        if (mConversation.getAllMessages().size() > 0) {
            EMMessage messge = mConversation.getLastMessage();
            EMTextMessageBody body = (EMTextMessageBody) messge.getBody();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long l = Long.valueOf(mConversation.getLastMessage().getMsgTime());
            String timeString = sdf.format(new Date(l));
            LiaoTianBean liaoTianBean=new LiaoTianBean(1,body.getMessage(),timeString);
            list.add(liaoTianBean);
            if(adapter==null){
                adapter= new LiaoTianAdapter(LiaoTianActivity.this,list,dicon);
                lt_list.setAdapter(adapter);
            }else {
                adapter.Refresh(liaoTianBean);
            }


          /*  // 将消息内容和时间显示出来
            mContentText.setText("聊天记录：" + body.getMessage() + " - time: " + timeString);*/
        }
    }

    /**
     * 自定义实现Handler，主要用于刷新UI操作
     */
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    EMMessage message = (EMMessage) msg.obj;
                    // 这里只是简单的demo，也只是测试文字消息的收发，所以直接将body转为EMTextMessageBody去获取内容
                    EMTextMessageBody body = (EMTextMessageBody) message.getBody();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Long l = Long.valueOf(mConversation.getLastMessage().getMsgTime());
                    String timeString = sdf.format(new Date(l));
                    // 将新的消息内容和时间加入到下边
                    LiaoTianBean liaoTianBean=new LiaoTianBean(1,body.getMessage(),timeString);
                    if(adapter==null){
                        adapter= new LiaoTianAdapter(LiaoTianActivity.this,list,dicon);
                        lt_list.setAdapter(adapter);
                    }else {
                        adapter.Refresh(liaoTianBean);
                    }
/*
                    mContentText.setText(mContentText.getText() + "\n接收：" + body.getMessage() + " - time: " +timeString);
*/
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        // 添加消息监听
        EMClient.getInstance().chatManager().addMessageListener(mMessageListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 移除消息监听
        EMClient.getInstance().chatManager().removeMessageListener(mMessageListener);
    }
    @Override
    public void onMessageReceived(List<EMMessage> list) {
        // 循环遍历当前收到的消息
        for (EMMessage message : list) {
            if (message.getFrom().equals(mChatId)) {
                // 设置消息为已读
                mConversation.markMessageAsRead(message.getMsgId());

                // 因为消息监听回调这里是非ui线程，所以要用handler去更新ui
                Message msg = mHandler.obtainMessage();
                msg.what = 0;
                msg.obj = message;
                mHandler.sendMessage(msg);
            } else {
                // 如果消息不是当前会话的消息发送通知栏通知
            }
        }
    }

    /**
     * 收到新的 CMD 消息
     *
     * @param list
     */
    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {
        for (int i = 0; i < list.size(); i++) {
            // 透传消息
            EMMessage cmdMessage = list.get(i);
            EMCmdMessageBody body = (EMCmdMessageBody) cmdMessage.getBody();
            Log.i("lzan13", body.action());
        }
    }

    @Override
    public void onMessageRead(List<EMMessage> list) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> list) {

    }

    @Override
    public void onMessageRecalled(List<EMMessage> list) {

    }

    /**
     * 收到新的已读回执
     *
     * @param list 收到消息已读回执
     */

    /**
     * 收到新的发送回执
     * TODO 无效 暂时有bug
     *
     * @param list 收到发送回执的消息集合
     */
    /**
     * 消息的状态改变
     *
     * @param message 发生改变的消息
     * @param object  包含改变的消息
     */
    @Override
    public void onMessageChanged(EMMessage message, Object object) {
    }

    @Override
    public void ondestory() {

    }
}
