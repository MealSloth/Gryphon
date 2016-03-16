package com.mealsloth.gryphon.api;

import java.io.IOException;
import java.util.HashMap;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by michael on 3/15/16.
 */
public class JsonPost
{
    private String postUrl;
    private String data;

    public JsonPost(APIHost.APIHostEnum service, String method, HashMap data)
    {
        this.postUrl = APIHost.URL(service) + method;
        this.data = new Gson().toJson(data);
    }

    public String post() throws IOException
    {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, this.data);
        Request request = new Request.Builder()
                .url(this.postUrl)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}