package duke;

import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        String logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello ! I'm Duke.\nWhat can I do for you?");
        System.out.println(menu());
    }

    private String menu() {
        return "1. list\n"
                + "2. done {item number}\n"
                + "3. todo {description}\n"
                + "4. deadline {description} /by {dd/mm/yyyy} {hhmm}\n"
                + "\t e.g. deadline return book /by 1/12/2020 1800\n"
                + "5. event {description} /at {dd/mm/yyyy} {hhmm}-{hhmm}\n"
                + "\t e.g. event meeting /at 1/12/2020 1800-1900\n"
                + "7. delete {item number}\n"
                + "6. bye\n";
    }

    public void showLoadingError() {
        System.err.println("Error: Fail to load file!");
    }

    public String readCommand() {
        System.out.println("Please enter your command(not the number but the full command):\n");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(
                "_____________________________________________________________________________");
    }

    public void showError(String error) {
        System.err.println("Error: " + error);
    }

    public void printString(String statement) {
        System.out.println(statement);
    }
}
