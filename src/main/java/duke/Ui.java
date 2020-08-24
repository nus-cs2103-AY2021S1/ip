package duke;

/**
 * Represents the user interface that outputs information to the user.
 */
public class Ui {

    public Ui() {

    }

    public void showLoadingError() {
        System.out.println("The test file cannot be loaded properly.");
    }

    /**
     * Introduces the bot.
     */
    public void introduce() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
