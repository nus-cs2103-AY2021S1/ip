/**
 * This class contains functions related to the
 * displaying
 */

package duke.ui;

import java.util.Arrays;
import java.util.List;

public class Ui {
    // String constants
    private static final String INDENT = "\t";
    private static final String LINE_BREAK = "_".repeat(70);
    private static final String WELCOME_MESSAGE = "Hello! I'm Tusk. " +
            "What can I do for you?";

    /**
     * Greet and prompt the user upon startup
     */
    public void greeting() {
        display(WELCOME_MESSAGE);
    }

    /**
     * Prints a list of strings to console with each item on a new line,
     * in a decorated format
     *
     * @param strings the list of strings to be displayed
     */
    public void display(List<String> strings) {
        System.out.println(LINE_BREAK);
        for (String s : strings) {
            System.out.println(INDENT + s);
        }
        System.out.println(LINE_BREAK + "\n");
    }

    /**
     * Does the same as above, except with multiple
     * String arguments (an array of Strings)
     *
     * @param strings the array of strings to be displayed
     */
    public void display(String ...strings) {
        display(Arrays.asList(strings));
    }
}
