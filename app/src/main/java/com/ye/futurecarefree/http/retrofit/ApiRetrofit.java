package com.ye.futurecarefree.http.retrofit;

import com.ye.futurecarefree.constants.NetworkConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit {
    private static ApiRetrofit mApiRetrofit;
    private static Retrofit mRetrofit;

    private ApiRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 单例模式获取ApiRetrofit对象
     * @return
     */
    public static ApiRetrofit getInstance(){
        if (mApiRetrofit == null) {
            synchronized (Object.class) {
                if (mApiRetrofit == null) {
                    mApiRetrofit = new ApiRetrofit();
                }
            }
        }
        return mApiRetrofit;
    }

    /**
     * 获取动态的ApiService对象
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getApiService(Class<T> tClass){
       return mRetrofit.create(tClass);
    }


}
