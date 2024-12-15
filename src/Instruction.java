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
 * Instruction class displays the instruction screen.
 */
public class Instruction extends JLayeredPaneWithBackground {
    JButton backButton;

    /**
     * Constructor for Instruction.
     *
     * @throws IOException If there's an error loading resources.
     */
    public Instruction() throws IOException {
        super("images/InstructionSc.png");
        this.setPreferredSize(new Dimension(700, 700));
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
                Instruction.this.setVisible(false);
            }
        });

        this.add(backButton);
        this.setVisible(true);
    }
}
