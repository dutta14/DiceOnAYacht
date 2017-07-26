package play.anindya;

import org.junit.Test;

import static org.junit.Assert.*;
import static play.anindya.GameRules.*;

/**
 * Different test-cases for all possible scoring categories.
 *
 * @author Anindya
 * @version 1.0
 * @since 7/25/2017.
 */

public class GameRulesTest {

    private GameRules game = new GameRules();

    @Test
    public void testNumbers() {
        int[] arr = {1, 2, 3, 4, 1};
        game.setDice(arr);
        assertEquals(2, game.playGame(ONES));
        assertEquals(2, game.playGame(TWOS));
        assertEquals(3, game.playGame(THREES));
        assertEquals(4, game.playGame(FOURS));
    }

    @Test
    public void testThreeOfAKind_Positive() {
        int[] arr = {1, 2, 1, 4, 1};
        game.setDice(arr);
        assertEquals(9, game.playGame(THREE_OF_A_KIND));
    }

    @Test
    public void testThreeOfAKind_Negative() {
        int[] arr = {1, 2, 1, 4, 5};
        game.setDice(arr);
        assertEquals(LOSS, game.playGame(THREE_OF_A_KIND));
    }

    @Test
    public void testFourOfAKind_Positive() {
        int[] arr = {1, 1, 1, 4, 1};
        game.setDice(arr);
        assertEquals(8, game.playGame(FOUR_OF_A_KIND));
    }

    @Test
    public void testFourOfAKind_Negative() {
        int[] arr = {1, 2, 1, 4, 5};
        game.setDice(arr);
        assertEquals(LOSS, game.playGame(FOUR_OF_A_KIND));
    }

    @Test
    public void testFullHouse_Positive() {
        int[] arr = {2, 2, 2, 3, 3};
        game.setDice(arr);
        assertEquals(FULL_HOUSE_SCORE, game.playGame(FULL_HOUSE));
    }

    @Test
    public void testFullHouse_Negative() {
        int[] arr = {2, 2, 2, 3, 4};
        game.setDice(arr);
        assertEquals(LOSS, game.playGame(FULL_HOUSE));
    }

    @Test
    public void testSmallStraight_Positive() {
        int[] arr = {1, 2, 3, 4, 1};
        game.setDice(arr);
        assertEquals(SMALL_STRAIGHT_SCORE, game.playGame(SMALL_STRAIGHT));
    }

    @Test
    public void testSmallStraight_Negative() {
        int[] arr = {1, 2, 7, 4, 1};
        game.setDice(arr);
        assertNotEquals(SMALL_STRAIGHT_SCORE, game.playGame(SMALL_STRAIGHT));
    }

    @Test
    public void testLargeStraight_Positive() {
        int[] arr = {1, 2, 3, 4, 5};
        game.setDice(arr);
        assertEquals(LARGE_STRAIGHT_SCORE, game.playGame(LARGE_STRAIGHT));
    }

    @Test
    public void testLargeStraight_Negative() {
        int[] arr = {1, 2, 3, 4, 8};
        game.setDice(arr);
        assertNotEquals(LARGE_STRAIGHT_SCORE, game.playGame(LARGE_STRAIGHT));
        assertEquals(LOSS, game.playGame(LARGE_STRAIGHT));
    }

    @Test
    public void testAllDifferent_Positive() {
        int[] arr = {1, 3, 5, 4, 7};
        game.setDice(arr);
        assertEquals(ALL_DIFFERENT_SCORE, game.playGame(ALL_DIFFERENT));
    }

    @Test
    public void testAllDifferent_Negative() {
        int[] arr = {1, 3, 5, 4, 3};
        game.setDice(arr);
        assertEquals(LOSS, game.playGame(ALL_DIFFERENT));
    }

    @Test
    public void testChance() {
        int[] arr = {7, 4, 6, 8, 5};
        game.setDice(arr);
        assertEquals(30, game.playGame(CHANCE));
    }

    @Test
    public void testAllSame_Positive() {
        int[] arr = {1, 1, 1, 1, 1};
        game.setDice(arr);
        assertEquals(ALL_SAME_SCORE, game.playGame(ALL_SAME));
    }

    @Test
    public void testAllSame_Negative() {
        int[] arr = {7, 4, 6, 8, 5};
        game.setDice(arr);
        assertEquals(LOSS, game.playGame(ALL_SAME));
    }
}
