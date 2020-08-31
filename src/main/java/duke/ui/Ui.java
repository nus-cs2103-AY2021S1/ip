package duke.ui;

import java.util.Arrays;
import java.util.List;

public class Ui {
    /**
     * Prints the greeting message and prompt when Duke is launched.
     */
    public String greet() {
        return "Hello! I'm Duke. What can I do for you?";
    }

    public String showLoadingError() {
        return "Oops! I wasn't able to load past tasks properly :(";
    }

    /**
     * Prints the list of strings, each item separated by a newline
     *
     * @param strings a list of strings to be displayed on separate lines
     */
    public String format(List<String> strings) {
        String res = "";
        for(String s: strings) {
            res += s;
        }
        return res;
    }

    /**
     * Prints the array of strings, each item separated by a newline
     *
     * @param strings an array of strings to be displayed on separate lines
     */
    public String format(String ...strings) {
        return format(Arrays.asList(strings));
    }
}
