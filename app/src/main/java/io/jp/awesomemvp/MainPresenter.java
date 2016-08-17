package io.jp.awesomemvp;

import io.jp.mvp.BasePresenter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author jpwang
 * @since 8/16/16
 */
public class MainPresenter extends BasePresenter<Void, MainContract.IMainView> implements MainContract.IMainPresenter {
    @Override
    public void calculatePI(long terms) {
        Observable.just(terms)
                .subscribeOn(Schedulers.computation())
                .map(new Func1<Long, Double>() {
                    @Override
                    public Double call(Long integer) {
                        double sum = 0;
                        for(double i=0; i<integer; i++)
                        {
                            if(i%2 == 0) // if the remainder of `i/2` is 0
                                sum += -1 / ( 2 * i - 1);
                            else
                                sum += 1 / (2 * i - 1);
                        }
                        return sum;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Double>() {
                    @Override
                    public void call(Double aDouble) {
                        getView().showToastMessage("PI = " + aDouble);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().showToastMessage(throwable.getMessage());
                    }
                });
    }
}
