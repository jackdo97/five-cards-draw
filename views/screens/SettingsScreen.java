package views.screens;

import views.OptionsView;
import views.components.custom.*;

import javax.swing.*;
import java.awt.*;

public class SettingsScreen extends BackgroundPanel implements OptionsView {

    private JLabel labelOpponents, labelMoney, labelAnte;
    private JSlider sliderOpponents;
    private JTextField textMoney, textAnte;
    private JButton buttonStart;

    public SettingsScreen(int maxPlayers) {
        super("views/res/titleBG.jpg");

        // Initialize components
        // Number of player options
        labelOpponents = (JLabel) ScaledComponentFactory.createComponent(ScaledComponentType.LABEL, "Number of Opponents:");
        sliderOpponents = (JSlider) ScaledComponentFactory.createComponent(ScaledComponentType.SLIDER, maxPlayers);
        sliderOpponents.setPaintLabels(true);
        sliderOpponents.setMajorTickSpacing(1);
        JPanel panelPlayers = PanelFactory.createPanel(PanelType.VERTICAL, labelOpponents, sliderOpponents);

        // Starting money amount
        labelMoney = (JLabel) ScaledComponentFactory.createComponent(ScaledComponentType.LABEL, "Starting Money:");
        textMoney = (JTextField) ScaledComponentFactory.createComponent(ScaledComponentType.TEXTFIELD, 10);
        JPanel panelMoney = PanelFactory.createPanel(PanelType.VERTICAL, labelMoney, textMoney);

        // Ante amount
        labelAnte = (JLabel) ScaledComponentFactory.createComponent(ScaledComponentType.LABEL, "Ante Amount:");
        textAnte = (JTextField) ScaledComponentFactory.createComponent(ScaledComponentType.TEXTFIELD, 10);
        JPanel panelAnte = PanelFactory.createPanel(PanelType.VERTICAL, labelAnte, textAnte);

        buttonStart = (JButton) ScaledComponentFactory.createComponent(ScaledComponentType.BUTTON, "Start Game");
        JPanel panelStart = PanelFactory.createPanel(PanelType.HORIZONTAL, buttonStart);

        JPanel vPanel = PanelFactory.createPanel(PanelType.VERTICAL, panelPlayers, panelMoney, panelAnte, panelStart);
        vPanel.add(Box.createRigidArea(new Dimension(300, 10)));
        SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, vPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, vPanel, 0, SpringLayout.VERTICAL_CENTER, this);
        setLayout(layout);
        add(vPanel);
    }

    @Override
    public JSlider getOpponentSlider() {
        return sliderOpponents;
    }

    @Override
    public JTextField getStartingMoneyTextField() {
        return textMoney;
    }

    @Override
    public JTextField getAnteTextField() {
        return textAnte;
    }

    @Override
    public JButton getStartGameButton() {
        return buttonStart;
    }

    @Override
    public JPanel getView() {
        return this;
    }
}
