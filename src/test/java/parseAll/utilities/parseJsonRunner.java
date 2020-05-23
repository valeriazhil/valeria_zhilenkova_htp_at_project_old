package parseAll.utilities;

public class parseJsonRunner {

    static parseJson jsonParser= new parseJson();

    public static void main(String[] args) throws Exception {
        jsonParser.parseJSON();
        jsonParser.parseGSON();
        //jsonParser.parseJackson();
        jsonParser.fromGSON();
    }
}
