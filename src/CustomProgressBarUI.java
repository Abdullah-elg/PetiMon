import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import javax.swing.*;

/**
 * Custom UI for the progress bar.
 */
public class CustomProgressBarUI extends BasicProgressBarUI {
    private int arcWidth = 15;
    private int arcHeight = 15;
    
    /**
     * Paints the progress bar in the determinate state.
     */
    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        Insets b = progressBar.getInsets(); // Area for border
        int width = progressBar.getWidth();
        int height = progressBar.getHeight();

        // Set anti-aliasing for smooth edges
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Paint the background
        g2.setColor(progressBar.getBackground());
        g2.fillRoundRect(
            b.left, b.top,
            width - b.right - b.left,
            height - b.top - b.bottom,
            arcWidth, arcHeight
        );

        // Calculate the width of the progress indicator
        int amountFull = getAmountFull(b, width - b.right - b.left, height - b.top - b.bottom);

        // Paint the progress indicator
        g2.setColor(progressBar.getForeground());
        g2.fillRoundRect(
            b.left, b.top,
            amountFull,
            height - b.top - b.bottom,
            arcWidth, arcHeight
        );

        // Paint the progress string if needed
        if (progressBar.isStringPainted()) {
            paintString(g2, b.left, b.top,
                width - b.right - b.left,
                height - b.top - b.bottom,
                amountFull, b);
        }

        g2.dispose();
    }

    /**
     * Paints the progress bar in the indeterminate state.
     */
    @Override
    protected void paintIndeterminate(Graphics g, JComponent c) {
        // For indeterminate state, you can implement similar painting logic
        super.paintIndeterminate(g, c);
    }
}