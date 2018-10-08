package com.ldlywt.hello.ui.project;

import com.ldlywt.hello.base.BaseContract;
import com.ldlywt.hello.bean.ProjectBean;
import com.ldlywt.hello.bean.TreeBean;

import java.util.List;

/**
 * <pre>
 *     author : lt
 *     e-mail : androidlt@163.com
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ProjectContract {

    interface View extends BaseContract.BaseView {
        void updateProjectView(ProjectBean data);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getProject(int page);
    }

}
