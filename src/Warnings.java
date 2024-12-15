import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

/**
 * Warnings class displays a warning message to the user.
 */
public class Warnings extends JLayeredPaneWithBackground {
    private JButton backButton;
    private String warning;
    private JLabel warningLabel;
    private Font font;

    /**
     * Constructor for Warnings.
     *
     * @param warn The warning message to display.
     * @throws IOException If there's an error loading resources.
     */
    public Warnings(String warn) throws IOException {
        super("images/Warning.png");

        this.setPreferredSize(new Dimension(700, 100));
        this.setLayout(null);

        this.warning = warn;
        BufferedImage back = null;
        try {
            back = ImageIO.read(new File("images/buttons/Back.png"));
            File fontFile = new File("resources/fonts/PixelatedElegance.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, 15);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            font = new Font("Arial", Font.PLAIN, 25);
        }

        backButton = new JButton(new ImageIcon(back));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.setBounds(0, 8, 81, 81);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Warnings.this.setVisible(false);
            }
        });

        warningLabel = new JLabel("WARNING: Pet's " + warning + " is under 25%!");
        warningLabel.setFont(font);
        warningLabel.setForeground(Color.WHITE);
        warningLabel.setBounds(100, 0, 600, 100);

        this.add(backButton);
        this.add(warningLabel);
        this.setVisible(true);

        new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Warnings.this.setVisible(false);
            }
        }).start();
    }
}
