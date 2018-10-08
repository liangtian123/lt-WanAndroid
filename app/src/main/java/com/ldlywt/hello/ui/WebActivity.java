package com.ldlywt.hello.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.BarUtils;
import com.just.agentweb.AgentWeb;
import com.ldlywt.hello.R;
import com.ldlywt.hello.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by wong on 2018/4/20.
 */

public class WebActivity extends BaseActivity {
    @BindView(R.id.fl_web)
    FrameLayout flWeb;

    private static final String WEB_URL = "web_url";
    private String mWebUrl;

    AgentWeb mAgentWeb;

    public static void startWebActivity(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WEB_URL, url);
        context.startActivity(intent);
    }




    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {

//        BarUtils.setStatusBarColor(this, Color.parseColor("#00aaaaaa"));
    }

    @Override
    protected void initTitle() {
    }

    @Override
    protected void initData() {
        mWebUrl = getIntent().getStringExtra(WEB_URL);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(flWeb, new FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(mWebUrl);
    }
}
