package play.anindya;

import java.util.stream.IntStream;

/**
 * The Dice class implements a set of dice.
 *
 * @author Anindya
 * @version 1.0
 * @since 7/24/2017.
 */
class Dice {
    private int[] mValues;
    private int mSum;

    static final int SIDES = 8;
    static final int NO_OF_DICE = 5;

    Dice(int[] values) {
        mValues = values;
        mSum = IntStream.of(values).sum();
    }

    /**
     * @return the array of dice.
     */
    int[] getValues() {
        return mValues;
    }

    /**
     * @return the sum of all the dice.
     */
    int getSum() {
        return mSum;
    }
}
