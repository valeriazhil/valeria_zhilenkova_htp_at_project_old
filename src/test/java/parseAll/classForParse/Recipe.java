package parseAll.classForParse;

import java.util.List;

public class Recipe {
public String recipename;
private Ingredient[] ingredient;
public String preptime;

    public Recipe(String recipename, Ingredient[] ingredient, String preptime) {
        this.recipename = recipename;
        this.ingredient = ingredient;
        this.preptime = preptime;
    }
}
