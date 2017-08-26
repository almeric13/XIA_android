package losbibis.xia;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by 46519757 on 09/11/2016.
 */

public class Requeteur_html {

    private final OkHttpClient client = new OkHttpClient();

    public String get(String script) throws IOException {
        // Prepare the request.
        Request request = new Request.Builder().url(script).build();
        // Execute the request.
        Response response = client.newCall(request).execute();
        // Get the result.
        return response.body().string();
    }

}
