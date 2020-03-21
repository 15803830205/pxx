package com.fh.shop.admin.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    public static String sendDelete(String url){
        CloseableHttpClient client = null;
        HttpDelete httpDelete = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
             client = HttpClientBuilder.create().build();
             httpDelete = new HttpDelete(url);
             response = client.execute(httpDelete);
             result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(response,httpDelete,client);
        }
        return result;
    }

    public static String sendPostJson(String url, Map<String, String> params)  {
        CloseableHttpClient client = null;
        HttpPost httpPost = null;
        CloseableHttpResponse execute = null;
        String result = null;
        try {
            client = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
            if(null != params && params.size() > 0){
                String toJSONString = JSONObject.toJSONString(params);
                StringEntity stringEntity = new StringEntity(toJSONString,"utf-8");
                stringEntity.setContentType("application/json;charset=uft-8");
                httpPost.setEntity(stringEntity);
            }
            execute = client.execute(httpPost);
            result = EntityUtils.toString(execute.getEntity(),"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(execute,httpPost,client);
        }
        return result;
    }

    public static String sendPost(String url, Map<String, String> params)  {
        CloseableHttpClient client = null;
        HttpPost httpPost = null;
        CloseableHttpResponse execute = null;
        String result = null;
        try {
             client = HttpClientBuilder.create().build();
             httpPost = new HttpPost(url);
            if(null != params && params.size() > 0){
                List<BasicNameValuePair> pairs = new ArrayList<>();
                params.forEach((x,y) -> pairs.add(new BasicNameValuePair(x,y)));
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs,"utf-8");
                httpPost.setEntity(urlEncodedFormEntity);
            }
             execute = client.execute(httpPost);
             result = EntityUtils.toString(execute.getEntity(), "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(execute,httpPost,client);
        }
        return result;
    }

    public static String sendGet(String url, Map<String, String> params)  {
        CloseableHttpClient client = null;
        HttpGet httpGet = null;
        CloseableHttpResponse execute = null;
        String result = null;
        try {
             client = HttpClientBuilder.create().build();
            if(null != params && params.size() > 0){
                List<BasicNameValuePair> paramList = new ArrayList<>();
    //            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
    //            while (iterator.hasNext()){
    //                Map.Entry<String, String> next = iterator.next();
    //                paramList.add(new BasicNameValuePair(next.getKey(),next.getValue()));
    //            }
                params.forEach((x,y) -> paramList.add(new BasicNameValuePair(x,y)));
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paramList,"utf-8");
                String paramStr = EntityUtils.toString(urlEncodedFormEntity, "utf-8");
                url += "?"+paramStr;
            }
             httpGet = new HttpGet();
             execute = client.execute(httpGet);
             result = EntityUtils.toString(execute.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(execute,httpGet,client);
        }
         return result;
    }

    private static void close(CloseableHttpResponse response, HttpRequestBase request,CloseableHttpClient client){
        if(null != response){
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(null != request){
            request.releaseConnection();
        }
        if(null != client){
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
