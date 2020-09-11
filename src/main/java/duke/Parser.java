package duke;

/** Utility class to parse strings */
public class Parser {
    /**
     * Returns split string
     *
     * @param toSplit String to split
     * @param split token to split string
     * @return String array of split string
     */
    public static String[] stringSplit(String toSplit, String split) {
        assert toSplit.length() > 0;
        return toSplit.split(split);
    }

    /**
     * Returns split string to predetermined limit
     *
     * @param toSplit string to split
     * @param split token to split string
     * @param limit limit the number of split strings
     * @return String array of split string
     */
    public static String[] stringSplitLimit(String toSplit, String split, int limit) {
        assert limit > 0 : "Spliting string using negative slices";
        assert limit < toSplit.length() : "Splitting string into more slices than length of string";
        return toSplit.split(split, limit);
    }

    /**
     * Returns command of the string for Duke
     *
     * @param line line of user input
     * @return first word of string
     */
    public static String getCommand(String line) {
        assert line.length() > 0;
        return stringSplitLimit(line, " ", 2)[0];
    }

    /**
     * Returns argument to pass to command in duke
     *
     * @param line line of user input
     * @return sentence after the first word
     */
    public static String getDetails(String line) {
        assert line.length() > 0;
        String[] splitString = stringSplitLimit(line, " ", 2);
        return splitString.length < 2 ? "" : splitString[1];
    }

    /**
     * Returns the argument as an integer
     *
     * @param line line of user input
     * @return integer form of argument
     */
    public static int getIndex(String line) {
        assert line.length() > 0;
        return Integer.parseInt(getDetails(line)) - 1;
    }
}
