package khs.study.alc_android.common.model;

import khs.study.alc_android.common.presenter.BasePresenter;

/**
 * Created by jaeyoung on 2017. 3. 26..
 */

public interface BaseService<T> {
    void setPresenter(T presenter);
}
