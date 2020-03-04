package io.jp.awesomemvp;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public final class WebContentServiceFactory {

    public static WebContentService singleInstance() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://www.robinhood.com")
                .build().create(WebContentService.class);
    }
}
