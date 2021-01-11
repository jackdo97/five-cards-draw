package views;

import views.components.HorizontalPanel;
import views.components.VerticalPanel;

import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {
    private static final Dimension TEXT_BOX_PREFERRED_SIZE = new Dimension(150, 20);

    private Window window;

    private JLabel playerLabel, anteLabel, moneyLabel;
    private JTextField numberOfPlayers, anteAmount, startingMoney;
    private JButton startGame, continueGame;

    public Settings(Window window) {
        super();
        this.window = window;

        playerLabel = new JLabel("Number of Players:");
        anteLabel = new JLabel("Ante Amount:");
        moneyLabel = new JLabel("Starting Money:");

        numberOfPlayers = new JTextField();
        numberOfPlayers.setPreferredSize(TEXT_BOX_PREFERRED_SIZE);
        anteAmount = new JTextField();
        anteAmount.setPreferredSize(TEXT_BOX_PREFERRED_SIZE);
        startingMoney = new JTextField();
        startingMoney.setPreferredSize(TEXT_BOX_PREFERRED_SIZE);

        startGame = new JButton("Start Game");
        startGame.addActionListener(event -> {
            window.setContent(new Table());
        });
        continueGame = new JButton("Load Game");

        JPanel hPanel = new HorizontalPanel(playerLabel, numberOfPlayers);
        JPanel hPanel1 = new HorizontalPanel(moneyLabel, startingMoney);
        JPanel hPanel2 = new HorizontalPanel(anteLabel, anteAmount);

        JPanel vPanel = new VerticalPanel(hPanel, hPanel1, hPanel2, startGame, continueGame);
        add(vPanel);
    }


}
