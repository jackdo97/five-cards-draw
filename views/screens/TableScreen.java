package views.screens;

import model.Card;
import model.Opponent;
import model.Player;
import views.TableView;
import views.UserActionsView;
import views.components.CardPanel;
import views.components.HandPanel;
import views.HandView;
import views.components.UserOptionsPanel;
import views.components.custom.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TableScreen extends BackgroundPanel implements TableView {
    private JLabel labelPot, labelRound;
    private HandView[] hands = new HandPanel[4];
    private UserActionsView userActions;

    public TableScreen(Player user, ArrayList<Opponent> opponents, int round, boolean isRevealed,int currentPot) {
        super("views/res/tableBG.jpg");
        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        Player tempPlayer;
        switch (opponents.size()) {
            case 3:
                tempPlayer = opponents.get(2);
                hands[3] = new HandPanel(tempPlayer, isRevealed, false);
                add(hands[3].getView());
                layout.putConstraint(SpringLayout.WEST, hands[3].getView(), 15, SpringLayout.WEST, this);
                layout.putConstraint(SpringLayout.VERTICAL_CENTER, hands[3].getView(), 0, SpringLayout.VERTICAL_CENTER, this);

            case 2:
                tempPlayer = opponents.get(1);
                hands[2] = new HandPanel(tempPlayer, isRevealed, false);
                add(hands[2].getView());
                layout.putConstraint(SpringLayout.EAST, hands[2].getView(), -15, SpringLayout.EAST, this);
                layout.putConstraint(SpringLayout.VERTICAL_CENTER, hands[2].getView(), 0, SpringLayout.VERTICAL_CENTER, this);

            case 1:
                tempPlayer = opponents.get(0);
                hands[1] = new HandPanel(tempPlayer, isRevealed, true);
                add(hands[1].getView());
                layout.putConstraint(SpringLayout.NORTH, hands[1].getView(), 15, SpringLayout.NORTH, this);
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, hands[1].getView(), 0, SpringLayout.HORIZONTAL_CENTER, this);

            default:
                tempPlayer = user;
                hands[0] = new HandPanel(tempPlayer, true, true);
                add(hands[0].getView());
                layout.putConstraint(SpringLayout.SOUTH, hands[0].getView(), -15, SpringLayout.SOUTH, this);
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, hands[0].getView(), 0, SpringLayout.HORIZONTAL_CENTER, this);

                userActions = new UserOptionsPanel(tempPlayer.getTotalMoney());
                add(userActions.getView());
                layout.putConstraint(SpringLayout.SOUTH, userActions.getView(), 0, SpringLayout.NORTH, hands[0].getView());
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, userActions.getView(), 0, SpringLayout.HORIZONTAL_CENTER, this);
                break;
        }

        labelRound = (JLabel) ScaledComponentFactory.createComponent(ScaledComponentType.LABEL, "Bet Round #" + round);
        labelRound.setAlignmentX(CENTER_ALIGNMENT);
        labelRound.setForeground(Color.WHITE);
        JPanel deckPanel = new CardPanel(new Card(1, Card.Suit.SPADE), true);
        labelPot = (JLabel) ScaledComponentFactory.createComponent(ScaledComponentType.LABEL, "POT: " + currentPot);
        labelPot.setForeground(Color.WHITE);
        JPanel potPanel = PanelFactory.createPanel(PanelType.HORIZONTAL, deckPanel, labelPot);
        potPanel.setOpaque(false);
        JPanel vPanel = PanelFactory.createPanel(PanelType.VERTICAL, labelRound, potPanel);
        vPanel.setOpaque(false);
        add(vPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, vPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, vPanel, 0, SpringLayout.VERTICAL_CENTER, this);
    }

    @Override
    public HandView[] getHands() {
        return hands;
    }

    @Override
    public UserActionsView getUserActionsView() {
        return userActions;
    }

    @Override
    public JPanel getView() {
        return this;
    }

    @Override
    public JLabel getPotLabel() {
        return labelPot;
    }

    @Override
    public JLabel getRoundLabel() {
        return labelRound;
    }
}
