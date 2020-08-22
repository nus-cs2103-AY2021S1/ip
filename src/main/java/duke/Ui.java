package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);
    private final String line = "------------------------------------------------------------";;

    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println(this.line);
    }

    public String readLine() {
        return this.sc.nextLine();
    }

    public void printLine(String message) {
        System.out.println(message);
        System.out.println(this.line);
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again!");
    }

    public void printError(DukeException exception) {
        this.printLine(exception.getMessage());
    }

}
