package io.jp.awesomemvp.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import io.jp.mvp.BasePresenter;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GeneratePIPresenter extends BasePresenter<GeneratePIModel, GeneratePIContract.IGeneratePIView> implements GeneratePIContract.IGeneratePIPresenter {

    @Override
    public void attachView(@NonNull GeneratePIContract.IGeneratePIView view, @Nullable GeneratePIModel model) {
        super.attachView(view, model);
        if (model != null) {
            runViewAction(v -> v.updatePIList(model.getPIs()));
        }
    }

    @Override
    public void generatePIs() {
        Observable.interval(3, TimeUnit.SECONDS)
                .startWithItem(1L)
                .map(this::generatePI)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(num -> {
                    getModel().addPI(num+"");
                    runViewAction(view -> view.updatePIList(getModel().getPIs()));
                });
    }

    private double generatePI(long num) {
        double sum = 0;
        for(double i=0; i< num; i++)
        {
            if(i%2 == 0) // if the remainder of `i/2` is 0
                sum += -1 / ( 2 * i - 1);
            else
                sum += 1 / (2 * i - 1);
        }
        return sum;
    }
}
