import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.FontFormatException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.GraphicsEnvironment;

/**
 * InventoryPanel class allows users to view and use items in their inventory.
 */
public class InventoryPanel extends JLayeredPaneWithBackground {
    private Game game;
    private JButton backButton;
    private JLabel apples;
    private JLabel bananas;
    private JLabel oranges;
    private JLabel strawberries;
    private JLabel watermelons;
    private JLabel balls;
    private JLabel dolls;
    private JLabel cars;
    private JLabel bears;
    private JLabel puzzles;
    private JButton useApple;
    private JButton useBanana;
    private JButton useOrange;
    private JButton useStrawberry;
    private JButton useWatermelon;
    private JButton useBall;
    private JButton useDoll;
    private JButton useCar;
    private JButton useBear;
    private JButton usePuzzle;
    private Font font;

    /**
     * Constructor for InventoryPanel.
     *
     * @param gameSave The game instance.
     * @throws IOException If there's an error loading resources.
     */
    public InventoryPanel(Game gameSave) throws IOException {
        super("images/Inv.png");
        this.game = gameSave;

        this.setPreferredSize(new Dimension(700, 700));
        this.setLayout(null);

        BufferedImage back = null, feed1 = null, gift1 = null;
        Image feed = null, gift = null;
        try {
            back = ImageIO.read(new File("images/buttons/Back.png"));
            feed1 = ImageIO.read(new File("images/buttons/Feed.png"));
            feed = feed1.getScaledInstance(100, 33, Image.SCALE_SMOOTH);
            gift1 = ImageIO.read(new File("images/buttons/Gift.png"));
            gift = gift1.getScaledInstance(100, 33, Image.SCALE_SMOOTH);

            File fontFile = new File("resources/fonts/PixelatedElegance.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, 15);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            font = new Font("Arial", Font.PLAIN, 25);
        }

        useApple = new JButton(new ImageIcon(feed));
        useApple.setBorder(BorderFactory.createEmptyBorder());
        useApple.setContentAreaFilled(false);
        useApple.setBounds(168, 170, 100, 33);
        useApple.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.feed(game.getInventory().getFood("Apple"));
            }
        });

        useBanana = new JButton(new ImageIcon(feed));
        useBanana.setBorder(BorderFactory.createEmptyBorder());
        useBanana.setContentAreaFilled(false);
        useBanana.setBounds(168, 279, 100, 33);
        useBanana.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.feed(game.getInventory().getFood("Banana"));
            }
        });

        useOrange = new JButton(new ImageIcon(feed));
        useOrange.setBorder(BorderFactory.createEmptyBorder());
        useOrange.setContentAreaFilled(false);
        useOrange.setBounds(168, 388, 100, 33);
        useOrange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.feed(game.getInventory().getFood("Orange"));
            }
        });

        useStrawberry = new JButton(new ImageIcon(feed));
        useStrawberry.setBorder(BorderFactory.createEmptyBorder());
        useStrawberry.setContentAreaFilled(false);
        useStrawberry.setBounds(168, 497, 100, 33);
        useStrawberry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.feed(game.getInventory().getFood("Strawberry"));
            }
        });

        useWatermelon = new JButton(new ImageIcon(feed));
        useWatermelon.setBorder(BorderFactory.createEmptyBorder());
        useWatermelon.setContentAreaFilled(false);
        useWatermelon.setBounds(168, 606, 100, 33);
        useWatermelon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.feed(game.getInventory().getFood("Watermelon"));
            }
        });

        useBall = new JButton(new ImageIcon(gift));
        useBall.setBorder(BorderFactory.createEmptyBorder());
        useBall.setContentAreaFilled(false);
        useBall.setBounds(405, 170, 100, 33);
        useBall.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.giveGift(game.getInventory().getGift("Ball"));
            }
        });

        useDoll = new JButton(new ImageIcon(gift));
        useDoll.setBorder(BorderFactory.createEmptyBorder());
        useDoll.setContentAreaFilled(false);
        useDoll.setBounds(405, 279, 100, 33);
        useDoll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.giveGift(game.getInventory().getGift("Doll"));
            }
        });

        useCar = new JButton(new ImageIcon(gift));
        useCar.setBorder(BorderFactory.createEmptyBorder());
        useCar.setContentAreaFilled(false);
        useCar.setBounds(405, 388, 100, 33);
        useCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.giveGift(game.getInventory().getGift("Toy Car"));
            }
        });

        useBear = new JButton(new ImageIcon(gift));
        useBear.setBorder(BorderFactory.createEmptyBorder());
        useBear.setContentAreaFilled(false);
        useBear.setBounds(405, 497, 100, 33);
        useBear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.giveGift(game.getInventory().getGift("Teddy Bear"));
            }
        });

        usePuzzle = new JButton(new ImageIcon(gift));
        usePuzzle.setBorder(BorderFactory.createEmptyBorder());
        usePuzzle.setContentAreaFilled(false);
        usePuzzle.setBounds(405, 606, 100, 33);
        usePuzzle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.giveGift(game.getInventory().getGift("Puzzle"));
            }
        });

        int appleCount = game.getInventory().getFoodItem("Apple");
        apples = new JLabel(Integer.toString(appleCount));
        apples.setFont(font);
        apples.setBounds(280, 162, 27, 24);

        int bananaCount = game.getInventory().getFoodItem("Banana");
        bananas = new JLabel(Integer.toString(bananaCount));
        bananas.setFont(font);
        bananas.setBounds(280, 271, 27, 24);

        int orangeCount = game.getInventory().getFoodItem("Orange");
        oranges = new JLabel(Integer.toString(orangeCount));
        oranges.setFont(font);
        oranges.setBounds(280, 380, 27, 24);

        int strawberryCount = game.getInventory().getFoodItem("Strawberry");
        strawberries = new JLabel(Integer.toString(strawberryCount));
        strawberries.setFont(font);
        strawberries.setBounds(280, 489, 27, 24);

        int watermelonCount = game.getInventory().getFoodItem("Watermelon");
        watermelons = new JLabel(Integer.toString(watermelonCount));
        watermelons.setFont(font);
        watermelons.setBounds(280, 598, 27, 24);

        int ballCount = game.getInventory().getGiftItem("Ball");
        balls = new JLabel(Integer.toString(ballCount));
        balls.setFont(font);
        balls.setBounds(384, 162, 27, 24);

        int dollCount = game.getInventory().getGiftItem("Doll");
        dolls = new JLabel(Integer.toString(dollCount));
        dolls.setFont(font);
        dolls.setBounds(384, 271, 27, 24);

        int carCount = game.getInventory().getGiftItem("Toy Car");
        cars = new JLabel(Integer.toString(carCount));
        cars.setFont(font);
        cars.setBounds(384, 380, 27, 24);

        int bearCount = game.getInventory().getGiftItem("Teddy Bear");
        bears = new JLabel(Integer.toString(bearCount));
        bears.setFont(font);
        bears.setBounds(384, 489, 27, 24);

        int puzzleCount = game.getInventory().getGiftItem("Puzzle");
        puzzles = new JLabel(Integer.toString(puzzleCount));
        puzzles.setFont(font);
        puzzles.setBounds(384, 598, 27, 24);

        backButton = new JButton(new ImageIcon(back));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.setBounds(0, 0, 81, 81);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InventoryPanel.this.setVisible(false);
            }
        });

        this.add(useApple);
        this.add(useBanana);
        this.add(useOrange);
        this.add(useStrawberry);
        this.add(useWatermelon);
        this.add(useBall);
        this.add(useDoll);
        this.add(useCar);
        this.add(useBear);
        this.add(usePuzzle);
        this.add(apples);
        this.add(bananas);
        this.add(oranges);
        this.add(strawberries);
        this.add(watermelons);
        this.add(balls);
        this.add(dolls);
        this.add(cars);
        this.add(bears);
        this.add(puzzles);
        this.add(backButton);
        this.setVisible(true);

        new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateInventory();
            }
        }).start();
    }

    /**
     * Updates the inventory.
     */
    public void updateInventory() {
        apples.setText(Integer.toString(game.getInventory().getFoodItem("Apple")));
        bananas.setText(Integer.toString(game.getInventory().getFoodItem("Banana")));
        oranges.setText(Integer.toString(game.getInventory().getFoodItem("Orange")));
        strawberries.setText(Integer.toString(game.getInventory().getFoodItem("Strawberry")));
        watermelons.setText(Integer.toString(game.getInventory().getFoodItem("Watermelon")));
        balls.setText(Integer.toString(game.getInventory().getGiftItem("Ball")));
        dolls.setText(Integer.toString(game.getInventory().getGiftItem("Doll")));
        cars.setText(Integer.toString(game.getInventory().getGiftItem("Toy Car")));
        bears.setText(Integer.toString(game.getInventory().getGiftItem("Teddy Bear")));
        puzzles.setText(Integer.toString(game.getInventory().getGiftItem("Puzzle")));
    }
}
