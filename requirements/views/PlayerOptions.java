package views;

import javax.swing.*;
import java.awt.*;

public class PlayerOptions extends JPanel {

    private JButton raise, call, check, fold;
    private JTextField betAmount;

    public PlayerOptions() {
        raise = new JButton("Raise");
        call = new JButton("Call");
        check = new JButton("Check");
        fold = new JButton("Fold");
        betAmount = new JTextField();
        betAmount.setPreferredSize(new Dimension(200, 30));

        add(betAmount);
        add(raise);
        add(call);
        add(check);
        add(fold);

        setBackground(Color.BLUE);
    }
}
