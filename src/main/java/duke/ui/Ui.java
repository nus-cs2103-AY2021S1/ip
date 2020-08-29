package duke.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a given message.
     *
     * @param message The message to be printed.
     */
    public void print(String message) {
        String horizontalLine = "____________________________________________________________";
        System.out.printf("%s\n%s\n%s\n", horizontalLine, message, horizontalLine);
    }

    /**
     * Prints an error telling users that something went wrong loading the storage and that the
     * program will initialise with an empty tasks list instead.
     */
    public void showLoadingError() {
        this.print("Something went wrong when loading previously saved tasks! Starting with an "
                + "empty tasks list instead...");
    }

    public void showError(String message) {
        this.print(message);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }
}
