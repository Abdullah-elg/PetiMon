import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 * ShopPanel class allows users to buy food and gifts for their pets.
 */
public class ShopPanel extends JLayeredPaneWithBackground {
    private Game game;
    private JButton backButton;
    private JButton buyApple;
    private JButton buyBanana;
    private JButton buyOrange;
    private JButton buyStrawberry;
    private JButton buyWatermelon;
    private JButton buyBall;
    private JButton buyDoll;
    private JButton buyCar;
    private JButton buyTeddy;
    private JButton buyPuzzle;

    /**
     * Constructor for ShopPanel.
     *
     * @param gameSave The game instance.
     * @throws IOException If there's an error loading resources.
     */
    public ShopPanel(Game gameSave) throws IOException {
        super("images/Store.png");
        this.game = gameSave;

        this.setPreferredSize(new Dimension(700, 700));
        this.setLayout(null);

        BufferedImage back = null, buy = null;
        try {
            back = ImageIO.read(new File("images/buttons/Back.png"));
            buy = ImageIO.read(new File("images/buttons/Buy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        backButton = new JButton(new ImageIcon(back));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.setBounds(0, 0, 81, 81);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShopPanel.this.setVisible(false);
            }
        });

        buyApple = new JButton(new ImageIcon(buy));
        buyApple.setBorder(BorderFactory.createEmptyBorder());
        buyApple.setContentAreaFilled(false);
        buyApple.setBounds(81, 170, 99, 40);
        buyApple.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.buyFood("Apple");
            }
        });

        buyBanana = new JButton(new ImageIcon(buy));
        buyBanana.setBorder(BorderFactory.createEmptyBorder());
        buyBanana.setContentAreaFilled(false);
        buyBanana.setBounds(81, 279, 99, 40);
        buyBanana.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.buyFood("Banana");
            }
        });

        buyOrange = new JButton(new ImageIcon(buy));
        buyOrange.setBorder(BorderFactory.createEmptyBorder());
        buyOrange.setContentAreaFilled(false);
        buyOrange.setBounds(81, 388, 99, 40);
        buyOrange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.buyFood("Orange");
            }
        });

        buyStrawberry = new JButton(new ImageIcon(buy));
        buyStrawberry.setBorder(BorderFactory.createEmptyBorder());
        buyStrawberry.setContentAreaFilled(false);
        buyStrawberry.setBounds(81, 497, 99, 40);
        buyStrawberry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.buyFood("Strawberry");
            }
        });

        buyWatermelon = new JButton(new ImageIcon(buy));
        buyWatermelon.setBorder(BorderFactory.createEmptyBorder());
        buyWatermelon.setContentAreaFilled(false);
        buyWatermelon.setBounds(81, 606, 99, 40);
        buyWatermelon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.buyFood("Watermelon");
            }
        });

        buyBall = new JButton(new ImageIcon(buy));
        buyBall.setBorder(BorderFactory.createEmptyBorder());
        buyBall.setContentAreaFilled(false);
        buyBall.setBounds(380, 170, 99, 40);
        buyBall.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.buyGift("Ball");
            }
        });

        buyDoll = new JButton(new ImageIcon(buy));
        buyDoll.setBorder(BorderFactory.createEmptyBorder());
        buyDoll.setContentAreaFilled(false);
        buyDoll.setBounds(380, 279, 99, 40);
        buyDoll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.buyGift("Doll");
            }
        });

        buyCar = new JButton(new ImageIcon(buy));
        buyCar.setBorder(BorderFactory.createEmptyBorder());
        buyCar.setContentAreaFilled(false);
        buyCar.setBounds(380, 388, 99, 40);
        buyCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.buyGift("Toy Car");
            }
        });

        buyTeddy = new JButton(new ImageIcon(buy));
        buyTeddy.setBorder(BorderFactory.createEmptyBorder());
        buyTeddy.setContentAreaFilled(false);
        buyTeddy.setBounds(380, 497, 99, 40);
        buyTeddy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.buyGift("Teddy Bear");
            }
        });

        buyPuzzle = new JButton(new ImageIcon(buy));
        buyPuzzle.setBorder(BorderFactory.createEmptyBorder());
        buyPuzzle.setContentAreaFilled(false);
        buyPuzzle.setBounds(380, 606, 99, 40);
        buyPuzzle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.buyGift("Puzzle");
            }
        });
        
        this.add(backButton);
        this.add(buyApple);
        this.add(buyBanana);
        this.add(buyOrange);
        this.add(buyStrawberry);
        this.add(buyWatermelon);
        this.add(buyBall);
        this.add(buyDoll);
        this.add(buyCar);
        this.add(buyTeddy);
        this.add(buyPuzzle);
        this.setVisible(true);
    }
}
