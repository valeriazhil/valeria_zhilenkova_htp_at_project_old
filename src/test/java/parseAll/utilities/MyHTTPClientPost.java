package parseAll.utilities;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import parseAll.classForParse.Ingredient;
import parseAll.classForParse.Recipe;
import parseAll.classForParse.Search;

import java.io.IOException;
import java.net.URISyntaxException;

public class MyHTTPClientPost {
    public static void main(String[] args) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder= new URIBuilder("http://178.124.206.46:8001/app/ws/");
        HttpPost request= new HttpPost(builder.build());
        Search search= new Search("s", false);
        request.setEntity(new StringEntity(parseJson.fromMyGSON(search)));
        HttpResponse response=client.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
