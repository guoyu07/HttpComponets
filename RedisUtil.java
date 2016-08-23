package com.example.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by lql on 2016/8/18.
 */
@Component
public class RedisUtil {
    @Autowired
    private HttpConnectionPool pool;
    private static final String url = "http://192.168.1.9:8888/RedisServer";

    public boolean set(final String key, final String value) {
        String result = execute("set", key, value);
        if(null != result) {
            if(result.equals("true"))
                return true;
            else
                return false;
        }
        return false;
    }

    public boolean set(final String key, final String value, final String expireTime) {
        String result = execute("set", key, value, expireTime);
        if(null != result) {
            if(result.equals("true"))
                return true;
            else
                return false;
        }
        return false;
    }

    public boolean setKeys(final String map) {
        String result = execute("setKeys", map);
        if(null != result) {
            if(result.equals("true"))
                return true;
            else
                return false;
        }
        return false;
    }

    public boolean setKeys(final String map, final String expireTime) {
        String result = execute("setKeys", map, expireTime);
        if(null != result) {
            if(result.equals("true"))
                return true;
            else
                return false;
        }
        return false;
    }

    public boolean setMapValue(final String key, final String hashKey, final String value) {
        String result = execute("setMapValue", key, hashKey, value);
        if(null != result) {
            if(result.equals("true"))
                return true;
            else
                return false;
        }
        return false;
    }

    public String get(final String key) {
        String result = execute("get", key);
        if(null != result)
            return result;
        else
            return null;
    }

    public String get(final String key, final String hashKey) {
        String result = execute("get", key, hashKey);
        if(null != result)
            return result;
        else
            return null;
    }

    public boolean delete(final String key) {
        String result = execute("delete", key);
        if(null != result) {
            if(result.equals("true"))
                return true;
            else
                return false;
        }
        return false;
    }

    private String execute(final String prefix, final String... params) {
        String request = url + "/" + prefix;
        for(final String param : params) {
            request += ("/" + param);
        }
        HttpGet get = new HttpGet(request);
        CloseableHttpClient client= pool.getConnection();
        try {
            CloseableHttpResponse response = client.execute(get);
            int status = response.getStatusLine().getStatusCode();
            if(status >= 200 && status <= 300) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "utf-8");
                return result;
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
