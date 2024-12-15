import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.IOException;

/**
 * Save class allows users to save their game.
 */
public class Save implements Serializable {
    private static final long serialVersionUID = 1L;
    private String saveName;
    private Game game;

    /**
     * Constructor for Save.
     *
     * @param gameSave The game instance to save.
     */
    public Save(Game gameSave) {
        this.game = gameSave;
        this.saveName = gameSave.getPet().getName();
        String fileName = "saves/" + saveName + ".dat";
        try (FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file)) {;
            out.writeObject(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
