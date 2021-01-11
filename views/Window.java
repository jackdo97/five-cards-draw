package views;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window {
    private JFrame frame;

    public Window(String title, int width, int height) {
        frame = new JFrame(title);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setContent(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.revalidate();
    }

    public JFrame getFrame() {
        return frame;
    }
}
