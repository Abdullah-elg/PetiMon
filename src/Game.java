import java.io.Serializable;

/**
 * Game class to handle the game logic
 */
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    private Shop shop;
    private Pet pet;
    private String petType;
    private int coins;
    private Inventory inventory;
    private int vetCD = 0;
    private int playCD = 0;
    private int playTime = 0;

    /**
     * Constructor for the Game class, initializes the pet, coins, and inventory
     * 
     * @param petName Name of the pet
     * @param coins Number of coins the player has
     * @param maxFullness Maximum fullness of the pet
     * @param maxHappiness Maximum happiness of the pet
     * @param maxSleep Maximum sleepiness of the pet
     */
    public Game(String petName, int coins, int maxFullness, int maxHappiness, int maxSleep, String type) {
        pet = new Pet(petName, maxFullness, maxHappiness, maxSleep);
        petType = type;
        this.coins = coins;
        inventory = new Inventory();
        shop = new Shop();
    }
    
    /**
     * Method to buy food from the shop
     * 
     * @param foodItem Name of the food item
     * @return true if the food was bought, false otherwise
     */
    public boolean buyFood(String foodItem){
        FoodItem food = shop.getFoodItem(foodItem);
        if (food != null) {
            if (coins >= food.getPrice()) {
                coins -= food.getPrice();
                inventory.addFoodItem(food);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Method to buy gift from the shop
     * 
     * @param giftItem Name of the gift item
     * @return true if the gift was bought, false otherwise
     */
    public boolean buyGift(String giftItem){
        GiftItem gift = shop.getGiftItem(giftItem);
        if (gift != null) {
            if (coins >= gift.getPrice()) {
                coins -= gift.getPrice();
                inventory.addGiftItem(gift);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Method to update, should be constantly called to update the pet's state
     * 
     * @return void if the pet is dead
     */
    public void update() {
        if (pet.getState() == PetState.DEAD) {
            System.out.println("Your pet has died.");
            return;
        }
        if (pet.getState() == PetState.SLEEPING) {
            int sleep = pet.getSleep() + 4;
            if (sleep > pet.getMaxSleep()) {
                sleep = pet.getMaxSleep();
            } 
            pet.setSleep(sleep);
        } else {
            int full, happy, sleep;
            int health = pet.getHealth() - 1;
            if (petType.equals("BlueGuy")) {
                full = pet.getFullness() - 2;
                happy = pet.getHappiness() - 1;
                sleep = pet.getSleep() - 1;
            } else if (petType.equals("GreenGuy")) {
                full = pet.getFullness() - 1;
                happy = pet.getHappiness() - 1;
                sleep = pet.getSleep() - 1;
            } else if (petType.equals("YellowGuy")) {
                full = pet.getFullness() - 2;
                happy = pet.getHappiness() - 2;
                sleep = pet.getSleep() - 2;
            } else {
                full = 0;
                happy = 0;
                sleep = 0;
            }

            if (health < 0) {
                pet.setHealth(0);
            } else {
                pet.setHealth(health);
            }

            if (full < 0) {
                pet.setFullness(0);
            } else {
                pet.setFullness(full);
            }

            if (happy < 0) {
                pet.setHappiness(0);
            } else {
                pet.setHappiness(happy);
            }

            if (sleep < 0) {
                pet.setSleep(0);
            } else {
                pet.setSleep(sleep);
            }
        }
        if (vetCD > 0) {
            vetCD--;
        }
        if (playCD > 0) {
            playCD--;
        }
        pet.updateState();
    }

    /**
     * Mehod to execute the goToBed command
     * 
     * @return true if the pet went to bed, false otherwise
     */
    public boolean goToBed() {
        PetState state = pet.getState();
        if (state == PetState.HUNGRY || state == PetState.NORMAL) {
            pet.setState(PetState.SLEEPING);
            pet.updateState();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to execute the feed command
     * 
     * @param food Food item to feed the pet
     * @return true if the pet was fed, false otherwise
     */
    public boolean feed(FoodItem food) {
        PetState state = pet.getState();
        if (state == PetState.HUNGRY || state == PetState.NORMAL) {
            if (inventory.removeFoodItem(food)) {
                int temp = food.getFullness() + pet.getFullness();
                if (temp > pet.getMaxFullness()) {
                    temp = pet.getMaxFullness();
                }
                pet.setFullness(temp);
                pet.addScore();
                pet.updateState();
                coins += food.getFullness();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Method to give gift to the pet
     * 
     * @param gift Gift item to give to the pet
     * @return true if the gift was given, false otherwise
     */
    public boolean giveGift(GiftItem gift) {
        PetState state = pet.getState();
        if (state == PetState.NORMAL || state == PetState.HUNGRY || state == PetState.ANGRY) {
            if (inventory.removeGiftItem(gift)) {
                int temp = gift.getHappiness() + pet.getHappiness();
                if (temp > pet.getMaxHappiness()) {
                    temp = pet.getMaxHappiness();
                }
                pet.setHappiness(temp);
                pet.addScore();
                pet.updateState();
                coins += gift.getHappiness();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Method to take the pet to the vet
     * 
     * @return true if the pet was taken to the vet, false otherwise
     */
    public boolean takeToVet() {
        PetState state = pet.getState();
        if (state == PetState.NORMAL || state == PetState.HUNGRY) {
            if (vetCD == 0) {
                int temp = 100;
                pet.setHealth(temp);
                vetCD = 25;
                pet.removeScore();
                pet.updateState();
                coins -= 50;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Method to play with the pet
     * 
     * @return true if the pet was played with, false otherwise
     */
    public boolean playWithPet() {
        PetState state = pet.getState();
        if (state == PetState.NORMAL || state == PetState.HUNGRY || state == PetState.ANGRY) {
            if (playCD == 0) {
                int temp = 20 + pet.getHappiness();
                if (temp > pet.getMaxHappiness()) {
                    temp = pet.getMaxHappiness();
                }
                pet.setHappiness(temp);
                playCD = 25;
                pet.addScore();
                pet.updateState();
                coins += 10;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Method to exercise the pet
     * 
     * @return true if the pet was exercised, false otherwise
     */
    public boolean exercise() {
        PetState state = pet.getState();
        if (state == PetState.NORMAL || state == PetState.HUNGRY) {
            int health = 10 + pet.getHealth();
            int sleep = pet.getSleep() - 20;
            int hunger = pet.getFullness() - 20;
            if (health > 100) {
                health = 100;
            }
            if (sleep < 0) {
                sleep = 0;
            }
            if (hunger < 0) {
                hunger = 0;
            }
            pet.setHealth(health);
            pet.setSleep(sleep);
            pet.setFullness(hunger);
            pet.addScore();
            pet.updateState();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to set the play time
     * 
     * @param time Time to set
     */
    public void setPlayTime(int time) {
        playTime = time;
    }

    /**
     * Method to get the play time
     * 
     * @return the play time
     */
    public int getPlayTime() {
        return playTime;
    }

    /** 
     * Method to get the pet
     * 
     * @return the pet
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Method to get the pet type
     * 
     * @return Type of the pet
     */
    public String getPetType() {
        return petType;
    }

    /**
     * Method to get the coins
     * 
     * @return Number of coins the player has
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Method to get the inventory of the player
     * 
     * @return Inventory of the player
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Method to get the shop
     * 
     * @return the shop
     */
    public Shop getShop() {
        return shop;
    }
}
