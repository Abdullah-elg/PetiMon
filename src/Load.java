import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Load class allows users to load saved games.
 */
public class Load implements Serializable {
    private static final long serialVersionUID = 1L;
    private String saveName;

    /**
     * Constructor for Load.
     *
     * @param save The name of the save file.
     */
    public Load(String save) {
        this.saveName = save;
    }

    /**
     * Load the game from the save file.
     *
     * @return The loaded game.
     */
    public Game loadGame() {
        String fileName = "saves/" + saveName + ".dat";
        Game gameLoad = null;
        try (FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file)) {
            gameLoad = (Game) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameLoad;
    }
}
