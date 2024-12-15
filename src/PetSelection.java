import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * PetSelection class allows users to select a pet.
 */
public class PetSelection extends JLayeredPaneWithBackground{
    private JFrame parentFrame;
    private JButton eButton;
    private JButton blueGuy;
    private JButton greenGuy;
    private JButton yellowGuy;
    private Name name;

    /**
     * Constructor for PetSelection.
     *
     * @param frame The parent JFrame.
     * @throws IOException If there's an error loading resources.
     */
    public PetSelection(JFrame frame) throws IOException {
        super("images/PetSelection.png");
        this.parentFrame = frame;

        this.setPreferredSize(new Dimension(1440, 810));
        this.setLayout(null);

        BufferedImage exit = null, select = null;
        try {
            exit = ImageIO.read(new File("images/buttons/Exit2.png"));
            select = ImageIO.read(new File("images/buttons/Select.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        eButton = new JButton(new ImageIcon(exit));
        eButton.setBorder(BorderFactory.createEmptyBorder());
        eButton.setContentAreaFilled(false);
        eButton.setBounds(10, 10, 79, 74);
        eButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PetSelection.this.setVisible(false);

                Component[] components = parentFrame.getContentPane().getComponents();
                for (Component component : components) {
                    if (component instanceof MainMenu) {
                        component.setVisible(true);
                        break;
                    }
                }
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });

        blueGuy = new JButton(new ImageIcon(select));
        blueGuy.setBorder(BorderFactory.createEmptyBorder());
        blueGuy.setContentAreaFilled(false);
        blueGuy.setBounds(147, 725, 191, 59);
        blueGuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    name = new Name("BlueGuy", PetSelection.this, parentFrame);
                    name.setBounds(470, 280, 500, 250);
                    name.setVisible(true);
                    PetSelection.this.add(name);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        greenGuy = new JButton(new ImageIcon(select));
        greenGuy.setBorder(BorderFactory.createEmptyBorder());
        greenGuy.setContentAreaFilled(false);
        greenGuy.setBounds(625, 725, 191, 59);
        greenGuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    name = new Name("GreenGuy", PetSelection.this, parentFrame);
                    name.setBounds(470, 280, 500, 250);
                    name.setVisible(true);
                    PetSelection.this.add(name);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

       
        yellowGuy = new JButton(new ImageIcon(select));
        yellowGuy.setBorder(BorderFactory.createEmptyBorder());
        yellowGuy.setContentAreaFilled(false);
        yellowGuy.setBounds(1103, 725, 191, 59);
        yellowGuy.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
                try {
                    name = new Name("YellowGuy", PetSelection.this, parentFrame);
                    name.setBounds(470, 280, 500, 250);
                    name.setVisible(true);
                    PetSelection.this.add(name);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        this.add(eButton);
        this.add(blueGuy);
        this.add(greenGuy);
        this.add(yellowGuy);
        this.setVisible(true);
    }
}
