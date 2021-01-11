mport model.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ControllerTests {
    @Test
    public void TableShouldHaveAtLeastTwoPlayers() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        assertEquals(true, controller.validNumOpponents(controller.getTable().getPlayers().size() - 1)) ;
    }

    @Test
    public void TableShouldNotHaveMoreThanFourPlayers() {
        ControllerTerminal controller = new ControllerTerminal(new Table(4, 100, 20));
        assertEquals(false, controller.validNumOpponents(controller.getTable().getPlayers().size() - 1));
    }

    @Test
    public void AllPlayersHaveTheSameStartingMoney() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        for (int i = 0; i < controller.getTable().getPlayers().size(); i++) {
            assertEquals(100, controller.getTable().getPlayers().get(i).getTotalMoney());
        }
    }

    @Test
    public void AllPlayersBetAnteAmount() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        for (int i = 0; i < controller.getTable().getPlayers().size(); i++) {
            assertEquals(80, controller.getTable().getPlayers().get(i).getTotalMoney());
        }
    }

    @Test
    public void AnteAmountsGoToPot() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        assertEquals(80, controller.getTable().getPot());
    }

    @Test
    public void PlayerBetValidationRoundOneShouldReturnTrue() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        assertEquals(true, controller.validBet(30, 1, 20));
    }

    @Test
    public void PlayerBetValidationRoundOneShouldReturnFalse() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        assertEquals(false, controller.validBet(10, 1, 20));
    }

    @Test
    public void PlayerBetValidationRoundTwoShouldReturnTrue() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        assertEquals(true, controller.validBet(50, 2, 40));
    }


    @Test
    public void PlayerBetValidationRoundTwoShouldReturnFalse() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        assertEquals(false, controller.validBet(10, 2, 20));
    }

    @Test
    public void UserBetRoundOne() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        controller.userBet(30, 1, 40);
        assertEquals(50, controller.getUser().getTotalMoney());
    }

    @Test
    public void UserBetRoundTwo() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        controller.userBet(30, 1, 40);
        controller.userBet(50, 2, 40);
        assertEquals(0, controller.getTable().getUser().getTotalMoney());
    }

    @Test
    public void PotAfterFirstRound() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        Table table = controller.getTable();
        controller.userBet(30, 1, 40);
        for (int i = 1; i < table.getPlayers().size(); i++) {
            Opponent opponent = (Opponent) table.getPlayers().get(i);
            table.addToPot(opponent.playBettingRound(table));
        }
        assertEquals(350, table.getPot());
    }

    @Test
    public void UserCannotBetAfterFolding() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        controller.userFold();
        controller.userBet(30, 1, 40);
        assertEquals(80, controller.getTable().getUser().getTotalMoney());
    }

    @Test
    public void UserCallRoundOne() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        controller.userCall(1, 20);
        assertEquals(60, controller.getTable().getUser().getTotalMoney());
    }

    @Test
    public void UserCallRoundTwo() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        controller.userCall(2, 50);
        assertEquals(30, controller.getTable().getUser().getTotalMoney());
    }

    @Test
    public void UserCannotCallAfterFolding() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        controller.userBet(30, 1, 20);
        controller.userFold();
        controller.userCall(2, 50);
        assertEquals(50, controller.getTable().getUser().getTotalMoney());
    }

    @Test
    public void UserCheckBeforeBet() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        assertEquals(true, controller.validCheck());
    }

    @Test
    public void UserCheckAfterBet() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        controller.userBet(30, 1, 40);
        assertEquals(false, controller.validCheck());
    }

    @Test
    public void UserFold() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.userFold();
        assertEquals(false, controller.getTable().getUser().inHand());
    }

    @Test
    public void UserReplaceCards() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 100, 20));
        controller.getTable().startNewRound();
        Hand oldHand = controller.getTable().getUser().getHand();
        ArrayList<Integer> cards = new ArrayList<Integer>();
        cards.add(0);
        cards.add(3);
        cards.add(4);
        controller.replaceCards(cards);
        Hand newHand = controller.getTable().getUser().getHand();
        assertTrue(oldHand != newHand);
    }

    @Test
    public void PotIsSplitBetweenWinners() {
        ControllerTerminal controller = new ControllerTerminal(new Table(3, 500, 20));
        controller.getTable().startNewRound();
        controller.userBet(30, 1, 20);
        for (int i = 1; i < controller.getTable().getPlayers().size(); i++) {
            Opponent opponent = (Opponent) controller.getTable().getPlayers().get(i);
            controller.getTable().addToPot(opponent.playBettingRound(controller.getTable()));
        }
        controller.userBet(100, 2, 60);
        for (int i = 1; i < controller.getTable().getPlayers().size(); i++) {
            Opponent opponent = (Opponent) controller.getTable().getPlayers().get(i);
            controller.getTable().addToPot(opponent.playBettingRound(controller.getTable()));
        }
        ArrayList<Integer> cards = new ArrayList<Integer>();
        cards.add(0);
        cards.add(3);
        cards.add(4);
        controller.replaceCards(cards);
        for (int i = 1; i < controller.getTable().getPlayers().size(); i++) {
            Opponent opponent = (Opponent) controller.getTable().getPlayers().get(i);
            opponent.playReplaceRound(controller.getTable().getDeck());
        }
        Rules rules = new Rules();
        ArrayList<Player> winners = rules.compareHands(controller.getTable().getPlayers());
        for (int i = 0; i < winners.size(); i++) {
            winners.get(i).addMoney(controller.getTable().getPot() / winners.size());
        }
        assertTrue(winners.get(0).getTotalMoney() * winners.size() == controller.getTable().getPot());
    }
}

