package duke.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import duke.Duke;

/**
 * A command line user interface for Duke.
 */
public class Cli implements Ui {
    private Consumer<String> inputHandler;
    private boolean shouldStop;

    /**
     * Creates a new Cli.
     */
    public Cli() {
        inputHandler = (String input) -> {};
        shouldStop = false;
        new Duke(this);
        run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        while (!shouldStop) {
            String input = scanner.nextLine();
            inputHandler.accept(input);
        }
        scanner.close();
    }

    @Override
    public void setInputHandler(Consumer<String> function) {
        inputHandler = function;
    }

    @Override
    public void say(String string, boolean isError) {
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

    @Override
    public void stop() {
        shouldStop = true;
    }

    /**
     * Splits a string into lines with at most lineLength number of characters. This method does not account for
     * characters of differing widths (eg. tab character, or if non-monospace fonts are used).
     *
     * @param string     the string to split.
     * @param lineLength the maximum number of characters per line.
     * @return a list of strings where each string corresponds to 1 line.
     */
    static List<String> splitIntoLines(String string, int lineLength) {
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
