package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private final Scanner sc;
    private final static String ERRORBORDER = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    private final static String BORDER = "===================================================";
    private final static String INDENT = "    ";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    public void printGreeting() {
        ArrayList<String> welcomeTextBlock = new ArrayList<>(List.of(
                "Hello, my name is ",
                " ____        _        ",
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "How may I help you?"
        ));

        printWithWrapper(welcomeTextBlock, false, false);
    }

    public void printExit() {
        printWithWrapper(new ArrayList<>(List.of("Bye bye! Hope to see you again soon!")), false, false);
    }

    private void printBorder(boolean isError) {
        System.out.println((isError ? ERRORBORDER : BORDER));
    }

    public <T> void printWithWrapper(ArrayList<T> toPrint, boolean withNumbering, boolean isError) {
        printBorder(isError);
        for (int i = 0; i < toPrint.size(); i++) {
            if (toPrint.get(i) == null) {
                break;
            }

            String prepend = INDENT;
            if (withNumbering) {
                prepend += (i + 1) + ". ";
            }
            System.out.println(prepend + toPrint.get(i).toString());
        }
        printBorder(isError);
    }
}
