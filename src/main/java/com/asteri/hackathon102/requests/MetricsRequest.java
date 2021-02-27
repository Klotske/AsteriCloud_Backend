package com.asteri.hackathon102.requests;

import com.cloud.apigateway.sdk.utils.Request;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class MetricsRequest {

    private static String projectId="/0b965500b98027322f65c014a08bf81b/";

    private static String  path = "V1.0"+projectId+"metrics";

    public static Request metrics(){
        Request request = AbstractRequest.makeRequest(path,"GET");
        return request;
    }
   public static   String getData(String nameSpace, String metric) {
        String result = "";
        Request request = metrics();
        try {
            String body = "";
            request.addQueryStringParam("namespace",nameSpace);
            request.addQueryStringParam("metric_name",metric);
            body = AbstractRequest.getResponseBody(request);
            JSONObject response = new JSONObject(body);
            JSONArray metrics = response.getJSONArray("metrics");
            JSONObject array = metrics.getJSONObject(0);
            JSONArray dimension = array.getJSONArray("dimensions");
            JSONObject last = dimension.getJSONObject(0);
            result = last.getString("value");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
