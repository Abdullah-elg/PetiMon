import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

/**
 * JLayeredPaneWithBackground class allows for a JLayeredPane with a background image.
 */
public class JLayeredPaneWithBackground extends JLayeredPane{
    private Image backgroundImage;

    /**
     * Constructor for JLayeredPaneWithBackground.
     * 
     * @param fileName The file name of the background image.
     * @throws IOException If there's an error loading the image.
     */
    public JLayeredPaneWithBackground(String fileName) throws IOException {
        backgroundImage = ImageIO.read(new File(fileName));
    }
    
    /**
     * Paints the background image.
     * 
     * @param g The Graphics object.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
