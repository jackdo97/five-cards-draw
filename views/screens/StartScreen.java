package views.screens;

import views.MainMenuView;
import views.components.custom.*;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends BackgroundPanel implements MainMenuView {
    private JLabel labelTitle;
    private JButton buttonNew, buttonLoad;

    public StartScreen() {
        super("views/res/titleBG.jpg");

        labelTitle = new JLabel("Welcome to Poker");
        labelTitle.setFont(new Font("Symbola", Font.PLAIN, 55));

        buttonNew = (JButton) ScaledComponentFactory.createComponent(ScaledComponentType.BUTTON, "Start New Game");
        buttonNew.setAlignmentX(CENTER_ALIGNMENT);

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

    @Override
    public JButton getNewGameButton() {
        return buttonNew;
    }

    @Override
    public JButton getLoadGameButton() {
        return buttonLoad;
    }

    @Override
    public JPanel getView() {
        return this;
    }
}
