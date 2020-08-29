package duke.ui;

import java.util.Scanner;

import duke.task.Task;

public class Ui {

    public Ui() { }

    /**
     * Prints a welcome message.
     */
    public void showWelcome() {
        String logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.print("Hello, I am\n" + logo);
        System.out.println("___________________________________________________"
                + "\nDuke: What can i do for you?");
    }

    /**
     * Prints a goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a success message after task is added successfully.
     *
     * @param taskAdded task added to TaskList
     */
    public static void printAddSuccess(Task taskAdded) {
        System.out.println("Got it. I've added this task:"
                + "\n\t" + taskAdded);
    }

    /**
     * Reads commands input from user.
     *
     * @return command as a input string
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("___________________________________________________");
    }

}
