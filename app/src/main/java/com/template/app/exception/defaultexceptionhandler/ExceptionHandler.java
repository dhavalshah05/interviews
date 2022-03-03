package com.template.app.exception.defaultexceptionhandler;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import timber.log.Timber;


public class ExceptionHandler {

    private static String TAG = "ExceptionsHandler";
    private static String[] stackTraceFileList = null;

    private static String versionName;
    private static String packageName;
    private static String filesPath;
    private static String model;
    private static String androidVersion;

    public ExceptionHandler() {
    }

    public static boolean register(Context var0) {
        Timber.i("Registering default exceptions handler");
        PackageManager var1 = var0.getPackageManager();

        try {
            PackageInfo var2 = var1.getPackageInfo(var0.getPackageName(), 0);
            versionName = var2.versionName;
            packageName = var2.packageName;
            filesPath = var0.getFilesDir().getAbsolutePath();
            model = Build.MODEL;
            androidVersion = Build.VERSION.RELEASE;
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        boolean var4 = false;
        if (searchForStackTraces().length > 0) {
            var4 = true;
        }

        (new Thread() {
            public void run() {
                ExceptionHandler.submitStackTraces();
                UncaughtExceptionHandler var1 = Thread.getDefaultUncaughtExceptionHandler();
                if (var1 != null) {
                    Timber.i("Current handler class= %s", var1.getClass().getName());
                }

                if (!(var1 instanceof DefaultExceptionHandler)) {
                    Thread.setDefaultUncaughtExceptionHandler(
                            new DefaultExceptionHandler(var1, versionName, filesPath, androidVersion, model)
                    );
                }

            }
        }).start();
        return var4;
    }

    private static String[] searchForStackTraces() {
        if (stackTraceFileList != null) {
            return stackTraceFileList;
        } else {
            File var0 = new File(filesPath + "/");
            var0.mkdir();
            FilenameFilter var1 = new FilenameFilter() {
                public boolean accept(File var1, String var2) {
                    return var2.endsWith(".stacktrace");
                }
            };
            return stackTraceFileList = var0.list(var1);
        }
    }

    public static void submitStackTraces() {
        boolean var22 = false;

        String[] var0;
        int var1;
        File var28;
        label166:
        {
            try {
                var22 = true;
                Timber.i("Looking for exceptions in: %s", filesPath);
                var0 = searchForStackTraces();
                if (var0 != null) {
                    if (var0.length <= 0) {
                        var22 = false;
                        break label166;
                    }

                    Timber.i("Found %s stacktracce(s)", var0.length);

                    for (var1 = 0; var1 < var0.length; ++var1) {
                        String var2 = filesPath + "/" + var0[var1];
                        String var3 = var0[var1].split("-")[0];
                        Timber.i("Stacktrace in file %s belongs to version %s", var2, var3);
                        StringBuilder var4 = new StringBuilder();
                        BufferedReader var5 = new BufferedReader(new FileReader(var2));
                        String var6 = null;
                        String var7 = null;
                        String var8 = null;
                        var4.append(System.getProperty("line.separator"));
                        var4.append("====================================================================================================================================");
                        var4.append(System.getProperty("line.separator"));
                        var4.append("====================================================================================================================================");
                        var4.append(System.getProperty("line.separator"));
                        var4.append("ANDROID_VERSION: " + Build.VERSION.RELEASE);
                        var4.append(System.getProperty("line.separator"));
                        var4.append("APP_VERSION: " + versionName);
                        var4.append(System.getProperty("line.separator"));
                        var4.append("APP_PACKAGE: " + packageName);
                        var4.append(System.getProperty("line.separator"));
                        var4.append("Board: " + Build.BOARD);
                        var4.append(System.getProperty("line.separator"));
                        var4.append("Brand: " + Build.BRAND);
                        var4.append(System.getProperty("line.separator"));
                        var4.append("Device: " + Build.DEVICE);
                        var4.append(System.getProperty("line.separator"));
                        var4.append("Model: " + Build.MODEL);
                        var4.append(System.getProperty("line.separator"));
                        SimpleDateFormat mDateFormat = new SimpleDateFormat();
                        mDateFormat.applyPattern("dd/MM/yyyy hh:mm:ss");
                        var4.append("Device Time When Generate Error: " + mDateFormat.format(Calendar.getInstance().getTime()));
                        var4.append(System.getProperty("line.separator"));
                        var4.append("====================================================================================================================================");
                        var4.append(System.getProperty("line.separator"));
                        var4.append("====================================================================================================================================");
                        var4.append(System.getProperty("line.separator"));
                        while ((var6 = var5.readLine()) != null) {
                            if (var7 == null) {
                                var7 = var6;
                            } else if (var8 == null) {
                                var8 = var6;
                            } else {
                                var4.append(var6);
                                var4.append(System.getProperty("line.separator"));
                            }
                        }
                        var4.append(System.getProperty("line.separator"));
                        var4.append("====================================================================================================================================");
                        //var4.append(System.getProperty("line.separator"));
                        //var4.append("http://domainname.com");
                        //var4.append(System.getProperty("line.separator"));
                        //var4.append(G.TraceAVersion);
                        //var4.append(System.getProperty("line.separator"));
                        //var4.append("====================================================================================================================================");
                        var5.close();
                        String var9 = var4.toString();
                        Timber.i("Transmitting stack trace: %s", var9);

                        HashMap<String, String> params = new HashMap<>();
                        params.put("package_name", packageName);
                        params.put("package_version", var3);
                        params.put("phone_model", var8);
                        params.put("android_version", var7);
                        params.put("stacktrace", var9);

                        // Uncomment following line for posting data to backend server
                        //String response = performPostCall(G.URL, params);
                        //DebugLog.INSTANCE.e("ExceptionHandler" + "ERROR RESPONSE :::::" + response);
                    }

                    var22 = false;
                    break label166;
                }

                var22 = false;
                break label166;
            } catch (Exception var26) {
                var26.printStackTrace();
                var22 = false;
            } finally {
                if (var22) {
                    try {
                        String[] var14 = searchForStackTraces();

                        for (int var15 = 0; var15 < var14.length; ++var15) {
                            File var16 = new File(filesPath + "/" + var14[var15]);
                            var16.delete();
                        }
                    } catch (Exception var23) {
                        var23.printStackTrace();
                    }

                }
            }

            try {
                var0 = searchForStackTraces();

                for (var1 = 0; var1 < var0.length; ++var1) {
                    var28 = new File(filesPath + "/" + var0[var1]);
                    var28.delete();
                }

                return;
            } catch (Exception var24) {
                var24.printStackTrace();
                return;
            }
        }

        try {
            var0 = searchForStackTraces();

            for (var1 = 0; var1 < var0.length; ++var1) {
                var28 = new File(filesPath + "/" + var0[var1]);
                var28.delete();
            }
        } catch (Exception var25) {
            var25.printStackTrace();
        }

    }

    public static String performPostCall(String requestURL,
                                         HashMap<String, String> postDataParams) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

}
