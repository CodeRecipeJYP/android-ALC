package khs.study.alc_android.common.presenter;

import khs.study.alc_android.common.model.BaseService;
import khs.study.alc_android.common.view.BaseView;

/**
 * Created by jaeyoung on 2017. 3. 26..
 */

public interface BasePresenter<T,U> {
    void attachView(T view);
    void detachView();
    void setService(U service);
}
