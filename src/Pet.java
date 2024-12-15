import java.io.Serializable;

/**
 * Class to represent a pet
 */
public class Pet implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int health;
    private int fullness;
    private int happiness;
    private int sleep;
    private PetState state;
    private int score;
    private int maxFullness;
    private int maxHappiness;
    private int maxSleep;

    /**
     * Constructor for the Pet class
     * 
     * @param name Name of the pet
     * @param maxFullness Maximum fullness of the pet
     * @param maxHappiness Maximum happiness of the pet
     * @param maxSleep Maximum sleepiness of the pet
     */
    public Pet(String name, int maxFullness, int maxHappiness, int maxSleep) {
        this.name = name;
        this.health = 100;
        this.fullness = maxFullness;
        this.happiness = maxHappiness;
        this.sleep = maxSleep;
        this.maxFullness = maxFullness;
        this.maxHappiness = maxHappiness;
        this.maxSleep = maxSleep;
        this.score = 0;
        updateState();
    }

    /**
     * Method to update the pet's state
     */
    public void updateState() {
        if (getState() == PetState.DEAD) {
            return;
        }
        if (getState() != PetState.SLEEPING || sleep == maxSleep) {
            if (health == 0) {
                setState(PetState.DEAD);
            } else if (sleep == 0) {
                setState(PetState.SLEEPING);
                health -= 10;
                if (health < 0) {
                    health = 0;
                }
            } else if (fullness == 0) {
                setState(PetState.HUNGRY);
                health -= 5; // Pet loses health when hungry keeps losing health until fed
                if (health < 0) {
                    health = 0;
                }
            } else if (happiness == 0) {
                setState(PetState.ANGRY);
            } else if (happiness <= (0.5 * maxHappiness)) {
                if (getState() != PetState.ANGRY) {
                    setState(PetState.NORMAL);
                }
            } else {
                setState(PetState.NORMAL);
            }
        }
    }

    /**
     * Method to revive the pet
     */
    public void revive() {
        health = 100;
        fullness = maxFullness;
        happiness = maxHappiness;
        sleep = maxSleep;
        state = PetState.NORMAL;
    }

    /**
     * Method to get the name of the pet
     * 
     * @return Name of the pet
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the health of the pet
     * 
     * @return Health of the pet
     */
    public int getHealth() {
        return health;
    }

    /**
     * Method to get the fullness of the pet
     * 
     * @return Fullness of the pet
     */
    public int getFullness() {
        return fullness;
    }

    /**
     * Method to get the happiness of the pet
     * 
     * @return Happiness of the pet
     */
    public int getHappiness() {
        return happiness;
    }

    /**
     * Method to get the sleepiness of the pet
     * 
     * @return Sleepiness of the pet
     */
    public int getSleep() {
        return sleep;
    }

    /**
     * Method to get the state of the pet
     * 
     * @return State of the pet
     */
    public PetState getState() {
        return state;
    }

    /**
     * Method to get the score of the pet
     * 
     * @return Score of the pet
     */
    public int getScore() {
        return score;
    }

    /**
     * Method to get the maximum fullness of the pet
     * 
     * @return Maximum fullness of the pet
     */
    public int getMaxFullness() {
        return maxFullness;
    }

    /**
     * Method to get the maximum happiness of the pet
     * 
     * @return Maximum happiness of the pet
     */
    public int getMaxHappiness() {
        return maxHappiness;
    }

    /**
     * Method to get the maximum sleepiness of the pet
     * 
     * @return Maximum sleepiness of the pet
     */
    public int getMaxSleep() {
        return maxSleep;
    }

    /**
     * Method to add score to the pet
     */
    public void addScore() {
        this.score += 1;
    }

    /**
     * Method to remove score from the pet
     */
    public void removeScore() {
        this.score -= 1;
    }

    /**
     * Method to set the state of the pet
     * 
     * @param state State of the pet
     */
    public void setState(PetState state) {
        this.state = state;
    }

    /**
     * Method to set the health of the pet
     * 
     * @param health Health of the pet
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Method to set the fullness of the pet
     * 
     * @param fullness Fullness of the pet
     */
    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    /**
     * Method to set the happiness of the pet
     * 
     * @param happiness Happiness of the pet
     */
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    /**
     * Method to set the sleepiness of the pet
     * 
     * @param sleep Sleepiness of the pet
     */
    public void setSleep(int sleep) {
        this.sleep = sleep;
    }
}
