package application_items;

public class Ingredient {
    public String itemdescription;
    public int quantity;

    @Override
    public String toString() {
        return "Ingredient: " +
                "name - " + itemdescription + ", " +
                "quantity - " + quantity;
    }
}
