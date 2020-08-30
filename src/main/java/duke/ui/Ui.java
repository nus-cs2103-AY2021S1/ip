/**
 * This class contains functions related to the
 * processing of Duke's response to be sent to the
 * GUI
 */

package duke.ui;

import java.util.Arrays;
import java.util.List;

public class Ui {
    // String constants
    private static final String INDENT = "\t";
    private static final String LINE_BREAK = "_".repeat(70);
    private static final String WELCOME_MESSAGE = "Hello! I'm Orang. "
            + "What can I do for you?";

    /**
     * Returns the Greet and prompt message upon startup
     *
     * @return the welcome message
     */
    public String greeting() {
        return output(WELCOME_MESSAGE);
    }

    /**
     * Processes a list of strings and returns a string
     * with each item in the list on a new line
     *
     * @param strings the list of strings to be processed
     * @return the processed string
     */
    public String output(List<String> strings) {
        String res = "";
        for (String s : strings) {
            res += s + "\n";
        }
        return res;
    }

    /**
     * Does the same as above, except with multiple
     * String arguments (an array of Strings)
     *
     * @param strings the array of strings to be processed
     * @return the processed string
     */
    public String output(String ...strings) {
        return output(Arrays.asList(strings));
    }
}
