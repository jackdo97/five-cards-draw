package tests;

import model.*;
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ModelTests {
	HandEvaluater eval = new HandEvaluater();

	@Test
	public void CardValueCorrect() {
		Card c = new Card(11, Card.Suit.DIAMOND);
		boolean result = c.getValue() == 11;
		assertEquals("Card.getValue() " + c, true, result);
	}
	
	@Test
	public void CardFaceCorrect() {
		Card c = new Card(11, Card.Suit.DIAMOND);
		boolean result = c.getFace() == Card.Face.JACK;
		assertEquals("Card.getFace() " + c, true, result);
	}

	@Test
	public void CardSuitCorrect() {
		Card c = new Card(11, Card.Suit.DIAMOND);
		boolean result = c.getSuit() == Card.Suit.DIAMOND;
		assertEquals("Card.getSuit() " + c, true, result);
	}

	@Test
	public void DeckDealOneDealCard() {
		Deck deck = new Deck();
		Card c = deck.dealOne();
		boolean result = true;
		if (c.getValue() != 14) {
			result = false;
		}
		if (c.getSuit() != Card.Suit.DIAMOND) {
			result = false;
		}
		assertEquals("Deck.dealOne() " + c, true, result);
	}

	@Test
	public void DeckDealOneRemoveCard() {
		Deck deck = new Deck();
		Card c1 = deck.dealOne();
		Card c2 = deck.dealOne();
		boolean result = true;
		if (c1.getValue() == c2.getValue()&& c1.getSuit() == c2.getSuit()) {
			result = false;
		}
		assertEquals("Deck.dealOne() "+c1+" "+c2, true, result);
	}

	@Test
	public void DeckDealNewHandHandReplaceCards() {
		Deck deck = new Deck();
		Player player = new Player(100, "Player");
		deck.dealNewHand(player);
		Hand hand1 = new Hand();
		for (int i = 0; i < 5; i++) {
			hand1.getCards()[i] = player.getHand().getCards()[i];
		}
		deck.dealNewHand(player);
		boolean result = true;
		for(int i = 0; i < 5; i++) {
			if(hand1.getCards()[i].getValue() == player.getHand().getCards()[i].getValue()) {
				if(hand1.getCards()[i].getSuit() == player.getHand().getCards()[i].getSuit()) {
					result = false;
				}
			}
		}
		assertEquals("Deck.dealNewHand()&Hand.replaceCards()"+hand1.reveal()+" "+player.getHand().reveal(),true,result);
	}

	@Test
	public void DeckResetDeck() {
		Deck deck = new Deck();
		Card c1 = deck.dealOne();
		deck.resetDeck();
		Card c2 = deck.dealOne();
		boolean result = true;
		if(c1.getValue() != c2.getValue()) {
			result = false;
		}
		if(c1.getSuit() != c2.getSuit()) {
			result = false;
		}
		assertEquals("Deck.resetDeck() "+c1+" "+c2, true, result);
	}

	@Test
	public void HandReplace() {
		Deck deck = new Deck();
		Hand hand = new Hand();
		Card c1 = deck.dealOne();
		hand.replace(c1,0);
		Card c2 = deck.dealOne();
		hand.replace(c2,0);
		boolean result = true;
		if (c2.getValue() != hand.getCards()[0].getValue()&&c2.getSuit() != hand.getCards()[0].getSuit()) {
			result = false;
		}
		assertEquals("Hand.replace() "+hand.reveal(), true, result);
	}

	@Test
	public void HandEvaluaterRoyalFlush() {
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[0] = new Card(14, Card.Suit.HEART);
		newHand[1] = new Card(13, Card.Suit.HEART);
		newHand[2] = new Card(12, Card.Suit.HEART);
		newHand[3] = new Card(11, Card.Suit.HEART);
		newHand[4] = new Card(10, Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 10;
		assertEquals("HandEvaluater.getHandValue(): (Royal Flush)"+ handValue[0], true, result);
	}

	@Test
	public void HandEvaluaterSortHand() {
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[2] = new Card(14, Card.Suit.HEART);
		newHand[3] = new Card(13, Card.Suit.HEART);
		newHand[4] = new Card(12, Card.Suit.HEART);
		newHand[0] = new Card(11, Card.Suit.HEART);
		newHand[1] = new Card(10, Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 10;
		assertEquals("HandEvaluater.cardSort() " + handValue[0], true, result);
	}

	@Test
	public void HandEvaluaterStraightFlush() {
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[2] = new Card(9,Card.Suit.HEART);
		newHand[3] = new Card(13,Card.Suit.HEART);
		newHand[4] = new Card(12,Card.Suit.HEART);
		newHand[0] = new Card(11,Card.Suit.HEART);
		newHand[1] = new Card(10,Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 9;
		assertEquals("HandEvaluater.getHandValue(): (Straight Flush)" + handValue[0], true, result);
	}
	
	@Test
	public void HandEvaluaterFourOfAKind()
	{
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[2] = new Card(10,Card.Suit.DIAMOND);
		newHand[3] = new Card(10,Card.Suit.SPADE);
		newHand[4] = new Card(10,Card.Suit.CLUB);
		newHand[0] = new Card(11,Card.Suit.HEART);
		newHand[1] = new Card(10,Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 8;
		assertEquals("HandEvaluater.getHandValue(): (Four of a Kind)"+handValue[0], true, result);
	}
	
	@Test
	public void HandEvaluaterFullHouse()
	{
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[2] = new Card(9,Card.Suit.HEART);
		newHand[3] = new Card(10,Card.Suit.CLUB);
		newHand[4] = new Card(10,Card.Suit.SPADE);
		newHand[0] = new Card(9,Card.Suit.CLUB);
		newHand[1] = new Card(10,Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 7;
		assertEquals("HandEvaluater.getHandValue(): (Full House)"+handValue[0], true, result);
	}

	@Test
	public void HandEvaluaterFlush()
	{
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[2] = new Card(9,Card.Suit.HEART);
		newHand[3] = new Card(3,Card.Suit.HEART);
		newHand[4] = new Card(12,Card.Suit.HEART);
		newHand[0] = new Card(11,Card.Suit.HEART);
		newHand[1] = new Card(10,Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 6;
		assertEquals("HandEvaluater.getHandValue(): (Flush)"+handValue[0], true, result);
	}

	@Test
	public void HandEvaluaterStraight()
	{
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[2] = new Card(9,Card.Suit.HEART);
		newHand[3] = new Card(13,Card.Suit.CLUB);
		newHand[4] = new Card(12,Card.Suit.HEART);
		newHand[0] = new Card(11,Card.Suit.HEART);
		newHand[1] = new Card(10,Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 5;
		assertEquals("HandEvaluater.getHandValue(): (Straight)"+handValue[0], true, result);
	}

	@Test
	public void HandEvaluaterThreeOfAKind()
	{
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[2] = new Card(9,Card.Suit.HEART);
		newHand[3] = new Card(10,Card.Suit.CLUB);
		newHand[4] = new Card(10,Card.Suit.SPADE);
		newHand[0] = new Card(11,Card.Suit.HEART);
		newHand[1] = new Card(10,Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 4;
		assertEquals("HandEvaluater.getHandValue(): (Three of a Kind)"+handValue[0], true, result);
	}

	@Test
	public void HandEvaluaterTwoPair()
	{
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[2] = new Card(9,Card.Suit.HEART);
		newHand[3] = new Card(10,Card.Suit.CLUB);
		newHand[4] = new Card(11,Card.Suit.CLUB);
		newHand[0] = new Card(11,Card.Suit.HEART);
		newHand[1] = new Card(10,Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 3;
		assertEquals("HandEvaluater.getHandValue(): (Two Pair)"+handValue[0], true, result);
	}

	@Test
	public void HandEvaluaterPair()
	{
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[2] = new Card(9,Card.Suit.HEART);
		newHand[3] = new Card(10,Card.Suit.CLUB);
		newHand[4] = new Card(12,Card.Suit.HEART);
		newHand[0] = new Card(11,Card.Suit.HEART);
		newHand[1] = new Card(10,Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 2;
		assertEquals("HandEvaluater.getHandValue(): (Pair)"+handValue[0], true, result);
	}

	@Test
	public void HandEvaluaterHighCard()
	{
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[2] = new Card(7,Card.Suit.CLUB);
		newHand[3] = new Card(13,Card.Suit.HEART);
		newHand[4] = new Card(12,Card.Suit.HEART);
		newHand[0] = new Card(11,Card.Suit.HEART);
		newHand[1] = new Card(10,Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 1;
		assertEquals("HandEvaluater.getHandValue(): (High Card)"+handValue[0], true, result);
	}

	@Test
	public void HandEvaluaterAceLowStraight()
	{
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[2] = new Card(14,Card.Suit.CLUB);
		newHand[3] = new Card(2,Card.Suit.HEART);
		newHand[4] = new Card(3,Card.Suit.HEART);
		newHand[0] = new Card(4,Card.Suit.HEART);
		newHand[1] = new Card(5,Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 5;
		assertEquals("HandEvaluater.getHandValue(): (Ace Low Straight)"+handValue[0], true, result);
	}

	@Test
	public void HandEvaluaterAceLowStraightFlush()
	{
		Hand hand = new Hand();
		Card[] newHand = new Card[5];
		newHand[2] = new Card(14,Card.Suit.HEART);
		newHand[3] = new Card(2,Card.Suit.HEART);
		newHand[4] = new Card(3,Card.Suit.HEART);
		newHand[0] = new Card(4,Card.Suit.HEART);
		newHand[1] = new Card(5,Card.Suit.HEART);
		hand.replaceCards(newHand);
		int[] handValue = eval.getHandValue(hand);
		boolean result = handValue[0] == 9;
		assertEquals("HandEvaluater.getHandValue(): (Ace Low Straight)"+handValue[0], true, result);
	}

	@Test
	public void PlayerGetSetCurrentBet()
	{
		Player player = new Player(100, "Player 1");
		player.setCurrentBet(10);
		boolean result = player.getCurrentBet() == 10;
		assertEquals("Player.getCurrentBet() "+player.getCurrentBet(), true, result);
	}

	@Test
	public void PlayerGetName()
	{
		Player player = new Player(100, "Player 1");
		boolean result = player.getName().equals("Player 1");
		assertEquals("Player.getName() "+player.getName(), true, result);
	}

	@Test
	public void PlayerGetSetInHand()
	{
		Player player = new Player(100, "Player 1");
		player.setInHand(true);
		boolean result = player.inHand() == true;
		assertEquals("Player.inHand() "+player.inHand(), true, result);
	}

	@Test
	public void PlayerBet()
	{
		Player player = new Player(100, "Player 1");
		player.bet(10);
		boolean result = player.getTotalMoney() == 90&&player.getCurrentBet() == 10;
		assertEquals("Player.bet() "+player.getCurrentBet(), true, result);
	}

	@Test
	public void PlayerCall()
	{
		Player player = new Player(100, "Player 1");
		player.setCurrentBet(10);
		player.call(30);
		boolean result = player.getTotalMoney() == 80&&player.getCurrentBet() == 30;
		assertEquals("Player.call() "+player.getCurrentBet()+" "+player.getTotalMoney(), true, result);
	}

	@Test
	public void PlayerAddMoney()
	{
		Player player = new Player(100, "Player 1");
		player.addMoney(10);
		boolean result = player.getTotalMoney() == 110;
		assertEquals("Player.addMoney() "+player.getTotalMoney(), true, result);
	}

	@Test
	public void OpponentPlayBetRoundMinBet()
	{
		Opponent opponent = new Opponent(100, "Opponent 1");
		Table table = new Table(10);
		opponent.playBettingRound(table);
		boolean result = opponent.getCurrentBet() == 10&& opponent.getTotalMoney() == 90&& table.getMaxBet() == 10;
		assertEquals("Opponent.playBettingRound(): minBet "+opponent.getTotalMoney(), true, result);
	}

	@Test
	public void OpponentPlayBetRoundCall()
	{
		Opponent opponent = new Opponent(100, "Opponent 1");
		Table table = new Table(10);
		table.setMaxBet(20);
		opponent.playBettingRound(table);
		boolean result = opponent.getCurrentBet() == 20&& opponent.getTotalMoney() == 80;
		assertEquals("Opponent.playBettingRound(): callBet "+opponent.getTotalMoney(), true, result);
	}

	@Test
	public void OpponentPlayReplaceRound()
	{
		Opponent opponent = new Opponent(100, "Opponent 1");
		Deck deck = new Deck();
		deck.dealNewHand(opponent);
		Hand hand1 = new Hand();
		for(int i = 0; i < 5; i++)
		{
			hand1.getCards()[i] = opponent.getHand().getCards()[i];
		}
		opponent.playReplaceRound(deck);
		boolean result = true;
		for(int i = 0; i < 5; i++)
		{
			if(hand1.getCards()[i].getValue() == opponent.getHand().getCards()[i].getValue())
			{
				if(hand1.getCards()[i].getSuit() == opponent.getHand().getCards()[i].getSuit())
				{
					result = false;
				}
			}
		}
		assertEquals("Opponent.playReplaceRound(): "+opponent.getHand().reveal(), true, result);
	}

	@Test
	public void OpponentAllInAnte()
	{
		Opponent opponent = new Opponent(5, "Opponent 1");
		Table table = new Table(10);
		opponent.playBettingRound(table);
		boolean result = opponent.getTotalMoney()==0;
		assertEquals("Opponent.playBettingRound(): "+opponent.getTotalMoney(), true, result);
	}

	@Test
	public void OpponentAllInCall()
	{
		Opponent opponent = new Opponent(5, "Opponent 1");
		Table table = new Table(10);
		table.setMaxBet(20);
		opponent.playBettingRound(table);
		boolean result = opponent.getTotalMoney()==0;
		assertEquals("Opponent.playBettingRound(): "+opponent.getTotalMoney(), true, result);
	}
		
	@Test
	public void TableGetAddToPot()
	{
		Table table = new Table(10);
		table.addToPot(35);
		boolean result = table.getPot() == 35;
		assertEquals("Table.addToPot() "+table.getPot(), true, result);
	}

	@Test
	public void TableSetGetMaxBet()
	{
		Table table = new Table(10);
		table.setMaxBet(25);
		boolean result = table.getMaxBet() == 25;
		assertEquals("Table.setMaxBet() "+table.getMaxBet(), true, result);
	}

	@Test
	public void RulesDetermineWinnerBetterHand()
	{
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1 = new Player(100, "Player 1");
		Player p2 = new Player(100, "Player 2");
		Player p3 = new Player(100, "Player 3");
		Player p4 = new Player(100, "Player 4");
		Card[] newCards = new Card[]{new Card(14,Card.Suit.CLUB), new Card(13,Card.Suit.CLUB), new Card(12,Card.Suit.CLUB), new Card(11,Card.Suit.CLUB), new Card(10,Card.Suit.CLUB)};
		p1.replaceCardsInHand(newCards);
		newCards[0] = new Card(9,Card.Suit.CLUB);
		p2.replaceCardsInHand(newCards);
		p3.replaceCardsInHand(newCards);
		p4.replaceCardsInHand(newCards);
		Rules rules = new Rules();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		ArrayList<Player> winnersList = new ArrayList<Player>();
		winnersList = rules.compareHands(playerList);
		boolean result = winnersList.size() == 1&& winnersList.get(0).getName().equals("Player 1");
		assertEquals("Rules.compareHands() ", true, result);
	}

	@Test
	public void RulesDetermineWinnerFourTies()
	{
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1 = new Player(100, "Player 1");
		Player p2 = new Player(100, "Player 2");
		Player p3 = new Player(100, "Player 3");
		Player p4 = new Player(100, "Player 4");
		Card[] newCards = new Card[]{new Card(14,Card.Suit.CLUB), new Card(13,Card.Suit.CLUB), new Card(12,Card.Suit.CLUB), new Card(11,Card.Suit.CLUB), new Card(10,Card.Suit.CLUB)};
		p1.replaceCardsInHand(newCards);
		p2.replaceCardsInHand(newCards);
		p3.replaceCardsInHand(newCards);
		p4.replaceCardsInHand(newCards);
		Rules rules = new Rules();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		ArrayList<Player> winnersList = new ArrayList<Player>();
		winnersList = rules.compareHands(playerList);
		boolean result = winnersList.size() == 4;
		assertEquals("Rules.compareHands() ", true, result);
	}

	@Test
	public void RulesWinnerStraightFlush()
	{
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1 = new Player(100, "Player 1");
		Player p2 = new Player(100, "Player 2");
		Player p3 = new Player(100, "Player 3");
		Player p4 = new Player(100, "Player 4");
		Card[] newCards = new Card[]{new Card(9,Card.Suit.CLUB), new Card(13,Card.Suit.CLUB), new Card(12,Card.Suit.CLUB), new Card(11,Card.Suit.CLUB), new Card(10,Card.Suit.CLUB)};
		p1.replaceCardsInHand(newCards);
		p2.replaceCardsInHand(newCards);
		newCards[1] = new Card(8,Card.Suit.CLUB);
		p3.replaceCardsInHand(newCards);
		p4.replaceCardsInHand(new Card[]{new Card(14,Card.Suit.CLUB), new Card(2,Card.Suit.CLUB), new Card(3,Card.Suit.CLUB), new Card(4,Card.Suit.CLUB), new Card(5,Card.Suit.CLUB)});
		Rules rules = new Rules();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		ArrayList<Player> winnersList = new ArrayList<Player>();
		winnersList = rules.compareHands(playerList);
		boolean result = winnersList.size() == 2;
		assertEquals("Rules.compareHands() ", true, result);
	}

	@Test
	public void RulesWinnerFourOfAKind()	
	{
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1 = new Player(100, "Player 1");
		Player p2 = new Player(100, "Player 2");
		Player p3 = new Player(100, "Player 3");
		Player p4 = new Player(100, "Player 4");
		Card[] newCards = new Card[]{new Card(13,Card.Suit.CLUB), new Card(13,Card.Suit.SPADE), new Card(13,Card.Suit.DIAMOND), new Card(13,Card.Suit.HEART), new Card(12,Card.Suit.CLUB)};
		p1.replaceCardsInHand(newCards);
		p2.replaceCardsInHand(newCards);
		newCards = new Card[]{new Card(12,Card.Suit.CLUB), new Card(12,Card.Suit.SPADE), new Card(12,Card.Suit.DIAMOND), new Card(12,Card.Suit.HEART), new Card(14,Card.Suit.CLUB)};
		p3.replaceCardsInHand(newCards);
		p4.replaceCardsInHand(newCards);
		Rules rules = new Rules();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		ArrayList<Player> winnersList = new ArrayList<Player>();
		winnersList = rules.compareHands(playerList);
		boolean result = winnersList.size() == 2&&winnersList.get(0).getName().equals("Player 1");
		assertEquals("Rules.compareHands() "+winnersList.size()+" "+winnersList.get(0).getName(), true, result);
	}

	@Test
	public void RulesWinnerFullHouse()
	{
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1 = new Player(100, "Player 1");
		Player p2 = new Player(100, "Player 2");
		Player p3 = new Player(100, "Player 3");
		Player p4 = new Player(100, "Player 4");
		Card[] newCards = new Card[]{new Card(13,Card.Suit.CLUB), new Card(13,Card.Suit.SPADE), new Card(13,Card.Suit.DIAMOND), new Card(12,Card.Suit.HEART), new Card(12,Card.Suit.CLUB)};
		p2.replaceCardsInHand(newCards);
		newCards[0] = new Card(14,Card.Suit.CLUB);
		newCards[1] = new Card(14,Card.Suit.HEART);
		newCards[2] = new Card(12,Card.Suit.DIAMOND);
		p1.replaceCardsInHand(newCards);
		newCards = new Card[]{new Card(12,Card.Suit.CLUB), new Card(12,Card.Suit.SPADE), new Card(14,Card.Suit.DIAMOND), new Card(13,Card.Suit.HEART), new Card(14,Card.Suit.CLUB)};
		p3.replaceCardsInHand(newCards);
		p4.replaceCardsInHand(newCards);
		Rules rules = new Rules();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		ArrayList<Player> winnersList = new ArrayList<Player>();
		winnersList = rules.compareHands(playerList);
		boolean result = winnersList.size() == 1&&winnersList.get(0).getName().equals("Player 2");
		assertEquals("Rules.compareHands() "+winnersList.size()+" "+winnersList.get(0).getName(), true, result);
	}

	@Test
	public void RulesWinnerFlush()
	{
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1 = new Player(100, "Player 1");
		Player p2 = new Player(100, "Player 2");
		Player p3 = new Player(100, "Player 3");
		Player p4 = new Player(100, "Player 4");
		Card[] newCards = new Card[]{new Card(10,Card.Suit.CLUB), new Card(7,Card.Suit.CLUB), new Card(13,Card.Suit.CLUB), new Card(11,Card.Suit.CLUB), new Card(12,Card.Suit.CLUB)};
		p1.replaceCardsInHand(newCards);
		newCards[1] = new Card(9,Card.Suit.HEART);
		p2.replaceCardsInHand(newCards);
		newCards[0] = new Card(14,Card.Suit.CLUB);
		newCards[1] = new Card(2,Card.Suit.CLUB);
		p3.replaceCardsInHand(newCards);
		newCards[1] = new Card(3,Card.Suit.CLUB);
		p4.replaceCardsInHand(newCards);
		Rules rules = new Rules();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		ArrayList<Player> winnersList = new ArrayList<Player>();
		winnersList = rules.compareHands(playerList);
		boolean result = winnersList.size() == 1&&winnersList.get(0).getName().equals("Player 4");
		assertEquals("Rules.compareHands() "+winnersList.size()+" "+winnersList.get(0).getName(), true, result);
	}

	@Test
	public void RulesWinnerStraight()
	{
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1 = new Player(100, "Player 1");
		Player p2 = new Player(100, "Player 2");
		Player p3 = new Player(100, "Player 3");
		Player p4 = new Player(100, "Player 4");
		Card[] newCards = new Card[]{new Card(10,Card.Suit.HEART), new Card(9,Card.Suit.CLUB), new Card(13,Card.Suit.CLUB), new Card(11,Card.Suit.CLUB), new Card(12,Card.Suit.CLUB)};
		p1.replaceCardsInHand(newCards);
		newCards[2] = new Card(8,Card.Suit.HEART);
		p2.replaceCardsInHand(newCards);
		p3.replaceCardsInHand(newCards);
		newCards[0] = new Card(14,Card.Suit.CLUB);
		newCards[1] = new Card(2,Card.Suit.HEART);
		newCards[2] = new Card(3,Card.Suit.DIAMOND);
		newCards[3] = new Card(4,Card.Suit.SPADE);
		newCards[4] = new Card(5,Card.Suit.CLUB);
		p4.replaceCardsInHand(newCards);
		Rules rules = new Rules();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		ArrayList<Player> winnersList = new ArrayList<Player>();
		winnersList = rules.compareHands(playerList);
		boolean result = winnersList.size() == 1&&winnersList.get(0).getName().equals("Player 1");
		assertEquals("Rules.compareHands() "+winnersList.size()+" "+winnersList.get(0).getName(), true, result);
	}

	@Test
	public void RulesWinnerThreeOfAKind()
	{
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1 = new Player(100, "Player 1");
		Player p2 = new Player(100, "Player 2");
		Player p3 = new Player(100, "Player 3");
		Player p4 = new Player(100, "Player 4");
		Card[] newCards = new Card[]{new Card(10,Card.Suit.CLUB), new Card(10,Card.Suit.CLUB), new Card(10,Card.Suit.CLUB), new Card(11,Card.Suit.SPADE), new Card(12,Card.Suit.CLUB)};
		p1.replaceCardsInHand(newCards);
		newCards[0] = new Card(11,Card.Suit.HEART);
		newCards[1] = new Card(11,Card.Suit.CLUB);
		newCards[2] = new Card(2,Card.Suit.CLUB);
		newCards[4] = new Card(3,Card.Suit.DIAMOND);
		p2.replaceCardsInHand(newCards);
		p3.replaceCardsInHand(newCards);
		p4.replaceCardsInHand(newCards);
		Rules rules = new Rules();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		ArrayList<Player> winnersList = new ArrayList<Player>();
		winnersList = rules.compareHands(playerList);
		boolean result = winnersList.size() == 3;
		assertEquals("Rules.compareHands() "+winnersList.size()+" "+winnersList.get(0).getName(), true, result);
	}

	@Test
	public void RulesWinnerTwoPair()
	{
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1 = new Player(100, "Player 1");
		Player p2 = new Player(100, "Player 2");
		Player p3 = new Player(100, "Player 3");
		Player p4 = new Player(100, "Player 4");
		Card[] newCards = new Card[]{new Card(10,Card.Suit.CLUB), new Card(10,Card.Suit.DIAMOND), new Card(11,Card.Suit.CLUB), new Card(11,Card.Suit.CLUB), new Card(9,Card.Suit.CLUB)};
		p1.replaceCardsInHand(newCards);
		newCards[4] = new Card(8,Card.Suit.HEART);
		p2.replaceCardsInHand(newCards);
		newCards[3] = new Card(8,Card.Suit.CLUB);
		newCards[2] = new Card(14,Card.Suit.DIAMOND);
		p3.replaceCardsInHand(newCards);
		p4.replaceCardsInHand(newCards);
		Rules rules = new Rules();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		ArrayList<Player> winnersList = new ArrayList<Player>();
		winnersList = rules.compareHands(playerList);
		boolean result = winnersList.size() == 1&&winnersList.get(0).getName().equals("Player 1");
		assertEquals("Rules.compareHands() "+winnersList.size()+" "+winnersList.get(0).getName(), true, result);
	}

	@Test
	public void RulesWinnerPair()
	{
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1 = new Player(100, "Player 1");
		Player p2 = new Player(100, "Player 2");
		Player p3 = new Player(100, "Player 3");
		Player p4 = new Player(100, "Player 4");
		Card[] newCards = new Card[]{new Card(10,Card.Suit.CLUB), new Card(10,Card.Suit.SPADE), new Card(9,Card.Suit.CLUB), new Card(11,Card.Suit.CLUB), new Card(8,Card.Suit.CLUB)};
		p1.replaceCardsInHand(newCards);
		newCards[2] = new Card(7,Card.Suit.HEART);
		p2.replaceCardsInHand(newCards);
		newCards[1] = new Card(14,Card.Suit.CLUB);
		newCards[3] = new Card(9,Card.Suit.SPADE);
		p3.replaceCardsInHand(newCards);
		p4.replaceCardsInHand(newCards);
		Rules rules = new Rules();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		ArrayList<Player> winnersList = new ArrayList<Player>();
		winnersList = rules.compareHands(playerList);
		boolean result = winnersList.size() == 1&&winnersList.get(0).getName().equals("Player 1");
		assertEquals("Rules.compareHands() "+winnersList.size()+" "+winnersList.get(0).getName(), true, result);
	}

	@Test
	public void RulesWinnerHighCard()
	{
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1 = new Player(100, "Player 1");
		Player p2 = new Player(100, "Player 2");
		Player p3 = new Player(100, "Player 3");
		Player p4 = new Player(100, "Player 4");
		Card[] newCards = new Card[]{new Card(14,Card.Suit.CLUB), new Card(12,Card.Suit.SPADE), new Card(8,Card.Suit.CLUB), new Card(3,Card.Suit.CLUB), new Card(2,Card.Suit.SPADE)};
		p1.replaceCardsInHand(newCards);
		newCards[4] = new Card(4,Card.Suit.HEART);
		p2.replaceCardsInHand(newCards);
		newCards[3] = new Card(2,Card.Suit.DIAMOND);
		p3.replaceCardsInHand(newCards);
		p4.replaceCardsInHand(newCards);
		Rules rules = new Rules();
		playerList.add(p1);
		playerList.add(p2);
		playerList.add(p3);
		playerList.add(p4);
		ArrayList<Player> winnersList = new ArrayList<Player>();
		winnersList = rules.compareHands(playerList);
		boolean result = winnersList.size() == 1&&winnersList.get(0).getName().equals("Player 2");
		assertEquals("Rules.compareHands() "+winnersList.size()+" "+winnersList.get(0).getName(), true, result);
	}	

	@Test
	public void FileIOManagerSaveGame()
	{
		boolean result = FileIOManager.saveGame(null,null,0,"SaveGameFile");
		assertEquals("FileIOManager.saveGame(): returns True",true, result);
		result = new File("SaveGameFile").exists();
		assertEquals("FileIOManager.saveGame(): File Exists",true, result);
	}

	@Test
	public void FileIOManagerLoadGameNoFile()
	{
		boolean result = null == FileIOManager.loadGame("a");
		assertEquals("FileIOManager.loadGame(): File not Found",true, result);
	}

	@Test
	public void FileIOManagerLoadGame()
	{
		ArrayList<Opponent> opponentList = new ArrayList<Opponent>();
		opponentList.add(new Opponent(100, "Opp"));
		Player user = new Player(51, "Play");
		int ante = 3;
		FileIOManager.saveGame(opponentList,user,ante,"SaveGameFile");
		SaveGameModel save = FileIOManager.loadGame("SaveGameFile");
		Opponent opp = save.getOpponentList().get(0);
		boolean result = ante == save.getAnteAmount()&&save.getUser().getName().equals("Play")&&save.getUser().getTotalMoney()==51&&opp.getName().equals("Opp")&&opp.getTotalMoney()==100;
		assertEquals("FileIOManager.loadGame()",true, result);
	}
}
