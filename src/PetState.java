/**
 * Enum class for the state of the pet.
 */
public enum PetState {
    NORMAL,
    ANGRY,
    HUNGRY,
    SLEEPING,
    DEAD;

    /**
     * Method to get the string representation of the pet state.
     * 
     * @return String representation of the pet state
     */
    @Override
    public String toString() {
        switch (this) {
            case NORMAL:
                return "normal";
            case ANGRY:
                return "angry";
            case HUNGRY:
                return "hungry";
            case SLEEPING:
                return "sleep";
            case DEAD:
                return "dead";
            default:
                return "Unknown";
        }
    }
}