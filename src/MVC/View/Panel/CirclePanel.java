package MVC.View.Panel;

import javax.swing.*;
import java.awt.*;

/**
 * CirclePanel represents a single cell in the Connect Four game grid.
 * Each cell can change color to represent a player's piece (RED/YELLOW).
 */
public class CirclePanel extends JPanel {
    private Color color;
    private boolean highlighted;

    public CirclePanel(Color color) {
        this.color = color;
        this.highlighted = false;
        this.setPreferredSize(new Dimension(80, 80));
        this.setBackground(Color.BLUE);
    }

    /**
     * Changes the color of the circle in the panel.
     *
     * @param color The new color for the circle.
     */
    public void changeColor(Color color) {
        this.color = color;
        this.highlighted = false;
        this.repaint();
    }

    /**
     * Changes the color of the circle and highlights it for the last action.
     *
     * @param color The new color for the circle.
     * @param highlighted Whether the circle should be highlighted.
     */
    public void changeColor(Color color, Boolean highlighted) {
        this.color = color;
        this.highlighted = highlighted;
        this.repaint();
    }

    /**
     * Paints the circle with the specified color and highlights it if needed.
     *
     * @param g The Graphics object used for rendering.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(color);
        int size = Math.min(getWidth(), getHeight()) - 10;
        g.fillOval(5, 5, size, size);

        if (highlighted) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.GREEN);
            g2.setStroke(new BasicStroke(3));
            g2.drawOval(5, 5, size, size);
        }
    }
}
