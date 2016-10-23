package com.hardwork.fg607.jystocks.presenter;

import com.hardwork.fg607.jystocks.model.SHCData;
import com.hardwork.fg607.jystocks.model.StockDataProvider;
import com.hardwork.fg607.jystocks.view.PreferView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by fg607 on 16-9-9.
 */
public class PreferPresenter implements Presenter<PreferView> {

    private PreferView mView;

    @Override
    public void attachView(PreferView view) {

        mView = view;
    }

    public void getSHCData(){

        StockDataProvider.getInstance().getSHCData(new Subscriber<List<SHCData>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<SHCData> shcDatas) {

                mView.updateSHC(shcDatas);
            }
        });
    }
}
