package views;

import javax.swing.*;

public interface UserActionsView {
    JSlider getBetSlider();
    JButton getBetButton();
    JButton getCheckButton();
    JButton getCallButton();
    JButton getFoldButton();
    JPanel getView();
    JLabel getLabelBet();
    JButton getCommitBetButton();
    JButton getDoneButton();
    JCheckBox getFirstCheckBox();
    JCheckBox getSecondCheckBox();
    JCheckBox getThirdCheckBox();
    JCheckBox getFourthCheckBox();
    JCheckBox getFifthCheckBox();

    void setBetSliderIsVisible(boolean isVisible);
    void setDoneButtonIsVisible(boolean isVisible);
    void setCallButtonIsVisible(boolean isVisible);
    void setCheckButtonIsVisible(boolean isVisible);
    void setBetButtonIsVisible(boolean isVisible);
    void setFoldButtonIsVisible(boolean isVisible);
    void setCheckBoxesIsVisible(boolean isVisible);
}
