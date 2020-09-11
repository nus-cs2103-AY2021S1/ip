package duke.util;

import static java.lang.Integer.parseInt;

public class IntegerChecker {

    /**
     * Checking if the user's string input is a number.
     *
     * @param str the inputted user's index for tasks.
     * @return boolean value of true if user's string input is a number, false otherwise.
     */
    public static boolean isNumber(String str) {
        try {
            int num = parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
