package com.template.app.exception.defaultexceptionhandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

import timber.log.Timber;


public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final Thread.UncaughtExceptionHandler defaultExceptionHandler;
    private final String appVersion;
    private final String filesPath;
    private final String androidVersion;
    private final String phoneModel;

    DefaultExceptionHandler(Thread.UncaughtExceptionHandler var1,
                            String appVersion,
                            String filesPath,
                            String androidVersion,
                            String phoneModel) {
        this.defaultExceptionHandler = var1;
        this.appVersion = appVersion;
        this.filesPath = filesPath;
        this.androidVersion = androidVersion;
        this.phoneModel = phoneModel;
    }

    public void uncaughtException(Thread var1, Throwable var2) {
        StringWriter var3 = new StringWriter();
        PrintWriter var4 = new PrintWriter(var3);
        var2.printStackTrace(var4);

        try {
            Random var5 = new Random();
            int var6 = var5.nextInt(99999);
            String var7 = appVersion + "-" + var6;
            Timber.i("UNHANDLED_EXCEPTION - Writing unhandled exception to: %s/%s/stacktrace", filesPath, var7);
            BufferedWriter var8 = new BufferedWriter(new FileWriter(filesPath + "/" + var7 + ".stacktrace"));
            var8.write(androidVersion + "\n");
            var8.write(phoneModel + "\n");
            var8.write(var3.toString());
            var8.flush();
            var8.close();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        Timber.i("UNHANDLED_EXCEPTION %s", var3.toString());
        this.defaultExceptionHandler.uncaughtException(var1, var2);
    }

}
