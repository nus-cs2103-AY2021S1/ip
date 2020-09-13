package duke.util;

import static java.lang.Integer.parseInt;

/**
 * Class that checks is the user's input is an integer.
 */
public class IntegerChecker {

    /**
     * Check if the user's string input is a number.
     *
     * @param string the inputted user's index for tasks.
     * @return boolean value of true if user's string input is a number, false otherwise.
     */
    public static boolean isNumber(String string) {
        try {
            int number = parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
