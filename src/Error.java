import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 * Error Screen, when the user tries to play the game in non-allowed time.
 */
public class Error extends JLayeredPaneWithBackground {
    JButton backButton;

    /**
     * Constructor for the Error screen. It sets the background image and adds a back button.
     * 
     * @throws IOException if the image file is not found
     */
    public Error() throws IOException {
        super("images/Error.png");
        this.setPreferredSize(new Dimension(500, 250));
        this.setLayout(null);

        BufferedImage back = null;
        try {
            back = ImageIO.read(new File("images/buttons/Back.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        backButton = new JButton(new ImageIcon(back));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.setBounds(0, 0, 81, 81);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Error.this.setVisible(false);
            }
        });

        this.add(backButton);
        this.setVisible(true);
    }
}
