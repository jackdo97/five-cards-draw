package views;

import views.components.*;

import javax.swing.*;
import java.awt.*;

public class StartGame extends BackgroundPanel {
    private JLabel labelTitle;
    private JButton buttonNew, buttonLoad;

    public StartGame(Window window) {
        super("views/res/titleBG.jpg");

        labelTitle = new JLabel("Welcome to Poker");
        labelTitle.setFont(new Font("Symbola", Font.PLAIN, 55));

        buttonNew = (JButton) ScaledComponentFactory.createComponent(ScaledComponentType.BUTTON, "Start New Game");
        buttonNew.setAlignmentX(CENTER_ALIGNMENT);
        buttonNew.addActionListener(event -> {
            window.setContent(new Settings(window, 3));
        });

        buttonLoad = (JButton) ScaledComponentFactory.createComponent(ScaledComponentType.BUTTON, "Load Game");
        buttonLoad.setAlignmentX(CENTER_ALIGNMENT);

        JPanel vPanel = PanelFactory.createPanel(PanelType.VERTICAL, labelTitle, buttonNew, buttonLoad);
        vPanel.add(Box.createRigidArea(new Dimension(500, 10)));

        SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, vPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, vPanel, 0, SpringLayout.VERTICAL_CENTER, this);
        setLayout(layout);
        add(vPanel);
    }
}
