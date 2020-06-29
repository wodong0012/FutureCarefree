package com.ye.futurecarefree.mvp.view;

import java.io.InputStream;

public interface ILaunchView extends IBaseView {

    /**
     * 异步加载之后的回调
     * @param byteStream
     */
    void LaunchPosterResponse(InputStream byteStream);

}
