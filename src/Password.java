import javax.swing.JButton;
import javax.swing.JTextField;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Image;

/**
 * Password class allows users to enter a password to access the game.
 */
public class Password extends JLayeredPaneWithBackground {
    private ParentControl parentControl;
    private JButton okButton;
    private JButton backButton;
    private JTextField nameField;

    /**
     * Constructor for Password.
     *
     * @param pc The parent control instance.
     * @throws IOException If there's an error loading resources.
     */
    public Password(ParentControl pc) throws IOException {
        super("images/Password.png");

        this.setPreferredSize(new Dimension(500, 250));
        this.setLayout(null);

        parentControl = pc;

        BufferedImage ok = null, back = null;
        try {
            ok = ImageIO.read(new File("images/buttons/Ok.png"));
            back = ImageIO.read(new File("images/buttons/Cancel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        nameField = new JTextField();
        nameField.setBounds(100, 100, 300, 50);

        Image backImage = back.getScaledInstance(149, 47, Image.SCALE_SMOOTH);
        backButton = new JButton(new ImageIcon(backImage));
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.setBounds(100, 167, 149, 47);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Password.this.setVisible(false);
            }
        });

        Image okImage = ok.getScaledInstance(130, 62, Image.SCALE_SMOOTH);
        okButton = new JButton(new ImageIcon(okImage));
        okButton.setBorder(BorderFactory.createEmptyBorder());
        okButton.setContentAreaFilled(false);
        okButton.setBounds(285, 160, 130, 62);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = nameField.getText();
                if(s.equals("pass")){
                    Password.this.setVisible(false);
                    parentControl.setVisible(true);
                    parentControl.revalidate();
                    parentControl.repaint();
                } else {
                    nameField.setText("");
                }
            }
        });

        this.add(nameField);
        this.add(backButton);
        this.add(okButton);
        this.setVisible(true);
    }
}
