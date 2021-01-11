package views.components;

import javax.swing.*;
import java.awt.*;

public class HorizontalPanel extends JPanel {
    public HorizontalPanel(JComponent... content) {
        super(new FlowLayout());
        for (JComponent component: content) {
            add(component);
        }
    }
}
