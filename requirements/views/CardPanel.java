package views;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    private static final Dimension PREFERRED_SIZE = new Dimension(125, 175);

    private JLabel card;

    public CardPanel(String card) {
        super();
        setPreferredSize(PREFERRED_SIZE);
        this.card = new JLabel(card);
        add(this.card);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, 125, 175);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Symbola", Font.BOLD, 50));
        g2d.drawString("A", 50, 100);
    }
}
