package com.example.a59436.myapplication;

/**
 * Created by Administrator on 2016/11/3.
 */
public interface ISupportOkHttp<E> {
    /**
     * 请求完成
     */
    public void requestCompleted(String task_name, E params);
    /**
     * 请求失败
     */
    public void requestEndedWithError(String task_name, String error);
    /**
     * 请求超时
     */
    public void requestTimeOutError(String task_name, String error);
}
