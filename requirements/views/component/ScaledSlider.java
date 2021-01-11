package views.components;

import javax.swing.*;
import java.awt.*;

public class ScaledSlider extends JSlider {
    public ScaledSlider(Font defaultFont, int value) {
        super(1, value);
        setMaximumSize(new Dimension(getPreferredSize().width, 65));
    }
}
