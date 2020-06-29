package com.ye.futurecarefree.http.retrofit;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface LaunchService {
    String LAUNCH_POSTER = "launch_a.png";

    /**
     * 获取启动页面的广告
     * @return
     */
    @GET(LAUNCH_POSTER)
    Observable<ResponseBody> getLaunchPoster();
}
