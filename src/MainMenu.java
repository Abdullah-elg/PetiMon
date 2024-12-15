import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.imageio.ImageIO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;

/**
 * MainMenu class is the main menu of the game.
 */
public class MainMenu extends JPanelWithBackground {
    private JFrame parentFrame;
    private PetSelection petSelection;
    private GameSelect gameSelect;
    private Instruction instructionScreen;
    private ParentControl parentControl;
    private Password passwordScreen;
    private Error errorScreen;
    private JButton startButton;
    private JButton loadButton;
    private JButton instructionButton;
    private JButton parentButton;
    private JButton exitButton;

    /**
     * Constructor for MainMenu.
     *
     * @param frame The parent JFrame.
     * @throws IOException If there's an error loading resources.
     */
    public MainMenu(JFrame frame) throws IOException {
        super("images/Menu.png");
        this.parentFrame = frame;

        this.setPreferredSize(new Dimension(1440, 810));
        this.setLayout(null);

        gameSelect = new GameSelect(parentFrame, this);
        gameSelect.setBounds(470, 280, 500, 250);
        gameSelect.setVisible(false);
        this.add(gameSelect);

        instructionScreen = new Instruction();
        instructionScreen.setBounds(370, 55, 700, 700);
        instructionScreen.setVisible(false);
        this.add(instructionScreen);

        parentControl = new ParentControl();
        parentControl.setBounds(370, 55, 700, 700);
        parentControl.setVisible(false);
        this.add(parentControl);

        passwordScreen = new Password(parentControl);
        passwordScreen.setBounds(470, 280, 500, 250);
        passwordScreen.setVisible(false);
        this.add(passwordScreen);

        errorScreen = new Error();
        errorScreen.setBounds(470, 280, 500, 250);
        errorScreen.setVisible(false);
        this.add(errorScreen);

        BufferedImage start = null, load = null, instruction = null, parent = null, exit = null;
        try {
            start = ImageIO.read(new File("images/Buttons/Start.png"));
            load = ImageIO.read(new File("images/Buttons/Load.png"));
            instruction = ImageIO.read(new File("images/Buttons/Instruction.png"));
            parent = ImageIO.read(new File("images/Buttons/Parent.png"));
            exit = ImageIO.read(new File("images/Buttons/Exit.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        startButton = new JButton(new ImageIcon(start));
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setContentAreaFilled(false);
        startButton.setBounds(543, 166, 355, 110);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) { 
                if (parentControl.isPlayAllowed()) {
                    try {
                        MainMenu.this.setVisible(false);

                        if (petSelection != null && petSelection.getParent() != null) {
                            parentFrame.getContentPane().remove(petSelection);
                        }

                        petSelection = new PetSelection(parentFrame);
                        petSelection.setBounds(0, 0, 1440, 810);
                        parentFrame.getContentPane().add(petSelection);

                        petSelection.setVisible(true);
                        parentFrame.revalidate();
                        parentFrame.repaint();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (!errorScreen.isVisible()) {
                        errorScreen.setVisible(true);
                        errorScreen.revalidate();
                        errorScreen.repaint();
                    }
                }
            }
        });

        loadButton = new JButton(new ImageIcon(load));
        loadButton.setBorder(BorderFactory.createEmptyBorder());
        loadButton.setContentAreaFilled(false);
        loadButton.setBounds(543, 296, 355, 110);
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (parentControl.isPlayAllowed()) {
                    if (!gameSelect.isVisible()) {
                        gameSelect.setVisible(true);
                        parentFrame.revalidate();
                        parentFrame.repaint();
                    }
                } else {
                    if (!errorScreen.isVisible()) {
                        errorScreen.setVisible(true);
                        errorScreen.revalidate();
                        errorScreen.repaint();
                    }
                }
            }
        });

        instructionButton = new JButton(new ImageIcon(instruction));
        instructionButton.setBorder(BorderFactory.createEmptyBorder());
        instructionButton.setContentAreaFilled(false);
        instructionButton.setBounds(543, 421, 355, 110);
        instructionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!instructionScreen.isVisible()) {
                    instructionScreen.setVisible(true);
                    instructionScreen.revalidate();
                    instructionScreen.repaint();
                }
            }
        });

        parentButton = new JButton(new ImageIcon(parent));
        parentButton.setBorder(BorderFactory.createEmptyBorder());
        parentButton.setContentAreaFilled(false);
        parentButton.setBounds(543, 551, 355, 110);
        parentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!passwordScreen.isVisible()) {
                    passwordScreen.setVisible(true);
                    passwordScreen.revalidate();
                    passwordScreen.repaint();
                }
            }
        });

        exitButton = new JButton(new ImageIcon(exit));
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.setContentAreaFilled(false);
        exitButton.setBounds(543, 681, 355, 110);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(startButton);
        this.add(loadButton);
        this.add(instructionButton);
        this.add(parentButton);
        this.add(exitButton);
        this.setVisible(true);
    }
}
