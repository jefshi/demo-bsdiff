package com.demo.bsdiff;

import android.support.annotation.NonNull;

import java.io.File;

public class BsdiffUtil {

    static {
        System.loadLibrary("bsdiff-lib");
    }

    /**
     * @see #bspatch(String, String, String)
     */
    public static int bspatch(@NonNull File old, @NonNull File out, @NonNull File patch) {
        return bspatch(old.getAbsolutePath(),
                out.getAbsolutePath(),
                patch.getAbsolutePath());
    }

    /**
     * 命令：./bspatch old.apk out.apk PATCH.patch
     *
     * @param old   old.apk：旧版本包（相同签名）
     * @param out   new.apk：新版本包（相同签名）
     * @param patch PATCH.patch：增量包
     * @return 上述命令返回值，0：表示正常
     */
    private static native int bspatch(String old, String out, String patch);
}
