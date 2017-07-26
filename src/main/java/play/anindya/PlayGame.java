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

    public static void main(String args[]) {

        int[] diceValues = new int[NO_OF_DICE];
        System.out.println("--- Dice on a Yacht ---");
        Scanner s = new Scanner(System.in);

        GameRules game = new GameRules();

        while(true) {
            System.out.print("Enter the values on the five dice (between 1 and 8): ");
            for (int i = 0; i < NO_OF_DICE; i++) {
                diceValues[i] = s.nextInt();
            }
            if(game.setDice(diceValues))    //valid data has been passed.
                break;
            else
                System.out.println("An incorrect value was entered");
        }

        System.out.println("\n1. Ones                2. Twos              3. Threes" +
                "\n4. Fours               5. Fives             6. Sixes" +
                "\n7. Sevens              8. Eights            9. Three of a Kind" +
                "\n10. Four of a Kind    11. Full House       12. Small Straight" +
                "\n13. Large Straight    14. All Different    15. Chance" +
                "\n16. All Same          17. Best Game        18.Exit");

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
    }
}
