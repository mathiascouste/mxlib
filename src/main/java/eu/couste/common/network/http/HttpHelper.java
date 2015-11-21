package eu.couste.common.network.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpHelper {

    // HTTP request types
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    // HTTP codes
    public static final int OK = 200;

    private String url;
    private String method;

    private String sendBody;

    private int responseCode;
    private String response;

    public HttpHelper setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpHelper setMethod(String method) {
        this.method = method;
        return this;
    }

    public HttpHelper setSendBody(String sendBody) {
        this.sendBody = sendBody;
        return this;
    }

    public String getResponse() {
        return response;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void call() throws IOException {
        URL urlink = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlink.openConnection();
        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        con.setRequestMethod(this.method);

        if (this.sendBody != null) {
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(this.sendBody);
            wr.flush();
            wr.close();
        }

        this.responseCode = con.getResponseCode();
        if (this.responseCode == 200) {

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            this.response = response.toString();
        }

    }
}
