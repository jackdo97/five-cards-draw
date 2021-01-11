package views;

import javax.swing.*;
import java.awt.*;

public class HandPanel extends JPanel {
    private JLabel moneyLabel;
    private int money;

    public HandPanel(String playerName, int money) {
        super();
        this.money = money;
        setPreferredSize(new Dimension(700, 200));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel cards = new JPanel();
        cards.add(new CardPanel("A"));
        cards.add(new CardPanel("2"));
        cards.add(new CardPanel("2"));
        cards.add(new CardPanel("2"));
        cards.add(new CardPanel("2"));
        add(cards);

        String labelText = playerName + " Money:" + money;
        moneyLabel = new JLabel(labelText);
        add(moneyLabel);
    }
}
