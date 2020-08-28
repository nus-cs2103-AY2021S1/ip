package duke.Ui;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    public Scanner scanner;

    public static final String LINE_SEPARATOR = System.lineSeparator();

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a new line.
     */
    public void showLine() {
        System.out.print(LINE_SEPARATOR);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void printResponse(String string) {
        System.out.print(string);
    }
}
