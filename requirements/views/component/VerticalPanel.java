package views.components;

import javax.swing.*;
import java.awt.*;

public class VerticalPanel extends JPanel {
    public VerticalPanel(JComponent... content) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (JComponent component: content) {
            add(component);
        }
    }
}
