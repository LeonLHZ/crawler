package com.lhz.crawler.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.UUID;

/**
 * @author Neo
 * @date 2020/6/3 22:29
 */
public class HttpUtils
{
    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
    private static CloseableHttpClient httpClient;

    static
    {
        poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        poolingHttpClientConnectionManager.setMaxTotal(100);
        //设置每个主机的最大连接数
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(10);
        httpClient = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).build();
    }

    public HttpUtils()
    {

    }

    public static String doGetHtml(String url)
    {
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try
        {
            response = httpClient.execute(httpGet);
            if (200==(response.getStatusLine().getStatusCode()))
            {
                HttpEntity entity = response.getEntity();
                if (entity != null)
                {
                    return EntityUtils.toString(entity, "utf-8");
                }


            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (response != null)
                {
                    response.close();
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String doGetImg(String url)
    {
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try
        {
            response = httpClient.execute(httpGet);
            if ("200".equals(response.getStatusLine().getStatusCode()))
            {
                HttpEntity entity = response.getEntity();
                if (entity != null)
                {
                    String extName = url.substring(url.lastIndexOf("."));
                    String picName = UUID.randomUUID().toString() + extName;
                    OutputStream fileOutputStream = new FileOutputStream(new File(""));
                    entity.writeTo(fileOutputStream);
                    return picName;

                }


            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (response != null)
                {
                    response.close();
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return "";
    }

    private static RequestConfig getConfig()
    {
        //配置请求信息
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000)//创建连接的最长时间，单位是毫秒
                .setConnectionRequestTimeout(500)//设置获取链接的最长时间单位是毫秒
                .setSocketTimeout(1000)//设置数据传输的最长时间单位是毫秒
                .build();
        return requestConfig;
    }
}
