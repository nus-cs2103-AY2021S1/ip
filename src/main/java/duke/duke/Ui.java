package duke.duke;

import java.util.Scanner;

public class Ui {

    Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showOutput(String output) {
        System.out.println(output);
    }

    public void showWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
    }

    public void showLine() {
        System.out.println("\n____________________________________________________________\n");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
