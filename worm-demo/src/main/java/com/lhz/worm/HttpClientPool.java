package com.lhz.worm;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * @author Neo
 * @date 2020/5/18 22:14
 */
public class HttpClientPool
{
    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;


    static
    {
        poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
                //设置最大连接数
                poolingHttpClientConnectionManager.setMaxTotal(100);
                //设置每个主机的最大连接数
                poolingHttpClientConnectionManager.setDefaultMaxPerRoute(10);
    }

    public HttpClientPool()
    {
    }

    public static void doGet()
    {
        CloseableHttpClient httpClient =
                HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).build();

        //2.输入网址
        HttpGet httpGet = new HttpGet("http://www.baidu.com");

        HttpUtils.response(httpClient, httpGet, null);
    }

    public static void main(String[] args)
    {
        doGet();
    }
}
