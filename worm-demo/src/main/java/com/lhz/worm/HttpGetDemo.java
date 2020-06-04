package com.lhz.worm;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.*;

import java.net.URISyntaxException;

/**
 * @author Neo
 * @date 2020/5/18 20:57
 */
public class HttpGetDemo
{
    public HttpGetDemo()
    {
    }


    public static void get()
    {
        //1.打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址
        HttpGet httpGet = new HttpGet("http://www.baidu.com");

        httpGet.setConfig(HttpUtils.config());
        HttpUtils.response(httpClient, httpGet,null);

    }


    public static void getByParams() throws URISyntaxException
    {
        //1.打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();

        URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/search");
        uriBuilder.setParameter("keys", "java");
        //2.输入网址
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setConfig(HttpUtils.config());
        HttpUtils.response(httpClient, httpGet,null);

    }




    public static void main(String[] args)throws Exception
    {
        getByParams();
    }


}
