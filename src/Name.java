import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Image;

/**
 * Name class allows users to name their pet.
 */
public class Name extends JLayeredPaneWithBackground {
    private JFrame parentFrame;
    private String petType;
    private PetSelection petSelection;
    private JButton okButton;
    private JButton backButton;
    private JTextField nameField;

    /**
     * Constructor for Name.
     *
     * @param petTp The type of pet.
     * @param petSelect The pet selection instance.
     * @param frame he parent JFrame.
     * @throws IOException If there's an error loading resources.
     */
    public Name(String petTp, PetSelection petSelect, JFrame frame) throws IOException {
        super("images/Name.png");

        this.setPreferredSize(new Dimension(500, 250));
        this.setLayout(null);

        this.petType = petTp;
        this.petSelection = petSelect;
        this.parentFrame = frame;

        BufferedImage ok = null, back = null;
        try {
            ok = ImageIO.read(new File("images/buttons/Ok.png"));
            back = ImageIO.read(new File("images/buttons/Cancel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        nameField = new JTextField();
        nameField.setBounds(100, 100, 300, 50);

        Image backImage = back.getScaledInstance(149, 47, Image.SCALE_SMOOTH);
        backButton = new JButton(new ImageIcon(backImage));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.setBounds(100, 167, 149, 47);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Name.this.setVisible(false);
            }
        });

        Image okImage = ok.getScaledInstance(130, 62, Image.SCALE_SMOOTH);
        okButton = new JButton(new ImageIcon(okImage));
        okButton.setBorder(BorderFactory.createEmptyBorder());
        okButton.setContentAreaFilled(false);
        okButton.setBounds(285, 160, 130, 62);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = nameField.getText();
                    if (s.equals("")) {
                        s = "Pet";
                    }
                    Game game = new Game(s, 50, 100, 100, 100, petType);
                    GameScreen gameScreen = new GameScreen(game, parentFrame);
                    gameScreen.setBounds(0, 0, 1440, 810);
                    gameScreen.setVisible(true);
                    petSelection.setVisible(false);
                    parentFrame.getContentPane().add(gameScreen);
                    parentFrame.revalidate();
                    parentFrame.repaint();
                    Name.this.setVisible(false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        this.add(nameField);
        this.add(backButton);
        this.add(okButton);
        this.setVisible(true);
    }
}
