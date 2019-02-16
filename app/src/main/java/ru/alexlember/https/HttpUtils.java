package ru.alexlember.https;

import android.content.res.Resources;
import android.util.Log;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.alexlember.MainActivity;
import ru.alexlember.hello.R;

import javax.net.ssl.*;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.util.Arrays;

public class HttpUtils {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static JSONObject sendPostRequest(URL url, JSONObject json) {
        JSONObject jsonObjectResp = null;

        try {
            OkHttpClient client = MainActivity.getHttpClient();

            okhttp3.RequestBody body = RequestBody.create(JSON, json.toString());
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            okhttp3.Response response = client.newCall(request).execute();

            String networkResp = response.body().string();
            if (!networkResp.isEmpty()) {
                jsonObjectResp = parseJSONStringToJSONObject(networkResp);
            }
        } catch (Exception ex) {
            String err = String.format("{\"result\":\"false\",\"error\":\"%s\"}", ex.getMessage());
            jsonObjectResp = parseJSONStringToJSONObject(err);
        }

        return jsonObjectResp;
    }

    static JSONObject parseJSONStringToJSONObject(final String strr) {

        JSONObject response = null;
        try {
            response = new JSONObject(strr);
        } catch (Exception ex) {
            try {
                response = new JSONObject();
                response.put("result", "failed");
                response.put("data", strr);
                response.put("error", ex.getMessage());
            } catch (Exception ignored) {}
        }
        return response;
    }

    @Nullable
    public static URL buildHttpPath(final Resources res) {
        URL url = null;
        try {

            String stringBuilder = res.getString(R.string.serverProtocol) +
                    "://" +
                    res.getString(R.string.serverHost) +
                    ":" +
                    res.getString(R.string.serverPort) +
                    res.getString(R.string.httpPath);
            url = new URL(stringBuilder);

        } catch (MalformedURLException ex) {
            Log.e("buildHttpPath", ex.toString());
        }
        return url;

    }

    @Nullable
    public static OkHttpClient buildHttpClient(final Resources res) {
        OkHttpClient client = null;

        try {
            String password = res.getString(R.string.clientPass);

            // init keystore
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            try (InputStream in = res.openRawResource(R.raw.client_cert)) {
//                keyStore.load(in, password.toCharArray());
//            }

//            // init key manager (with key store)
//            KeyManagerFactory kmf = KeyManagerFactory.getInstance("X509");
//            kmf.init(keyStore, password.toCharArray());
//            KeyManager[] keyManagers = kmf.getKeyManagers();

            // init trust manager factory (with key store)
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            //trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

            // init ssl context (with key manager and trust manager factory)
            SSLContext sslContext = SSLContext.getInstance("TLS");
            //sslContext.init(keyManagers, trustManagers, null);

            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            client = new OkHttpClient
                    .Builder()
                    .sslSocketFactory(sslSocketFactory, trustManager)
                    .hostnameVerifier(new AllowAllHostnameVerifier())
                    .build();

        } catch (Exception ex) {
            Log.e("buildHttpClient", ex.toString());
        }
        return client;
    }
}
