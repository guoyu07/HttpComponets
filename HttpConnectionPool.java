package com.example.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.stereotype.Component;

/**
 * Created by lql on 2016/8/22.
 */
@Component
public class HttpConnectionPool {
    private static PoolingHttpClientConnectionManager connectionManager;
    private static RequestConfig requestConfig;

    static {
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(500);
//        connectionManager.setDefaultMaxPerRoute(2);
//        connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("127.0.0.1", 80)), 500);

        requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).setConnectionRequestTimeout(2000).build();
    }

    public CloseableHttpClient getConnection() {
        return HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
    }
}
