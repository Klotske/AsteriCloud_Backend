package com.asteri.hackathon102.requests;

import com.cloud.apigateway.sdk.utils.Client;
import com.cloud.apigateway.sdk.utils.Request;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public abstract class AbstractRequest {

    private static Request request = new Request();

    private static String key="LTNYPLEPGMIPFDOA6GPZ";

    private static String secretKey="HInxwhqZ7Z84H4uYc2s28JQgcNUhPpJdngXmf3uf";

    private static String url="https://ces.ru-moscow-1.hc.sbercloud.ru/";

    public static Request makeRequest(String path,String method){
         request = new Request();
        try {
            request.setMethod(method);
            request.setSecret(secretKey);
            request.setKey(key);
            request.setUrl(url+path);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    public static String getResponseBody(Request request){
        String body = "";
        CloseableHttpClient client = null;
        try{
            HttpRequestBase signedRequest = Client.sign(request);

            client = HttpClients.custom().build();
            HttpResponse response = client.execute(signedRequest);
            System.out.println(response.getStatusLine().toString());
            Header[] resHeaders = response.getAllHeaders();
            for (Header h : resHeaders)
            {
                System.out.println(h.getName() + ":" + h.getValue());
            }
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null)
            {
                body =  System.getProperty("line.separator") + EntityUtils.toString(resEntity, "UTF-8");
            }
        } catch (Exception e){e.printStackTrace();}
        finally {
            try{
                if (client != null) client.close();
            } catch (IOException e) {e.printStackTrace();}
        }
        return body;
    }
}
