import java.io.Serializable;

/**
 * Shop class contains the food and gift items available for purchase
 */
public class Shop implements Serializable {
    private static final long serialVersionUID = 1L;
    private FoodItem[] foodItems;
    private GiftItem[] giftItems;

    /**
     * Constructor for the Shop class that initializes the food and gift items
     */
    public Shop() {
        this.foodItems = new FoodItem[] {
            new FoodItem("Apple", 10, 5),
            new FoodItem("Banana", 15, 7),
            new FoodItem("Orange", 20, 10),
            new FoodItem("Strawberry", 25, 12),
            new FoodItem("Watermelon", 30, 15),
        };
        this.giftItems = new GiftItem[] {
            new GiftItem("Ball", 10, 5),
            new GiftItem("Doll", 15, 7),
            new GiftItem("Toy Car", 20, 10),
            new GiftItem("Teddy Bear", 25, 12),
            new GiftItem("Puzzle", 30, 15),
        };
    }

    /**
     * Method to get the food items
     * 
     * @return Array of food items
     */
    public FoodItem[] getFoodItems() {
        return foodItems;
    }

    /**
     * Method to get a specific food item
     * 
     * @param foodName Name of the food item
     * @return Food item object
     */
    public FoodItem getFoodItem(String foodName) {
        for (FoodItem foodItem : foodItems) {
            if (foodItem.getName().equals(foodName)) {
                return foodItem;
            }
        }
        return null;
    }

    /**
     * Method to get the gift items
     * 
     * @return Array of gift items
     */
    public GiftItem[] getGiftItems() {
        return giftItems;
    }

    /**
     * Method to get a specific gift item
     * 
     * @param giftName Name of the gift item
     * @return Gift item object
     */
    public GiftItem getGiftItem(String giftName) {
        for (GiftItem giftItem : giftItems) {
            if (giftItem.getName().equals(giftName)) {
                return giftItem;
            }
        }
        return null;
    }
}
