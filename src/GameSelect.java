import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.DefaultComboBoxModel;

/**
 * GameSelect class allows users to select and load saved games.
 */
public class GameSelect extends JLayeredPaneWithBackground {
    private JButton backButton;
    private JButton okButton;
    private Game game;
    private String s;
    private MainMenu mainMenu;

    private JFrame parentFrame;
    private JLabel selectSaveLabel;
    private JComboBox<String> saveComboBox;

    /**
     * Constructor for GameSelect.
     *
     * @param frame The parent JFrame.
     * @param menu  The main menu instance.
     * @throws IOException If there's an error loading resources.
     */
    public GameSelect(JFrame frame, MainMenu menu) throws IOException {
        super("images/LoadGame.png");
        this.mainMenu = menu;
        s = "";

        this.setPreferredSize(new Dimension(500, 250));
        this.setLayout(null);

        this.parentFrame = frame;

        BufferedImage back = null, ok = null;
        try {
            back = ImageIO.read(new File("images/buttons/Back.png"));
            ok = ImageIO.read(new File("images/buttons/Ok.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        backButton = new JButton(new ImageIcon(back));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.setBounds(0, 0, 81, 81);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameSelect.this.setVisible(false);
            }
        });

        Image okImage = ok.getScaledInstance(149, 60, Image.SCALE_SMOOTH);
        okButton = new JButton(new ImageIcon(okImage));
        okButton.setBorder(BorderFactory.createEmptyBorder());
        okButton.setContentAreaFilled(false);
        okButton.setBounds(176, 155, 149, 60);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (s != null && !s.isEmpty()) {
                        Load load = new Load(s);
                        game = load.loadGame();
                        Game newGame = game;
                        GameScreen gameScreen = new GameScreen(newGame, parentFrame);
                        gameScreen.setBounds(0, 0, 1440, 810);
                        gameScreen.setVisible(true);
                        mainMenu.setVisible(false);
                        parentFrame.getContentPane().add(gameScreen);
                        parentFrame.revalidate();
                        parentFrame.repaint();
                        GameSelect.this.setVisible(false);
                    } else {
                        // Prompt user to select a save file
                        javax.swing.JOptionPane.showMessageDialog(GameSelect.this, "Please select a save file.", "No Save Selected", javax.swing.JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Initialize and add the label and combo box
        selectSaveLabel = new JLabel("Select Load File:");
        selectSaveLabel.setBounds(150, 90, 200, 30);
        this.add(selectSaveLabel);

        saveComboBox = new JComboBox<>();
        saveComboBox.setBounds(150, 120, 200, 30);
        saveComboBox.addItem("Select a load file");
        saveComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSave = (String) saveComboBox.getSelectedItem();
                if (selectedSave != null && !selectedSave.equals("Select a load file")) {
                    s = selectedSave;
                } else {
                    s = "";
                }
            }
        });
        this.add(saveComboBox);

        // Initially populate the combo box
        addSaveComboBox();

        // Update the combo box every second
        new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSaveComboBox();
            }
        }).start();

        this.add(backButton);
        this.add(okButton);
        this.setVisible(true);
    }

    /**
     * Updates the saveComboBox with the current list of save files.
     */
    public void addSaveComboBox() {
        File saveDir = new File("saves");
        String[] saveFiles = saveDir.list((dir, name) -> name.toLowerCase().endsWith(".dat"));

        String currentSelection = (String) saveComboBox.getSelectedItem();
        saveComboBox.removeAllItems();
        saveComboBox.addItem("Select a load file");

        if (saveFiles != null && saveFiles.length != 0) {
            for (String fileName : saveFiles) {
                String displayName = fileName.substring(0, fileName.lastIndexOf('.'));
                saveComboBox.addItem(displayName);
            }
        }

        // Restore the selection if possible
        if (currentSelection != null && ((DefaultComboBoxModel<String>) saveComboBox.getModel()).getIndexOf(currentSelection) != -1) {
            saveComboBox.setSelectedItem(currentSelection);
        } else {
            saveComboBox.setSelectedIndex(0);
            s = "";
        }
    }
}