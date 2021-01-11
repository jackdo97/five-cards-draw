package views;

import javax.swing.*;
import java.awt.*;

public class Table extends JPanel {
    public Table() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new HandPanel("Opponent", 100));
        add(new HandPanel("Player", 100));
        add(new PlayerOptions());
    }
}
