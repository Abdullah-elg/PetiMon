import javax.swing.JFrame;
import java.io.IOException;

/**
 * MainFrame class is the main JFrame for the game.
 */
public class MainFrame extends JFrame {
    private MainMenu mainMenu;

    /**
     * Constructor for MainFrame.
     */
    public MainFrame() {
        try {
            mainMenu = new MainMenu(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.add(mainMenu);

        this.setTitle("PetiMon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1440, 810);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Main method for the game.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new MainFrame();
    }
}