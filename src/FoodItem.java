import java.io.Serializable;

/**
 * Class to represent a food item in the game
 */
public class FoodItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int fullness;
    private int price;

    /**
     * Constructor for the FoodItem class
     * 
     * @param name Name of the food item
     * @param fullness Fullness value of the food item
     * @param price Price of the food item
     */
    public FoodItem(String name, int fullness, int price) {
        this.name = name;
        this.fullness = fullness;
        this.price = price;
    }

    /**
     * Method to get the name of the food item
     * 
     * @return Name of the food item
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the fullness value of the food item
     * 
     * @return Fullness value of the food item
     */
    public int getFullness() {
        return fullness;
    }

    /**
     * Method to get the price of the food item
     * 
     * @return Price of the food item
     */
    public int getPrice() {
        return price;
    }
}
