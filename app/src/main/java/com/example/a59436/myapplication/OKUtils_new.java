package com.example.a59436.myapplication;

import android.app.Activity;
import android.content.Intent;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;


/**
 * Created by Administrator on 2016/11/2.
 */
public class OKUtils_new {


    /*
	* -403跳登录
	* -406跳学校信息补充界面  现在改成-1027
	* */

    public static void okRequest(final Activity activity, final String task_name, Map<String, String> map, final String url, final OkHttpClient client, final ISupportOkHttp<String> iSupportOkHttp) {
        final Gson gson = new Gson();

        OkHttpUtils
                .get()//
                .url(url)
                .params(map)
                .build()//
                .execute(new MyStringCallback() {


                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null) {
                            iSupportOkHttp.requestEndedWithError(task_name, "neterror");
                            return;
                        }
                        Myresponse myresponse = gson.fromJson(response, Myresponse.class);
                        if (myresponse.getCode() == -403) {
                          /*  Intent i = new Intent(activity, NoPassWordEnterActivity.class);
//                            Intent intent = new Intent();
//                            intent.setAction(BROADCAST_ACTION);
//                            activity.sendBroadcast(intent);
//                            SPUtils.put(activity,"token","");
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_SINGLE_TOP
                                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.putExtra("message1", response);
                            activity.startActivity(i);*/
                            Intent intent1=new Intent();
                            intent1.setAction(Constant.REMOTE_LOGIN);
                            intent1.putExtra("errormsg",myresponse.getErrormsg());
                            activity.sendBroadcast(intent1);
//                            activity.finish();

                        } else if (myresponse.getCode() == -404) {
//                            L.d("myurl", url);
                          /*  Intent intent = new Intent();
                            intent.putExtra("message", response);
                            intent.setAction(BROADCAST_UPDATE);
                            activity.sendBroadcast(intent);
                            iSupportOkHttp.requestCompleted(task_name, Constant.UPDATE);*/
                            Intent intent1=new Intent();
                            intent1.setAction(Constant.UPDATE);
                            intent1.putExtra("errormsg",myresponse.getErrormsg());
                            activity.sendBroadcast(intent1);
                        }
                       /* else if (myresponse.getCode() == -1028){
                            Intent intent = new Intent();
                            intent.putExtra("commessage", response);
                            intent.setAction(BROADCAST_STUDENTINFO);
                            activity.sendBroadcast(intent);
                            iSupportOkHttp.requestCompleted(task_name, ComContents.OTHER_LOGIN);
                        }else if (myresponse.getCode() == -1001){
                            SPUtils.put(activity,"token","");
                            SPUtils.put(activity,"uid","");
                            iSupportOkHttp.requestCompleted(task_name, ComContents.OTHER_LOGIN);
                        }*/
                        else {
                            iSupportOkHttp.requestCompleted(task_name, response);
                        }

                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (e!=null)
                        {
                            if (e.getCause().equals(SocketTimeoutException.class))
                            {
                                iSupportOkHttp.requestTimeOutError(task_name,"");
                            }else
                            {
                                iSupportOkHttp.requestEndedWithError(task_name, e.getMessage());
                            }
                        }


//                        iSupportOkHttp.requestEndedWithError(task_name, e.getMessage());
                    }
                });
    }


    public static void okpostRequest(final Activity activity, final String task_name, Map<String, String> map, final String url, final OkHttpClient client, final ISupportOkHttp<String> iSupportOkHttp) {
        final Gson gson = new Gson();

        OkHttpUtils
                .post()//
                .url(url)//
                .params(map)
                .build()//
                .execute(new MyStringCallback() {


                    @Override
                    public void onResponse(String response, int id) {
//                        Intent loadIntent = new Intent();
//                        loadIntent.setAction(BROADCAST_LOADING);
//                        activity.sendBroadcast(loadIntent);
                        if (response == null) {
                            iSupportOkHttp.requestEndedWithError(task_name, "neterror");
                            return;
                        }
                        Myresponse myresponse = gson.fromJson(response, Myresponse.class);
                        if (myresponse.getCode() == -403) {
//                            Intent i = new Intent(activity, NoPassWordEnterActivity.class);
                           /* SPUtils.put(activity,"token","");
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_SINGLE_TOP
                                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            activity.startActivity(i);*/
                            Intent intent1=new Intent();
                            intent1.setAction(Constant.REMOTE_LOGIN);
                            intent1.putExtra("errormsg",myresponse.getErrormsg());
                            activity.sendBroadcast(intent1);
                        } else if (myresponse.getCode() == -404) {
//                            L.d("myurl", url);

                            Intent intent1=new Intent();
                            intent1.setAction(Constant.UPDATE);
                            intent1.putExtra("errormsg",myresponse.getErrormsg());
                            activity.sendBroadcast(intent1);

                           /* Intent intent = new Intent();
                            intent.putExtra("message", response);
                            intent.setAction(BROADCAST_UPDATE);
                            activity.sendBroadcast(intent);
                            iSupportOkHttp.requestCompleted(task_name, ComContents.OTHER_LOGIN);*/
                        } else {
                            iSupportOkHttp.requestCompleted(task_name, response);
                        }

                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (e.getCause().equals(SocketTimeoutException.class))
                        {
                            iSupportOkHttp.requestTimeOutError(task_name,"");
                        }else
                        {
                            iSupportOkHttp.requestEndedWithError(task_name, e.getMessage());
                        }

                    }
                });
    }


}

abstract class MyStringCallback extends Callback<String> {
    public String parseNetworkResponse(Response response, int id) throws IOException {
        String myresponse = response.body().string();
        if (response.code() == 200) {
            return myresponse;
        } else return null;
    }
}
     


