import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.GraphicsEnvironment;

/**
 * GameScreen class is the main screen of the game. It displays the pet, buttons for actions, and the pet's status bars.
 */
public class GameScreen extends JLayeredPaneWithBackground {
    private JFrame parentFrame;
    private Game game;
    private JButton exerciseButton;
    private JButton playButton;
    private JButton vetButton;
    private JButton shopButton;
    private JButton inventoryButton;
    private JButton sleepButton;
    private JButton settingsButton;
    private JProgressBar healthBar;
    private JProgressBar hungerBar;
    private JProgressBar happinessBar;
    private JProgressBar sleepBar;
    private ShopPanel shopPanel;
    private InventoryPanel inventoryPanel;
    private Settings settingsPanel;
    private JLabel coinsLabel;
    private JLabel petName;
    private JLabel petLabel;
    private Font font;
    private Warnings warningScreen;
    private int healthFlag = 0;
    private int hungerFlag = 0;
    private int happinessFlag = 0;
    private int sleepFlag = 0;
    private int time = 0;

    /**
     * Constructor for GameScreen.
     *
     * @param gameSave The game instance to be displayed.
     * @param frame The parent JFrame.
     * @throws IOException If there's an error loading resources.
     */
    public GameScreen(Game gameSave, JFrame frame) throws IOException {
        super("images/background.png");
        this.parentFrame = frame;
        game = gameSave;
        shopPanel = new ShopPanel(game);
        inventoryPanel = new InventoryPanel(game);
        settingsPanel = new Settings(game, this, parentFrame);

        this.setPreferredSize(new Dimension(1440, 810));
        this.setLayout(null);

        shopPanel.setBounds(370, 55, 700, 700);
        shopPanel.setVisible(false);
        this.add(shopPanel);

        inventoryPanel.setBounds(370, 55, 700, 700);
        inventoryPanel.setVisible(false);
        this.add(inventoryPanel);

        settingsPanel.setBounds(370, 55, 700, 700);
        settingsPanel.setVisible(false);
        this.add(settingsPanel);

        BufferedImage exercise = null, play = null, vet = null, shop = null, inventory = null, sleep = null, settings = null;
        try {
            exercise = ImageIO.read(new File("images/buttons/Exercise.png"));
            play = ImageIO.read(new File("images/buttons/Play.png"));
            vet = ImageIO.read(new File("images/buttons/Vet.png"));
            shop = ImageIO.read(new File("images/buttons/Shop.png"));
            inventory = ImageIO.read(new File("images/buttons/Inventory.png"));
            sleep = ImageIO.read(new File("images/buttons/Sleep.png"));
            settings = ImageIO.read(new File("images/buttons/Settings.png"));

            File fontFile = new File("resources/fonts/PixelatedElegance.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, 25);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            font = new Font("Arial", Font.PLAIN, 25);
        }

        coinsLabel = new JLabel(Integer.toString(game.getCoins()));
        coinsLabel.setFont(font);
        coinsLabel.setForeground(Color.ORANGE);
        coinsLabel.setBounds(170, 460, 200, 40);

        settingsButton = new JButton(new ImageIcon(settings));
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBounds(1330, 10, 100, 100);
        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!settingsPanel.isVisible()) {
                    settingsPanel.setVisible(true);
                    GameScreen.this.revalidate();
                    GameScreen.this.repaint();
                }
            }
        });

        inventoryButton = new JButton(new ImageIcon(inventory));
        inventoryButton.setBorder(BorderFactory.createEmptyBorder());
        inventoryButton.setContentAreaFilled(false);
        inventoryButton.setBounds(25, 300, 194, 64);
        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!inventoryPanel.isVisible()) {
                    inventoryPanel.setVisible(true);
                    GameScreen.this.revalidate();
                    GameScreen.this.repaint();
                }
            }
        });

        sleepButton = new JButton(new ImageIcon(sleep));
        sleepButton.setBorder(BorderFactory.createEmptyBorder());
        sleepButton.setContentAreaFilled(false);
        sleepButton.setBounds(1230, 340, 194, 64);
        sleepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.goToBed();
            }
        });

        exerciseButton = new JButton(new ImageIcon(exercise));
        exerciseButton.setBorder(BorderFactory.createEmptyBorder());
        exerciseButton.setContentAreaFilled(false);
        exerciseButton.setBounds(1230, 420, 194, 64);
        exerciseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.exercise();
            }
        });

        // add Play Cooldown
        playButton = new JButton(new ImageIcon(play));
        playButton.setBorder(BorderFactory.createEmptyBorder());
        playButton.setContentAreaFilled(false);
        playButton.setBounds(1230, 500, 194, 64);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.playWithPet();
            }
        });

        // add vet cooldown
        vetButton = new JButton(new ImageIcon(vet));
        vetButton.setBorder(BorderFactory.createEmptyBorder());
        vetButton.setContentAreaFilled(false);
        vetButton.setBounds(1230, 580, 194, 64);
        vetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.takeToVet();
            }
        });

        shopButton = new JButton(new ImageIcon(shop));
        shopButton.setBorder(BorderFactory.createEmptyBorder());
        shopButton.setContentAreaFilled(false);
        shopButton.setBounds(90, 570, 194, 64);
        shopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!shopPanel.isVisible()) {
                    shopPanel.setVisible(true);
                    GameScreen.this.revalidate();
                    GameScreen.this.repaint();
                }
            }
        });

        petName = new JLabel(game.getPet().getName() + " - " + game.getPetType());
        petName.setFont(font);
        petName.setForeground(Color.WHITE);
        petName.setBounds(25, 15, 1300, 40);

        healthBar = new JProgressBar(0, 100);
        healthBar.setValue(100);
        healthBar.setString("Health");
        healthBar.setStringPainted(false);
        healthBar.setBounds(50, 70, 200, 15);
        healthBar.setForeground(Color.GREEN);
        healthBar.setBackground(Color.BLACK);
        healthBar.setUI(new CustomProgressBarUI());

        hungerBar = new JProgressBar(0, game.getPet().getMaxFullness());
        hungerBar.setValue(game.getPet().getMaxFullness());
        hungerBar.setString("Hunger");
        hungerBar.setStringPainted(false);
        hungerBar.setBounds(50, 110, 2*game.getPet().getMaxFullness(), 15);
        hungerBar.setForeground(Color.RED);
        hungerBar.setBackground(Color.BLACK);
        hungerBar.setUI(new CustomProgressBarUI());

        happinessBar = new JProgressBar(0, game.getPet().getMaxHappiness());
        happinessBar.setValue(game.getPet().getMaxHappiness());
        happinessBar.setString("Happiness");
        happinessBar.setStringPainted(false);
        happinessBar.setBounds(50, 150, 2*game.getPet().getMaxHappiness(), 15);
        happinessBar.setForeground(Color.YELLOW);
        happinessBar.setBackground(Color.BLACK);
        happinessBar.setUI(new CustomProgressBarUI());

        sleepBar = new JProgressBar(0, game.getPet().getMaxSleep());
        sleepBar.setValue(game.getPet().getMaxSleep());
        sleepBar.setString("Sleep");
        sleepBar.setStringPainted(false);
        sleepBar.setBounds(50, 190, 2*game.getPet().getMaxSleep(), 15);
        sleepBar.setForeground(Color.BLUE);
        sleepBar.setBackground(Color.BLACK);
        sleepBar.setUI(new CustomProgressBarUI());

        petLabel = new JLabel();
        this.add(petLabel);
        this.add(petName);
        this.add(coinsLabel);
        this.add(settingsButton);
        this.add(sleepButton);
        this.add(exerciseButton);
        this.add(playButton);
        this.add(vetButton);
        this.add(shopButton);
        this.add(inventoryButton);
        this.add(healthBar);
        this.add(hungerBar);
        this.add(happinessBar);
        this.add(sleepBar);
        this.setVisible(true);

        new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (GameScreen.this.isVisible()) {
                    time++;
                }
                coinsLabel.setText(Integer.toString(game.getCoins()));
                updateBars();
                updateSprite();
            }
        }).start();
    }

    /**
     * Method to update the status bars of the pet.
     */
    public void updateBars() {
        if(game.getPet().getHealth() > 0 && shopPanel.isVisible() == false && settingsPanel.isVisible() == false && this.isVisible() == true) {
            game.update(); 
            healthBar.setValue(game.getPet().getHealth());
            hungerBar.setValue(game.getPet().getFullness());
            happinessBar.setValue(game.getPet().getHappiness());
            sleepBar.setValue(game.getPet().getSleep());
            if (healthFlag == 1 && game.getPet().getHealth() > 25) {
                healthFlag = 0;
            }
            if (hungerFlag == 1 && game.getPet().getFullness() > 0.25*game.getPet().getMaxFullness()) {
                hungerFlag = 0;
            }
            if (happinessFlag == 1 && game.getPet().getHappiness() > 0.25*game.getPet().getMaxHappiness()) {
                happinessFlag = 0;
            }
            if (sleepFlag == 1 && game.getPet().getSleep() > 0.25*game.getPet().getMaxSleep()) {
                sleepFlag = 0;
            }
            try {
                if (game.getPet().getHealth() <= 25 && healthFlag == 0) {
                    warningScreen = new Warnings("Health");
                    warningScreen.setBounds(370, 15, 700, 700);
                    this.add(warningScreen);
                    warningScreen.setVisible(true);
                    this.revalidate();
                    this.repaint();
                    healthFlag = 1;
                } else if (game.getPet().getFullness() <= 0.25*game.getPet().getMaxFullness() && hungerFlag == 0) {
                    warningScreen = new Warnings("Hunger");
                    warningScreen.setBounds(370, 55, 700, 700);
                    this.add(warningScreen);
                    warningScreen.setVisible(true);
                    this.revalidate();
                    this.repaint();
                    hungerFlag = 1;
                } else if (game.getPet().getHappiness() <= 0.25*game.getPet().getMaxHappiness() && happinessFlag == 0) {
                    warningScreen = new Warnings("Happiness");
                    warningScreen.setBounds(370, 55, 700, 700);
                    this.add(warningScreen);
                    warningScreen.setVisible(true);
                    this.revalidate();
                    this.repaint();
                    happinessFlag = 1;
                } else if (game.getPet().getSleep() <= 0.25*game.getPet().getMaxSleep() && sleepFlag == 0) {
                    warningScreen =  new Warnings("Sleep");
                    warningScreen.setBounds(370, 55, 700, 700);
                    this.add(warningScreen);
                    warningScreen.setVisible(true);
                    this.revalidate();
                    this.repaint();
                    sleepFlag = 1;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to update the sprite of the pet.
     */
    public void updateSprite() {
        if (game.getPetType().equals("BlueGuy")) {
            try {
                BufferedImage blueGuy = ImageIO.read(new File("images/BlueGuy/" + game.getPet().getState().toString() + ".png"));
                ImageIcon petImage = new ImageIcon(blueGuy);
                petLabel.setIcon(petImage);
                petLabel.setBounds(519, 195, 402, 621);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (game.getPetType().equals("GreenGuy")) {
            try {
                BufferedImage greenGuy = ImageIO.read(new File("images/GreenGuy/" + game.getPet().getState().toString() + ".png"));
                Image petimage1 = greenGuy.getScaledInstance(402, 421, Image.SCALE_SMOOTH);
                ImageIcon petImage = new ImageIcon(petimage1);
                petLabel.setIcon(petImage);
                petLabel.setBounds(519, 270, 402, 421);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (game.getPetType().equals("YellowGuy")) {
            try {
                BufferedImage yellowguy = ImageIO.read(new File("images/YellowGuy/" + game.getPet().getState().toString() + ".png"));
                if (game.getPet().getState() == PetState.DEAD || game.getPet().getState() == PetState.SLEEPING) {
                    Image petimage1 = yellowguy.getScaledInstance(402, 421, Image.SCALE_SMOOTH);
                    ImageIcon petImage = new ImageIcon(petimage1);
                    petLabel.setIcon(petImage);
                    petLabel.setBounds(519, 270, 402, 421);
                } else {
                    Image petimage1 = yellowguy.getScaledInstance(402, 621, Image.SCALE_SMOOTH);
                    ImageIcon petImage = new ImageIcon(petimage1);
                    petLabel.setIcon(petImage);
                    petLabel.setBounds(519, 195, 402, 621);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        petLabel.revalidate();
        petLabel.repaint();
        // this.revalidate();
        // this.repaint();
    }

    /**
     * Method to get the time played.
     *
     * @return The time played.
     */
    public int getTime() {
        return time;
    }
}
