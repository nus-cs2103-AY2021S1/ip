package duke;

import java.util.Scanner;

/**
 * A class to deal with all the interaction with the user.
 */
public class Ui {

    /**
     * A function to print welcome message.
     */
    public void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * A function to print goodbye message.
     */
    public void goodbyeMessage() {
        System.out.println("GoodBye, Hope to see you back soon.");
    }

    /**
     * A function to read command.
     * @return String which is the command read.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("*****************************************************************");
    }
    public void showSpace() {
        System.out.println();
    }

    public void showError(Exception e) {
        System.out.print(e);
    }
}
