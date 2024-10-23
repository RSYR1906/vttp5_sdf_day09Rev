package practicePM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackTask11 {

    static List<String> decks = new ArrayList<>();

    public static void main(String[] args) {

        String[] suits = { "SPADE", "HEART", "CLUB", "DIAMOND" };
        String[] ranks = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J",
                "Q", "K" };

        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                decks.add(ranks[j] + " " + suits[i]);
            }
        }

        Collections.shuffle(decks);

        // System.out.println(decks.size());

        // for (String card : decks) {
        // System.out.println(card);
        // }

        // Function to draw cards from the deck
        List<String> playerDrawnCards = new ArrayList<>();
        List<String> bankerDrawnCards = new ArrayList<>();

        // deal two cards to player and banker, remove the dealt cards from the deck
        drawCards(playerDrawnCards, 2);
        drawCards(bankerDrawnCards, 2);

        // compare the sum of the cards between player and banker (larger hand wins)
        int playerPoints = calculatePoints(playerDrawnCards);
        int bankerPoints = calculatePoints(bankerDrawnCards);

        if (playerPoints == 21) {
            System.out.println("BLACKJACK! Player wins!");
            return;
        }

        if (bankerPoints == 21) {
            System.out.println("BLACKJACK! Banker wins!");
            return;
        }

        while (playerPoints < 16 && playerDrawnCards.size() < 5) {
            drawCards(playerDrawnCards, 1);
            playerPoints = calculatePoints(playerDrawnCards);

        }

        while (bankerPoints < 16 && bankerDrawnCards.size() < 5) {
            drawCards(bankerDrawnCards, 1);
            bankerPoints = calculatePoints(bankerDrawnCards);
        }

        System.out.println(playerDrawnCards);
        System.out.println(bankerDrawnCards);

        if (playerPoints > 21) {
            System.out.println("Banker wins!");
        }
        if (bankerPoints > 21) {
            System.out.println("Player wins!");
        } else if (playerPoints > bankerPoints) {
            System.out.println("Player wins!");
        } else if (bankerPoints > playerPoints) {
            System.out.println("Banker wins!");
        } else if (playerPoints == bankerPoints) {
            System.out.println("Draw!");
        } else {
            System.out.println("Error!");
        }

        // set rules:
        // 1. exceed 21, lose
        // 2. blackjack straight win (A pair with 10s (10,J,Q,K))
        // 3. max 5 cards
        // 4. if first two cards less than 16, must draw one card

        // show winner
    }

    public static int calculatePoints(List<String> hand) {
        int points = 0;

        for (String card : hand) {
            String[] parts = card.split(" ");
            String rank = parts[0];

            if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) {
                points += 10;
            } else if (rank.equals("A") && hand.size() < 3) {
                points += 11;
            } else if (rank.equals("A") && hand.size() > 2) {
                points += 1;
            } else {
                points += Integer.parseInt(rank);
            }
        }
        return points;
    }

    public static List<String> drawCards(List<String> emptyHand, int numberOfTimes) {
        for (int i = 0; i < numberOfTimes; i++) {
            emptyHand.add(decks.remove(0)); // Remove the top card from the deck
        }
        return emptyHand;
    }
}
