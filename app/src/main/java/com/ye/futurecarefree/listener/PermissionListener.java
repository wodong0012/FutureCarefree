package com.ye.futurecarefree.listener;

import java.util.List;

/**
 * Create Time : 2020-06-17
 * Author : WoDong
 * Desc :
 */
public interface PermissionListener {
    /**
     * 获取到权限的回调
     */
    void onGranted();

    /**
     * 被拒绝的全新啊
     * @param deniedPermissions 被拒绝的权限集合
     */
    void onDenied(List<String> deniedPermissions);
}
