import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.net.UrlEscapers;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.StreamSupport;

public class DawaDataSource {


    private LoadingCache<String, String[]> cache;

    public DawaDataSource() {
        CacheLoader<String, String[]> loader = new CacheLoader<String, String[]>() {
            @Override
            public String[] load(String key) {
                try {
                    String query = UrlEscapers.urlFormParameterEscaper().escape(key);
                    String url = "http://dawa.aws.dk/adresser?q=" + query;

                    java.net.URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");

                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);

                    try (InputStream stream = con.getInputStream()) {
                        JsonElement element = new JsonParser().parse(new InputStreamReader(stream));
                        JsonArray results = element.getAsJsonArray();

                        return StreamSupport.stream(results.spliterator(), false).map(result -> {
                            JsonObject object = result.getAsJsonObject();
                            return object.getAsJsonPrimitive("adressebetegnelse").getAsString();
                        }).toArray(String[]::new);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(loader);
    }

    public String[] search(String query) throws Exception {
        return cache.get(query);
    }
}
