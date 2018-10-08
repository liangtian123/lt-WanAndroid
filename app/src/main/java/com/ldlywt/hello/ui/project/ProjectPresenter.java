package com.ldlywt.hello.ui.project;

import com.blankj.utilcode.util.ToastUtils;
import com.ldlywt.hello.base.BasePresenter;
import com.ldlywt.hello.bean.BaseResult;
import com.ldlywt.hello.bean.ProjectBean;
import com.ldlywt.hello.bean.TreeArticleBean;
import com.ldlywt.hello.bean.TreeBean;
import com.ldlywt.hello.ui.tree.TreeContract;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * <pre>
 *     author : lt
 *     e-mail : androidlt@163.com
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {

    private Disposable mDisposable;

    @Inject
    ProjectPresenter() {
    }


    @Override
    public void getProject(int page) {
        mDisposable = EasyHttp
                .get("/article/list/" + page + "/json")
                .params("cid", String.valueOf(294))
                .execute(new CallBackProxy<BaseResult<ProjectBean>, ProjectBean>(new SimpleCallBack<ProjectBean>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtils.showShort(e.getMessage());
                        mView.updateProjectView(null);
                    }

                    @Override
                    public void onSuccess(ProjectBean bean) {
                        mView.updateProjectView(bean);
                    }
                }) {
                });
    }

    @Override
    public void detachView() {
        super.detachView();
        EasyHttp.cancelSubscription(mDisposable);
    }
}
