package khs.study.alc_android.common.view;

import android.view.View;

import khs.study.alc_android.common.presenter.BasePresenter;

/**
 * Created by jaeyoung on 2017. 3. 26..
 */

public interface BaseView<T> {
    void setMotherView(View motherView);
    void setPresenter(T presenter);
}
