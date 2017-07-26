package play.anindya;

import java.util.Arrays;

import static play.anindya.Dice.*;

/**
 * GameRules implements all the different types of scoring categories.
 *
 * @author Anindya
 * @version 1.0
 * @since 7/24/2017.
 */

class GameRules {

    static final int ONES = 1;
    static final int TWOS = 2;
    static final int THREES = 3;
    static final int FOURS = 4;
    static final int FIVES = 5;
    static final int SIXES = 6;
    static final int SEVENS = 7;
    static final int EIGHTS = 8;

    /* Other categories */
    static final int THREE_OF_A_KIND = 9;
    static final int FOUR_OF_A_KIND = 10;
    static final int FULL_HOUSE = 11;
    static final int SMALL_STRAIGHT = 12;
    static final int LARGE_STRAIGHT = 13;
    static final int ALL_DIFFERENT = 14;
    static final int CHANCE = 15;
    static final int ALL_SAME = 16;
    static final int BEST = 17;

    /* Scores */
    static final int FULL_HOUSE_SCORE = 25;
    static final int SMALL_STRAIGHT_SCORE = 30;
    static final int LARGE_STRAIGHT_SCORE = 40;
    static final int ALL_DIFFERENT_SCORE = 40;
    static final int ALL_SAME_SCORE = 50;
    static final int LOSS = 0;

    private Dice mDice;
    private int[] mCount;

    /**
     * Empty constructor. Values are set in setDice().
     */
    GameRules() {}

    /**
     * Create the dice and value counters.
     *
     * @param diceValues An array of dice values.
     * @return true if all the dice have valid data, false otherwise.
     */
    public boolean setDice(int[] diceValues) {
        for (int i : diceValues) {
            if (i < 1 || i > 8)
                return false;
        }

        mDice = new Dice(diceValues);
        mCount = new int[Dice.SIDES + 1]; //because 8-sided dice.

        for (int val : mDice.getValues()) {
            mCount[val]++;
        }

        return true;
    }

    /**
     * Returns the sum of all dice that match the number.
     *
     * @param number the number whose sum we need to find.
     * @return the sum of the occurrences of the number.
     */
    private int sumOfNumber(int number) {
        return number * mCount[number];
    }

    /**
     * Finds if there are three-of-a-kind and four-of-a-kind.
     *
     * @param value 3 or 4, depending on if it is three-of-a-kind or four-of-a-kind.
     * @return sum of all dice if there are at least 3 or 4 dice that are the same, or zero.
     */
    private int ofAKind(int value) {
        for (int i : mCount) {
            if (i >= value)
                return mDice.getSum();
        }
        return LOSS;
    }

    /**
     * Finds if a full-house occurred.
     *
     * @return 25, if there are three of one kind and two of another, 0 otherwise.
     */
    private int fullHouse() {
        boolean three = false;
        boolean two = false;

        for (int i : mCount) {
            if (i == 3)
                three = true;
            else if (i == 2)
                two = true;
            if (three && two)
                break;
        }

        return three && two ? FULL_HOUSE_SCORE : LOSS;
    }

    /**
     * Finds small-straights and large-straights
     *
     * @param type small-straight, or large-straight
     * @return if small-straight and there are four dice in sequence, 30 else zero.
     * if large-straight and there are five dice in sequence, 40 else zero.
     */
    private int straights(int type) {
        int length = 1;
        int[] values = mDice.getValues();
        for (int i = 1; i < NO_OF_DICE; i++) {
            if (values[i] - values[i - 1] == 1) {
                length += 1;
            } else {
                if (type == SMALL_STRAIGHT && length == 4)
                    break;
                length = 1;
            }
        }
        return type == SMALL_STRAIGHT && length >= 4 ? SMALL_STRAIGHT_SCORE :
                type == LARGE_STRAIGHT && length == 5 ? LARGE_STRAIGHT_SCORE : LOSS;
    }

    /**
     * Finds if an all-different occurred
     *
     * @return 40 if all dice have unique values, otherwise zero.
     */
    private int allDifferent() {
        for (int i : mCount) {
            if (i > 1)
                return LOSS;
        }
        return ALL_DIFFERENT_SCORE;
    }

    /**
     * Finds the chance.
     *
     * @return sum of all dice.
     */
    private int chance() {
        return mDice.getSum();
    }

    /**
     * Finds if all of the dice are same.
     *
     * @return 50 if all the dice have the same value, else 0.
     */
    private int allSame() {
        for (int i : mCount) {
            if (i == NO_OF_DICE)
                return ALL_SAME_SCORE;
        }
        return LOSS;
    }

    /**
     * Iterates through all categories and returns the one with the highest score.
     *
     * @return the highest score.
     */
    private int best() {
        int[] allGames = {ONES, TWOS, THREES, FOURS, FIVES, SIXES, SEVENS, EIGHTS, THREE_OF_A_KIND, FOUR_OF_A_KIND,
                FULL_HOUSE, SMALL_STRAIGHT, LARGE_STRAIGHT, ALL_DIFFERENT, CHANCE, ALL_SAME};

        return Arrays.stream(allGames).map(this::playGame).max().getAsInt();
    }

    /**
     * Play the game.
     *
     * @param type type of game that user wants to play.
     * @return score in that game.
     */
    int playGame(int type) {
        switch (type) {
            case ONES:
            case TWOS:
            case THREES:
            case FOURS:
            case FIVES:
            case SIXES:
            case SEVENS:
            case EIGHTS:
                return sumOfNumber(type);

            case THREE_OF_A_KIND:
                return ofAKind(THREES);
            case FOUR_OF_A_KIND:
                return ofAKind(FOURS);
            case FULL_HOUSE:
                return fullHouse();
            case SMALL_STRAIGHT:
                return straights(SMALL_STRAIGHT);
            case LARGE_STRAIGHT:
                return straights(LARGE_STRAIGHT);

            case ALL_DIFFERENT:
                return allDifferent();
            case CHANCE:
                return chance();
            case ALL_SAME:
                return allSame();
            case BEST:
                return best();
            default:
                return LOSS;
        }
    }
}
