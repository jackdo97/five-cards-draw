package views;

import javax.swing.*;

public interface TableView {
    HandView[] getHands();
    JLabel getPotLabel();
    JLabel getRoundLabel();
    UserActionsView getUserActionsView();
    JPanel getView();
}
