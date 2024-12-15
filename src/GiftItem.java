import java.io.Serializable;

/**
 * GiftItem class represents a gift item in the game.
 */
public class GiftItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int happiness;
    private int price;

    /**
     * Constructor for the GiftItem class
     * 
     * @param name Name of the gift item
     * @param happiness Happiness value of the gift item
     * @param price Price of the gift item
     */
    public GiftItem(String name, int happiness, int price) {
        this.name = name;
        this.happiness = happiness;
        this.price = price;
    }

    /**
     * Method to get the name of the gift item
     * 
     * @return Name of the gift item
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the happiness value of the gift item
     * 
     * @return Happiness value of the gift item
     */
    public int getHappiness() {
        return happiness;
    }

    /**
     * Method to get the price of the gift item
     * 
     * @return Price of the gift item
     */
    public int getPrice() {
        return price;
    }
}
