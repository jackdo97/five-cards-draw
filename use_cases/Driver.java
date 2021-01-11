public class Driver {
    public static void main(String[] args) {
        // Use case 1
        System.out.println("WELCOME TO POKER - 5 CARD DRAW");
        System.out.println("START GAME OR LOAD GAME?");
        Scanner scanner = new Scanner(System.in);
        String gameSetting = scanner.nextLine();
        if (gameSetting.equalsIgnoreCase("start game")) {
            startNewGame(scanner);
        } else if (gameSetting.equalsIgnoreCase("load game")) {
            loadOldGame();
        }

        // Use case 3
        System.out.println("Do you want to play again? Y N");
        if (scanner.nextLine.equalsIgnoreCase("Y")) {
            startNewGame(scanner);
        }
    }

    public static void startNewGame(Scanner scanner) {
        System.out.println("Select number of opponents");
        int numOpponent = scanner.nextInt();

        System.out.println("Select starting money");
        int startingMoney = scanner.nextInt();

        System.out.println("Select ante amount");
        int anteAmount = scanner.nextInt();

        // Use case 2
        gamePlay(numOpponent, startingMoney, anteAmount);
    }

    public static void loadOldGame() {

    }

    public static void gamePlay(int numOpponent, int startingMoney, int anteAmount) {
        Pot pot = new Pot();

        Player player = new Player();
        player.setTotalMoney(startingMoney);

        ArrayList<Opponent> opponents = new ArrayList<Opponent>();
        for (int i = 0; i < numOpponent; i++) {
            opponents.add(new Opponent());
            opponents.get(i).setTotalMoney(startingMoney);
        }

        Deck deck = new Deck();
        deck.shuffle();

        for (int i = 0; i < 5; i++) {
            deck.dealCard(player);
        }

        for (int i = 0; i < opponents.size(); i++) {
            for (int i = 0; i < 5; i++) {
                deck.dealCard(opponents.get(i));
            }
            opponents.get(i).getHand().hide();
        }

        bettingRound(player, opponents, pot, anteAmount, scanner);
        drawPhase(player, opponents, deck);
        bettingRound(player, opponents, pot, anteAmount, scanner);
        gameOver(player, opponents, pot);
    }

    public static void bettingRound(Player player, ArrayList<Opponent> opponents,
                                    Pot pot, int anteAmount, Scanner scanner) {;
        String userPlay = "";
	boolean validOption = true;;

        while (validOption) {
            System.out.println("RAISE, CHECK, FOLD OR CALL");
            userPlay = scanner.nextLine();

            if (userPlay.equalsIgnoreCase("RAISE")) {
                userRaise(player);
		validOption = false;
            } else if (userPlay.equalsIgnoreCase("CHECK")) {
                userCheck(player);
		validOption = false;
            } else if (userPlay.equalsIgnoreCase("CALL")) {
                userCall(player);
		validOption = false;
            } else if (userPlay.equalsIgnoreCase("FOLD")) {
                userFold(player);
		validOption = false;
            } 
        }

        for (int i = 0; i < opponents.size(); i++) {
            opponents.get(i).playBetRound();
        }
    }

    public static void drawPhase(Player player, ArrayList<Opponent> opponents, Deck deck) {
       player.replaceCards(); 

        for (int i = 0; i < opponents.size(); i++) {
            opponents.get(i).playReplaceRound(deck);
        }
    }

    public static void userRaise(Player player) {
	player.raise();
    }
   
    public static void userCheck(Player player) {
	player.check();
    }

    public static void userCall(Player player) {
        player.call();
    }

    public static void userFold(Player player) {
        player.fold();
    }

    public static void gameOver(Player player, ArrayList<Opponent> opponents, Pot pot) {
        System.out.println("GAME OVER");
        System.out.println("Your hand value: " + player.getHand().getValue());
        for (int i = 0; i < opponents.size(); i++) {
            System.out.println("Opponent " + i + "'s hand value: " + opponents.get(i).getValue());
        }

        ArrayList<Hand> hands = new ArrayList<Hand>();
        hands.add(player.getHand());
        for (int i = 0; i < opponents.size(); i++) {
            hands.add(opponents.get(i).getHand());
        }
        Result result = Rules.compareHands(hands);

        // Announce winner
        // Let the player knows if they won or lost
        // Update the winner's amount of money
    }
}
