package play.anindya;

import java.util.Scanner;

import static play.anindya.Dice.*;

/**
 * This is the main class that is used to run the game.
 *
 * @author Anindya
 * @version 1.0
 * @since 7/24/2017.
 */
public class PlayGame {

    static String[] options = {"", "Ones", "Twos", "Threes", "Fours", "Fives", "Sixes", "Sevens",
            "Eights", "Three of a Kind", "Four of a Kind", "Full House", "Small Straight", "Large Straight",
            "All Different", "Chance", "All Same", "Best Game", "Exit"};

    public static void main(String args[]) {

        int[] diceValues = new int[NO_OF_DICE];
        System.out.println("--- Dice on a Yacht ---");
        Scanner s = new Scanner(System.in);

        GameRules game = new GameRules();

        while (true) {
            System.out.print("Enter the values on the five dice (between 1 and 8): ");
            for (int i = 0; i < NO_OF_DICE; i++) {
                diceValues[i] = s.nextInt();
            }
            if (game.setDice(diceValues))    //valid data has been passed.
                break;
            else
                System.out.println("An incorrect value was entered");
        }

        for (int i = 1; i < options.length; i++) {
            System.out.println(i + ". " + options[i]);
        }

        while (true) {
            System.out.print("Enter the number for the game you want to play: ");
            int type = s.nextInt();
            if (type == 18)
                break;
            if (type < 1 || type > 17) {
                System.out.print("Enter a valid number: ");
            }
            int score = game.playGame(type);
            System.out.println(score);
        }

        s.close();
    }
}
