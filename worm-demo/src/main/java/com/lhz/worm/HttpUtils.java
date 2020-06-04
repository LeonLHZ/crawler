package com.lhz.worm;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Neo
 * @date 2020/5/18 21:51
 */
public class HttpUtils
{
    public HttpUtils()
    {
    }

    public static void response(CloseableHttpClient httpClient, HttpGet httpGet, HttpPost httpPost)
    {
        CloseableHttpResponse response = null;
        try
        {

            //3.按回车发起请求，返回响应
            if (Optional.ofNullable(httpGet).isPresent() && !Optional.ofNullable(httpPost).isPresent())
            {
                response = httpClient.execute(httpGet);
            }
            else if (!Optional.ofNullable(httpGet).isPresent() && Optional.ofNullable(httpPost).isPresent())
            {
                response = httpClient.execute(httpPost);
            }
            else
                return;


            //4.解析响应，获取数据
            if (response.getStatusLine().getStatusCode() == 200)
            {

                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity, "utf-8");
                System.out.println(content);
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
                response.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
//使用连接池无需关闭
              /*  try
                {
                    httpClient.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }*/
        }

    }


    public static RequestConfig config(){
        //配置请求信息
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000)//创建连接的最长时间，单位是毫秒
                .setConnectionRequestTimeout(500)//设置获取链接的最长时间单位是毫秒
                .setSocketTimeout(1000)//设置数据传输的最长时间单位是毫秒
                .build();
        return requestConfig;
    }
}
