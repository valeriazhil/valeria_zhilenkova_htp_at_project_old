package runners.junit;

import application_items.Search;
import utilities.web_service.MyHttpClient;
import utilities.web_service.ParserGson;

import java.io.IOException;
import java.net.URISyntaxException;

public class ParserRunner {
    static ParserGson parser = new ParserGson();

    public static void main(String[] args)
            throws IOException, URISyntaxException {
        parser.parseJSON();
        parser.parseGSON();
        parser.parseJackson();
        parser.fromGSON();
        MyHttpClient httpClient = new MyHttpClient();
        Search search = new Search("berta", true);
        httpClient.search(search);
    }
}
