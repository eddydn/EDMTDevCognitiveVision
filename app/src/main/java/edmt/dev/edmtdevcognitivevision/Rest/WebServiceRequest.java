package edmt.dev.edmtdevcognitivevision.Rest;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebServiceRequest {
    private static final String headerKey = "ocp-apim-subscription-key";
    private OkHttpClient client = new OkHttpClient();
    private String subscriptionKey;
    private Gson gson = new Gson();

    public WebServiceRequest(String key) {
        this.subscriptionKey = key;
    }

    public Object request(String url, String method, Map<String, Object> data, String contentType, boolean responseInputStream) throws VisionServiceException {
        if (method.matches("GET")) {
            return get(url);
        } else if (method.matches("POST")) {
            return post(url, data, contentType, responseInputStream);
        } else if (method.matches("PUT")) {
            return put(url, data);
        } else if (method.matches("DELETE")) {
            return delete(url);
        } else if (method.matches("PATCH")) {
            return patch(url, data, contentType, false);
        }

        throw new VisionServiceException("Error! Incorrect method provided: " + method);
    }

    private Object get(String url) throws VisionServiceException {

        Request request = new Request.Builder()
                .addHeader(headerKey, this.subscriptionKey)
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();

            int statusCode = response.code();
            if (statusCode == 200) {
                return readInput(response.body().byteStream());
            } else {
                throw new Exception("Error executing GET request! Received error code: " + response.code());
            }
        } catch (Exception e) {
            throw new VisionServiceException(e.getMessage());
        }
    }

    private Object post(String url, Map<String, Object> data, String contentType, boolean responseInputStream) throws VisionServiceException {
        return webInvoke("POST", url, data, contentType, responseInputStream);
    }

    private Object patch(String url, Map<String, Object> data, String contentType, boolean responseInputStream) throws VisionServiceException {
        return webInvoke("PATCH", url, data, contentType, responseInputStream);
    }

    private Object webInvoke(String method, String url, Map<String, Object> data, String contentType, boolean responseInputStream) throws VisionServiceException {

        Request request;




        MediaType mediaType;
        boolean isStream = false;


        if(contentType != null && !contentType.isEmpty())
        {
            mediaType = MediaType.parse(contentType);
            if (contentType.toLowerCase().contains("octet-stream")) {
                isStream = true;
            }
        }
        else {
            mediaType =  MediaType.parse("application/json; charset=utf-8");
        }





        try {
            RequestBody requestBody;
            if (!isStream) {
                String json = this.gson.toJson(data).toString();
                requestBody = RequestBody.create(mediaType, json);
            } else {
                requestBody = RequestBody.create(mediaType, (byte[]) data.get("data"));
            }

                    if (method.matches("POST")) {
            request = new Request.Builder()
                    .addHeader(headerKey, this.subscriptionKey)
                    .url(url)
                    .post(requestBody)
                    .build();
        } else if (method.matches("PATCH")) {
            request = new Request.Builder()
                    .addHeader(headerKey, this.subscriptionKey)
                    .url(url)
                    .patch(requestBody)
                    .build();
        }
        else
            request = new Request.Builder()
                                .addHeader(headerKey, this.subscriptionKey)
                                .url(url)
                                .build();

            Response response = client.newCall(request).execute();
            int statusCode = response.code();

            if (statusCode == 200) {
                if(!responseInputStream) {
                    return readInput(response.body().byteStream());
                }else {
                    return response.body().string();
                }
            }else if(statusCode==202)
            {
                return response.header("Operation-Location");
            }
            else {
                throw new Exception("Error executing POST request! Received error code: " + response.code());
            }
        } catch (Exception e) {
            throw new VisionServiceException(e.getMessage());
        }
    }

    private Object put(String url, Map<String, Object> data) throws VisionServiceException {
        Request request;


        try {
            MediaType JSON
                    = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, this.gson.toJson(data));

            request   = new Request.Builder()
                    .url(url)
                    .addHeader(headerKey, this.subscriptionKey)
                    .addHeader("Content-Type", "application/json")
                    .put(body)
                    .build();

            Response response = client.newCall(request).execute();

            int statusCode = response.code();
            if (statusCode == 200 || statusCode == 201) {
                return readInput(response.body().byteStream());
            } else {
                throw new Exception("Error executing PUT request! Received error code: " + response.code());
            }
        } catch (Exception e) {
            throw new VisionServiceException(e.getMessage());
        }
    }

    private Object delete(String url) throws VisionServiceException {
        Request request;


        try {

            request   = new Request.Builder()
                    .url(url)
                    .addHeader(headerKey, this.subscriptionKey)
                    .addHeader("Content-Type", "application/json")
                    .delete()
                    .build();

            Response response = client.newCall(request).execute();

            int statusCode = response.code();
            if (statusCode != 200) {
                throw new Exception("Error executing DELETE request! Received error code: " + response.code());
            }

            return readInput(response.body().byteStream());
        } catch (Exception e) {
            throw new VisionServiceException(e.getMessage());
        }
    }

    public static String getUrl(String path, Map<String, Object> params) {
        StringBuffer url = new StringBuffer(path);

        boolean start = true;
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (start) {
                url.append("?");
                start = false;
            } else {
                url.append("&");
            }

            try {
                url.append(param.getKey() + "=" + URLEncoder.encode(param.getValue().toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return url.toString();
    }

    private String readInput(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer json = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            json.append(line);
        }

        return json.toString();
    }
}

