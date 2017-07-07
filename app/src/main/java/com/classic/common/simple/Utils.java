package com.classic.common.simple;

import android.os.Build;
import android.view.View;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 应用名称: MultipleStatusView
 * 包 名 称: com.classic.common.simple
 *
 * 文件描述: TODO
 * 创 建 人: 续写经典
 * 创建时间: 2017/7/7 15:51
 */
final class Utils {

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    static int generateViewId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return View.generateViewId();
        }
        for (;;) {
            final int result = sNextGeneratedId.get();
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

}
