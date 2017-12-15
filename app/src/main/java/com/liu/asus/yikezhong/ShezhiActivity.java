package com.liu.asus.yikezhong;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import bean.VisionBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import commpont.DaggerUpvisioncommpont;
import mInterface.UpVisonv;
import mybase.BaseActivity;
import mybase.Basepresent;
import mymodules.Upvisionmoudule;
import present.UpVisonP;
import utils.ClearCacheUtils;
import utils.SPUtils;

public class ShezhiActivity extends BaseActivity implements UpVisonv {
     @Inject
    UpVisonP upVisonP;
    @BindView(R.id.shezhi_rl_clear_huncun)
    RelativeLayout shezhiRlClearHuncun;
    @BindView(R.id.shezhi_login_clear)
    Button shezhiLoginClear;
    @BindView(R.id.she_tv_hcnum)
    TextView sheTvHcnum;
    @BindView(R.id.shezhi_back)
    ImageView shezhiBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.shezhi_rl_check_version)
    RelativeLayout shezhiRlCheckVersion;
    Handler m_mainHandler;
    ProgressDialog m_progressDlg;

    @Override
    public List<Basepresent> initp() {
        return null;
    }

    @Override
    public int getid() {
        return R.layout.activity_shezhi;
    }

    @Override
    public void init() {
        m_mainHandler = new Handler();
        m_progressDlg =  new ProgressDialog(this);
        m_progressDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
        m_progressDlg.setIndeterminate(false);
        DaggerUpvisioncommpont.builder().upvisionmoudule(new Upvisionmoudule(this)).build().upject(this);
        try {
            String totalCacheSize = ClearCacheUtils.getdqSize(this);
            sheTvHcnum.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void ondestory() {

    }


    @OnClick({R.id.shezhi_rl_clear_huncun, R.id.shezhi_login_clear, R.id.shezhi_back, R.id.tv_back,R.id.shezhi_rl_check_version})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shezhi_rl_clear_huncun:
                try {

                    AlertDialog.Builder ad = new AlertDialog.Builder(this)
                            .setTitle("是否清除缓存")
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ClearCacheUtils.clearAllCache(ShezhiActivity.this);
                                    try {
                                        ClearCacheUtils.clearAllCache(ShezhiActivity.this);
                                        String totalCacheSize = ClearCacheUtils.getdqSize(ShezhiActivity.this);
                                        sheTvHcnum.setText(totalCacheSize);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                    ad.show();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.shezhi_login_clear:
                SPUtils.clear(this);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.shezhi_back:
                finish();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.shezhi_rl_check_version:

                upVisonP.getupvision();
                 break;
        }
    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String msg) {

    }

    @Override
    public void upsuccess(final VisionBean visionBean) {
        Toast(visionBean.versionCode);
        try {
            PackageInfo   Common = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            int verCode = Common.versionCode;
            System.out.println("verCode==="+verCode);
            String verName =Common.versionName;
            if(Common.versionCode==Integer.parseInt(visionBean.versionCode)){
                 Toast("已经是最新版本");
                 return;
            }
            String str= "当前版本："+verName+" Code:"+verCode+" ,发现新版本："+visionBean.versionName+
                    " Code:"+visionBean.versionCode+" ,是否更新？";

            Dialog dialog = new AlertDialog.Builder(this).setTitle("软件更新").setMessage(str)
                    // 设置内容
                    .setPositiveButton("更新",// 设置确定按钮
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    m_progressDlg.setTitle("正在下载");
                                    m_progressDlg.setMessage("请稍候...");
                                    downFile(visionBean.apkUrl);  //开始下载
                                }
                            })
                    .setNegativeButton("暂不更新",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    // 点击"取消"按钮之后退出程序

                                                                    }
                            }).create();// 创建
            // 显示对话框
            dialog.show();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }




    }

    private void downFile(final String url) {
        m_progressDlg.show();
        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    long length = entity.getContentLength();

                    m_progressDlg.setMax((int)length);//设置进度条的最大值

                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory(),
                                "yikezhong.apk");
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];
                        int ch = -1;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                                m_progressDlg.setProgress(count);//设置当前进度
                            }
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    down();  //告诉HANDER已经下载完成了，可以安装了
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();



    }

    private void down() {
        m_mainHandler.post(new Runnable() {
            public void run() {
                m_progressDlg.cancel();
                update();
            }
        });

    }
    void update() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), "yikezhong.apk")),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
