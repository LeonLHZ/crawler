package com.lhz.worm;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author Neo
 * @date 2020/5/18 21:49
 */
public class HttpPostDemo
{
    public HttpPostDemo()
    {
    }

    public static void post()
    {
        //1.打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址
        HttpPost httpPost = new HttpPost("http://www.baidu.com");
        httpPost.setConfig(HttpUtils.config());
        HttpUtils.response(httpClient, null, httpPost);

    }

    public static void postParams() throws UnsupportedEncodingException
    {
        //1.打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址
        HttpPost httpPost = new HttpPost("http://yun.itheima.com/search");
        httpPost.setConfig(HttpUtils.config());
        HttpUtils.response(httpClient, null, httpPost);

        ArrayList<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("keys", "java"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,"utf-8");
        httpPost.setEntity(entity);
        HttpUtils.response(httpClient, null, httpPost);
    }

    public static void main(String[] args) throws UnsupportedEncodingException
    {
        postParams();
    }
}
