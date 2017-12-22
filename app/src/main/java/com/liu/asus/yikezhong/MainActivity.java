package com.liu.asus.yikezhong;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.meg7.widget.CircleImageView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.umeng.analytics.MobclickAgent;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import adapter.GlideLoader;
import bean.UserBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import commpont.DaggerMaicommpont;
import fragment.Celeft;
import fragment.Duanzi;
import fragment.Shiping;
import fragment.Tuijian;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mInterface.Lognview;
import mybase.BaseActivity;
import mybase.Basebean;
import mybase.Basepresent;
import mymodules.Mainmoudule;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import present.LognP;
import utils.MyQusetUtils;
import utils.SPUtils;

public class MainActivity extends BaseActivity implements Lognview, Celeft.Ce_iconback {

    @BindView(R.id.img_biji)
    ImageView imgBiji;
    @BindView(R.id.frame_main)
    FrameLayout frameMain;
    @BindView(R.id.frame_left)
    FrameLayout frameLeft;
    @BindView(R.id.img_icon)
    CircleImageView imgIcon;
    @BindView(R.id.main_title)
    TextView mainTitle;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    private DrawerLayout dw;
    @Inject
    LognP lognP;
    private int uid;
    private ArrayList<String> path;
    private CircleImageView ce_icon;
    private Tuijian tuijian;
    private Duanzi duanzi;
    private Shiping shiping;
    private Fragment currentFragment;

    @Override
    public List<Basepresent> initp() {
        List<Basepresent> list = new ArrayList<>();
        list.add(lognP);
        return list;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int getid() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        path = new ArrayList<>();
        dw = findViewById(R.id.dw);
        tuijian = new Tuijian();
        shiping = new Shiping();
        duanzi = new Duanzi();
        DaggerMaicommpont.builder().mainmoudule(new Mainmoudule(this)).build().injectm(this);
        denglu();
        uid = (int) SPUtils.get(this, "uid", 0);
        String token = (String) SPUtils.get(this, "token", "");
        if (uid != 0) {
            lognP.getuser(uid, token);
        } else {
            Glide.with(this).load(R.drawable.raw_1499936862)
                    .into(imgIcon);
        }
        Celeft celeft = new Celeft();
        celeft.setCe_iconback(this);
        dw.closeDrawers();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_left, celeft).commit();
        dw.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerView.setClickable(true);

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case 2131690105:
                        switchFragment(tuijian).commit();
                        mainTitle.setText("推荐");
                        break;
                    case 2131690106:
                        switchFragment(duanzi).commit();
                        mainTitle.setText("段子");
                        break;
                    case 2131690107:
                        switchFragment(shiping).commit();
                        mainTitle.setText("视频");
                        break;
                }
            }
        });
        // dw.setScrimColor(Color.TRANSPARENT);  去除阴影
    }

    private void denglu() {
        String username = (String) SPUtils.get(this, "username", "");
        String userpass = (String) SPUtils.get(this, "userpass", "");
        EMClient.getInstance().login(username, userpass, new EMCallBack() {
            /**
             * 登陆成功的回调
             */
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 加载所有会话到内存
                        EMClient.getInstance().chatManager().loadAllConversations();
                        // 加载所有群组到内存，如果使用了群组的话
                        // EMClient.getInstance().groupManager().loadAllGroups();
                        // 登录成功跳转界面
                        System.out.println("====" + "登录成功");

                    }
                });
            }
            /**
             * 登陆错误的回调
             * @param i
             * @param s
             */
            @Override
            public void onError(final int i, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("lzan13", "登录失败 Error code:" + i + ", message:" + s);
                        /**
                         * 关于错误码可以参考官方api详细说明
                         * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1_e_m_error.html
                         */
                        switch (i) {
                            // 网络异常 2
                            case EMError.NETWORK_ERROR:
                                Toast.makeText(MainActivity.this, "网络错误 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                break;
                            // 无效的用户名 101
                            case EMError.INVALID_USER_NAME:
                                Toast.makeText(MainActivity.this, "无效的用户名 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                break;
                            // 无效的密码 102
                            case EMError.INVALID_PASSWORD:
                                Toast.makeText(MainActivity.this, "无效的密码 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                break;
                            // 用户认证失败，用户名或密码错误 202
                            case EMError.USER_AUTHENTICATION_FAILED:
                                Toast.makeText(MainActivity.this, "用户认证失败，用户名或密码错误 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                break;
                            // 用户不存在 204
                            case EMError.USER_NOT_FOUND:
                                Toast.makeText(MainActivity.this, "用户不存在 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                break;
                            // 无法访问到服务器 300
                            case EMError.SERVER_NOT_REACHABLE:
                                Toast.makeText(MainActivity.this, "无法访问到服务器 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                break;
                            // 等待服务器响应超时 301
                            case EMError.SERVER_TIMEOUT:
                                Toast.makeText(MainActivity.this, "等待服务器响应超时 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                break;
                            // 服务器繁忙 302
                            case EMError.SERVER_BUSY:
                                Toast.makeText(MainActivity.this, "服务器繁忙 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                break;
                            // 未知 Server 异常 303 一般断网会出现这个错误
                            case EMError.SERVER_UNKNOWN_ERROR:
                                Toast.makeText(MainActivity.this, "未知的服务器异常 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "ml_sign_in_failed code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    @Override
    public void ondestory() {
        lognP.destoiry();
        if (path != null) {
            path = null;
        }
    }

    public void onResume() {

        MobclickAgent.onResume(this);

        if (uid != 0) {
            String icon = (String) SPUtils.get(this, "icon", "");
            if (icon != null && icon.length() >= 3) {
                RequestOptions options = new RequestOptions();
                options.diskCacheStrategy(DiskCacheStrategy.NONE);
                options.skipMemoryCache(true);
                options.dontAnimate();
                Glide.with(this).load(icon)
                        .apply(options)
                        .into(imgIcon);
            } else {
                Glide.with(this).load(R.drawable.raw_1499936862)
                        .into(imgIcon);
            }
        } else {
            Glide.with(this).load(R.drawable.raw_1499936862)
                    .into(imgIcon);
        }


        super.onResume();
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @OnClick({R.id.img_icon, R.id.img_biji})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_icon:
                FrameLayout rela_left = findViewById(R.id.frame_left);
                dw.openDrawer(rela_left);
                break;
            case R.id.img_biji:
                intent(MainActivity.this, ChuangzuoActivity.class);
                break;
        }
    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String msg) {
        Toast(msg);
        intent(MainActivity.this, LoginActivity.class);
    }

    @Override
    public void lognsuess(UserBean userBean) {
        SPUtils.put(this, "token", userBean.token);
        if (userBean.icon != null && userBean.icon.length() >= 3) {
            SPUtils.put(this, "icon", userBean.icon);
            RequestOptions options = new RequestOptions();
            options.diskCacheStrategy(DiskCacheStrategy.NONE);
            options.skipMemoryCache(true);
            options.dontAnimate();
            Glide.with(this).load(userBean.icon)
                    .apply(options)
                    .into(imgIcon);
        } else {
            Glide.with(this).load(R.drawable.raw_1499936862)
                    .into(imgIcon);
        }
    }

    @Override
    public void lognfail(String msg) {
        // 开启图片选择器


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            path.clear();
            path.addAll(pathList);

            if (pathList.size() > 0) {
                MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
                build.addFormDataPart("uid", uid + "");
                File file = new File(path.get(0));
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                build.addFormDataPart("file", file.getName(), requestFile);
                List<MultipartBody.Part> parts = build.build().parts();
                new MyQusetUtils.Builder().addConverterFactory()
                        .addCallAdapterFactory().build().getQuestInterface().changeicon(parts)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Basebean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Basebean value) {
                        if (value.code.equals("0")) {
                            Toast(value.msg);
                            SPUtils.put(MainActivity.this, "icon", path.get(0));
                            Glide.with(MainActivity.this).load(path.get(0)).into(ce_icon);
                            Glide.with(MainActivity.this).load(path.get(0)).into(imgIcon);
                        } else {
                            Toast(value.msg);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

            }


        }

    }

    @Override
    public void Changge(CircleImageView icon) {
        this.ce_icon = icon;
        ImageConfig imageConfig
                = new ImageConfig.Builder(
                // GlideLoader 可用自己用的缓存库
                new GlideLoader())
                // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                .steepToolBarColor(getResources().getColor(R.color.status))
                // 标题的背景颜色 （默认黑色）
                .titleBgColor(getResources().getColor(R.color.status))
                // 提交按钮字体的颜色  （默认白色）
                .titleSubmitTextColor(getResources().getColor(R.color.white))
                // 标题颜色 （默认白色）
                .titleTextColor(getResources().getColor(R.color.white))
                // 开启多选   （默认为多选）  (单选 为 singleSelect)
                .singleSelect()
//                        .crop()
                // 多选时的最大数量   （默认 9 张）
                .mutiSelectMaxSize(1)
                // 已选择的图片路径
                .pathList(path)
                // 拍照后存放的图片路径（默认 /temp/picture）
                .filePath("/ImageSelector/Pictures")
                // 开启拍照功能 （默认开启）
                .showCamera()
                .crop()
                .requestCode(101)
                .build();


        ImageSelector.open(MainActivity.this, imageConfig);
    }


    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.frame_main, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }



}
