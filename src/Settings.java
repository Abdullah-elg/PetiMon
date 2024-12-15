import javax.swing.JButton;
import javax.swing.JFrame;
import java.io.IOException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Settings class allows users to save the game and return to the main menu.
 */
public class Settings extends JLayeredPaneWithBackground{
    private Game game;
    private JFrame parentFrame;
    private GameScreen gameScreen;
    private JButton backButton;
    private JButton mainMenu;
    private JButton saveGame;

    /**
     * Constructor for Settings.
     *
     * @param gameSave The game instance.
     * @param gameScr The game screen instance.
     * @param frame The parent JFrame.
     * @throws IOException If there's an error loading resources.
     */
    public Settings(Game gameSave, GameScreen gameScr, JFrame frame) throws IOException {
        super("images/Setting.png");

        this.game = gameSave;
        this.gameScreen = gameScr;
        this.parentFrame = frame;

        this.setPreferredSize(new Dimension(700, 700));
        this.setLayout(null);

        BufferedImage back = null, main = null, save = null;
        try {
            back = ImageIO.read(new File("images/buttons/Back.png"));
            main = ImageIO.read(new File("images/buttons/MainMenu.png"));
            save = ImageIO.read(new File("images/buttons/SaveGame.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        backButton = new JButton(new ImageIcon(back));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.setBounds(0, 0, 81, 81);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Settings.this.setVisible(false);
            }
        });

        mainMenu = new JButton(new ImageIcon(main));
        mainMenu.setBorder(BorderFactory.createEmptyBorder());
        mainMenu.setContentAreaFilled(false);
        mainMenu.setBounds(120, 192, 460, 158);
        mainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Settings.this.setVisible(false);
                parentFrame.getContentPane().remove(gameScreen);
                Component[] components = parentFrame.getContentPane().getComponents();
                for (Component component : components) {
                    if (component instanceof MainMenu) {
                        component.setVisible(true);
                        break;
                    }
                }

                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });

        saveGame = new JButton(new ImageIcon(save));
        saveGame.setBorder(BorderFactory.createEmptyBorder());
        saveGame.setContentAreaFilled(false);
        saveGame.setBounds(120, 412, 460, 158);
        saveGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setPlayTime(gameScreen.getTime());
                new Save(game);
            }
        });

        this.add(backButton);
        this.add(mainMenu);
        this.add(saveGame);
        this.setVisible(true);
    }
}
