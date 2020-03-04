package io.jp.awesomemvp;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WebContentService {

    @GET
    Observable<ResponseBody> getRawContent(@Url String url);
}
