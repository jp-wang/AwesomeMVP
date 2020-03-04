package io.jp.awesomemvp;

import io.jp.mvp.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author jpwang
 * @since 8/16/16
 */
public class MainPresenter extends BasePresenter<Void, MainContract.IMainView> implements MainContract.IMainPresenter {
    @Override
    public void calculatePI(long terms) {
        Observable.just(terms)
                .subscribeOn(Schedulers.computation())
                .map(new Function<Long, Double>() {
                    @Override
                    public Double apply(Long integer) {
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
                .subscribe(aDouble -> runViewAction(view -> view.showToastMessage("PI = " + aDouble)),
                        throwable -> runViewAction(view -> view.showToastMessage(throwable.getMessage())));
    }
}
