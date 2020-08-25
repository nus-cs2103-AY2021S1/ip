package duke.ui;

import java.util.Arrays;
import java.util.List;

public class Ui {
    /**
     * Prints the greeting message and prompt when Duke is launched.
     */
    public void greet() {
        print("Hello! I'm duke.Duke", "What can I do for you?");
    }

    public void showLoadingError() {
        print("Oops! I wasn't able to load past tasks properly :(");
    }

    /**
     * Prints the list of strings, each item separated by a newline
     *
     * @param strings a list of strings to be displayed on separate lines
     */
    public void print(List<String> strings) {
        final String INDENT = "\t";
        final String SEPARATOR = "_".repeat(69);

        System.out.println(INDENT + SEPARATOR);
        for(String s: strings) {
            System.out.println(INDENT + INDENT + s);
        }
        System.out.println(INDENT + SEPARATOR + "\n");
    }

    /**
     * Prints the array of strings, each item separated by a newline
     *
     * @param strings an array of strings to be displayed on separate lines
     */
    public void print(String ...strings) {
        print(Arrays.asList(strings));
    }
}
