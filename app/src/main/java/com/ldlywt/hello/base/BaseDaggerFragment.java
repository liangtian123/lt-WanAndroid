package com.ldlywt.hello.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ldlywt.hello.App;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseDaggerFragment<T extends BaseContract.BasePresenter> extends RxFragment implements BaseContract.BaseView {
    //凡是继承BaseDaggerFragment的fragment一创建时就获得了Presenter，
    @Inject
    @Nullable
    protected T mPresenter;

    private Unbinder unbinder;
    private View mRootView, mErrorView, mEmptyView;
    private ProgressDialog mProgressDialog;
    //凡是继承BaseDaggerFragment的fragment一创建时就把自己给了Presenter，
    // 为什么这里不用dagger注入？因为已经没有耦合了，不需要new，所以不需要解耦，依赖注入的核心原则：
    // 一个类不应该知道如何实现依赖注入。
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        detachView();
    }

    /**
     * 分离view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 贴上view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflaterView(inflater, container);
        unbinder = ButterKnife.bind(this, mRootView);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage(getResources().getString(com.chad.library.R.string.loading));
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    protected abstract void initData();

    /**
     * 设置View
     *
     * @param inflater
     * @param container
     */
    private void inflaterView(LayoutInflater inflater, @Nullable ViewGroup container) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }
    }

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showSuccess() {
    }

    @Override
    public void showFailed() {
        Toast.makeText(App.getInstance(), "加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoNet() {
        Toast.makeText(App.getInstance(), "无网络链接", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRetry() {
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }
}
