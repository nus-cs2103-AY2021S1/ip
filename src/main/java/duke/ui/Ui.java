package duke.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    private static final String DIVIDER = "---------------------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showBlankLine() {
        System.out.println();
    }

    public void showStartMessage() {
        String message = "Greetings, what may I do for you?";
        System.out.println(LOGO + message);
    }

    public void showDivider() {
        System.out.println(DIVIDER);
    }

    public void showStatus(String msg) {
        System.out.println(msg);
    }

    public String readUserInput() {
        return this.scanner.nextLine();
    }
}
