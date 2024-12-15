import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * JPanelWithBackground class allows for a JPanel with a background image.
 */
public class JPanelWithBackground extends JPanel {
    private Image backgroundImage;

    /**
     * Constructor for JPanelWithBackground.
     * 
     * @param fileName The file name of the background image.
     * @throws IOException If there's an error loading the image.
     */
    public JPanelWithBackground(String fileName) throws IOException {
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