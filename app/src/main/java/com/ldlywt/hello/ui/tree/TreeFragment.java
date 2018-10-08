package com.ldlywt.hello.ui.tree;

import android.os.Bundle;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.ldlywt.hello.R;
import com.ldlywt.hello.base.BaseDaggerFragment;
import com.ldlywt.hello.bean.TreeBean;
import com.ldlywt.hello.ui.tree.adapter.HorizontalPagerAdapter;

import java.util.List;


/**
 * <pre>
 *     author : lex
 *     e-mail : ldlywt@163.com
 *     time   : 2018/08/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TreeFragment extends BaseDaggerFragment<TreePresenter> implements TreeContract.View {

    private HorizontalPagerAdapter mPagerAdapter;

    public static TreeFragment newInstance(String from) {
        TreeFragment fragment = new TreeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {
        mPresenter.getTree();
    }
    //先执行initView再执行initData，所以当下面方法执行时HorizontalInfiniteCycleViewPager已经有
    // 数据了，但是是假数据，详细看源码
    @Override
    protected void initView() {
        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) getView().findViewById(R.id.hicvp);
        mPagerAdapter = new HorizontalPagerAdapter(getContext());
        //执行后就已经有数据了，但是是假数据，在initData时会走getTree>updateTreeView，再给
        // mPagerAdapter设置真数据，所以要mPagerAdapter.notifyDataSetChanged();通知adapter更新数据
        horizontalInfiniteCycleViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tree;
    }

    @Override
    public void updateTreeView(List<TreeBean> data) {
        mPagerAdapter.setList(data);
        //原本已经有假数据了，更新数据
        mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateArticleView(String result) {

    }

    @Override
    public void updateRegisterView(String result) {

    }
}
