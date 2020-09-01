package duck.ui;

import java.util.List;

/**
 * Simple console UI that simply prints responses with a line
 * and indentation.
 */
public class ConsoleUi implements Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String INDENT = "    ";


    public void printLine() {
        System.out.println(INDENT + LINE);
    }

    @Override
    public void respond(List<String> messages) {
        printLine();
        for (String s : messages) {
            System.out.print(INDENT);
            System.out.println(s);
        }
        printLine();
    }
}
