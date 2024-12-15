import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Inventory class to store the food and gift items
 */
public class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<FoodItem> foodItems;
    private List<GiftItem> giftItems;

    /**
     * Constructor for the Inventory class to initialize the food and gift items
     */
    public Inventory() {
        foodItems = new ArrayList<FoodItem>();
        giftItems = new ArrayList<GiftItem>();
    }

    /**
     * Method to get the food item from the inventory
     * 
     * @param foodName Name of the food item
     * @return food item
     */
    public FoodItem getFood(String foodName) {
        for (FoodItem foodItem : foodItems) {
            if (foodItem.getName().equals(foodName)) {
                return foodItem;
            }
        }
        return null;
    }

    /**
     * Method to get the gift item from the inventory
     * 
     * @param giftName Name of the gift item
     * @return gift item
     */
    public GiftItem getGift(String giftName) {
        for (GiftItem giftItem : giftItems) {
            if (giftItem.getName().equals(giftName)) {
                return giftItem;
            }
        }
        return null;
    }

    /**
     * Method to get the number of a specific food item in the inventory
     *
     * @param foodName Name of the food item
     * @return Number of food items
     */
    public int getFoodItem(String foodName) {
        int count = 0;
        for (FoodItem foodItem : foodItems) {
            if (foodItem.getName().equals(foodName)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Method to get the number of a specific gift item in the inventory
     * 
     * @param giftName Name of the gift item
     * @return Number of gift items
     */
    public int getGiftItem(String giftName) {
        int count = 0;
        for (GiftItem giftItem : giftItems) {
            if (giftItem.getName().equals(giftName)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Method to add food item to the inventory
     * 
     * @param foodItem Name of the food item
     */
    public void addFoodItem(FoodItem foodItem) {
        foodItems.add(foodItem);
    }

    /**
     * Method to add gift item to the inventory
     * 
     * @param giftItem Name of the gift item
     */
    public void addGiftItem(GiftItem giftItem) {
        giftItems.add(giftItem);
    }

    /**
     * Method to remove food item from the inventory
     * 
     * @param foodItem Name of the food item
     * @return True if the food item is removed, false otherwise
     */
    public boolean removeFoodItem(FoodItem foodItem) {
        if (!foodItems.contains(foodItem)) {
            return false;
        }
        foodItems.remove(foodItem);
        return true;
    }

    /**
     * Method to remove gift item from the inventory
     * 
     * @param giftItem Name of the gift item
     * @return True if the gift item is removed, false otherwise
     */
    public boolean removeGiftItem(GiftItem giftItem) {
        if (!giftItems.contains(giftItem)) {
            return false;
        }
        giftItems.remove(giftItem);
        return true;
    }
}
