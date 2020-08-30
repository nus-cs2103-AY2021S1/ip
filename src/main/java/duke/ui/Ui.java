package duke.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for displaying Duke's responses on the screen.
 */
public class Ui {

    /**
     * Prints the given string to the console as a response from Duke, wrapping lines that exceed 68
     * characters in length.
     *
     * @param string the string to print
     */
    public void say(String string) {
        final String INDENT = "  ";
        final int LINE_LENGTH = 68;
        final String SEPARATOR = INDENT + "_".repeat(LINE_LENGTH);
        List<String> strings = splitIntoLines(string, LINE_LENGTH);
        System.out.println(SEPARATOR);
        for (String s : strings) {
            System.out.println(INDENT + s);
        }
        System.out.println(SEPARATOR);
        System.out.println();
    }

    /**
     * Splits a string into lines with at most lineLength number of characters. This method does not
     * account for characters of differing widths (eg. tab character, or if non-monospace fonts are
     * used).
     *
     * @param string the string to split
     * @param lineLength the maximum number of characters per line
     * @return a list of strings where each string corresponds to 1 line
     */
    List<String> splitIntoLines(String string, int lineLength) {
        // TODO: should this method be private instead?
        List<String> list = new ArrayList<>();
        String[] lines = string.split("\n");
        StringBuilder sb;

        for (String l : lines) {
            String[] words = l.split(" ");
            sb = new StringBuilder();
            for (String w : words) {
                if (sb.length() == 0 || sb.length() + w.length() <= lineLength) {
                    sb.append(w);
                    sb.append(" ");
                } else {
                    list.add(sb.toString().stripTrailing());
                    sb = new StringBuilder();
                    sb.append(w);
                    sb.append(" ");
                }
            }
            if (l.equals("") || sb.length() > 0) {
                list.add(sb.toString().stripTrailing());
            }
        }

        return list;
    }
}
