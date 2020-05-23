package parseAll.utilities;

import parseAll.classForParse.Ingredient;
import parseAll.classForParse.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.json.JSONObject;
import parseAll.classForParse.Search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class parseJson {

    private final static String  JSON="src/test/java/forParse/resipe.json";

    File file = new File(JSON);

    public static String fromMyGSON(Search search) throws FileSystemNotFoundException {
        Gson gson=new Gson();
        return gson.toJson(search);
    }



    public void parseJSON()  throws IOException{
        String  input=new String(Files.readAllBytes(Paths.get(JSON)));
        JSONObject obj=new JSONObject(input);
        System.out.println(obj.getString("recipename"));
    }

    public void parseGSON() throws FileSystemNotFoundException, FileNotFoundException {
        Gson gson=new Gson();
        Recipe recipe=gson.fromJson(new JsonReader(new FileReader(JSON)), Recipe.class);
        //System.out.println(recipe.recipename);
        System.out.println(recipe.recipename);
    }

    public void fromGSON() throws FileSystemNotFoundException, FileNotFoundException{
        Gson gson=new Gson();
        Recipe recipe= new Recipe("Borsch", new  Ingredient[]{}, "120");
        System.out.println(gson.toJson(recipe));
    }

    public void parseJackson() throws IOException {
        ObjectMapper mapper= new ObjectMapper();
        Recipe recipe=mapper.readValue(file, Recipe.class);
        System.out.println(recipe.recipename);
    }

}
