package views;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame frame;

    public Window(String title, int width, int height) {
        frame = new JFrame(title);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setContent(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
    }
}
