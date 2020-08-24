package duke.ui;

import java.util.ArrayList;
import java.util.List;

public class Ui {

    public void say(String string) {
        final String indent = "  ";
        final int lineLength = 68;
        final String separator = indent + "_".repeat(lineLength);
        List<String> strings = splitIntoLines(string, lineLength);
        System.out.println(separator);
        for (String s : strings) {
            System.out.println(indent + s);
        }
        System.out.println(separator);
        System.out.println();
    }

    /*
     * Splits a string into lines with at most lineLength number of characters. This method does not
     * account for characters of differing widths (eg. tab character, or if non-monospace fonts are
     * used).
     *
     * TODO should this method be private instead?
     *
     * @param string the string to split
     * @param lineLength the maximum number of characters per line
     * @return a list of strings where each string corresponds to 1 line
     */
    public List<String> splitIntoLines(String string, int lineLength) {
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
