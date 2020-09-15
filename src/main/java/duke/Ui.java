package duke;

/**
 * UI to interact with user
 */
public class Ui {

    /**
     * Constructor.
     */
    public Ui() {

    }

    public static String sayHi() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        logo = ("Hello from\n" + logo + "\n");
        logo = logo + ("Hello! I'm Duke") + "\n";
        logo = logo + ("What can I do for you?");
        return logo;
    }

    public static void sayBye(MyString response) {
        response.addNewLines("Bye. Hope to see you again soon!");
    }

    public static void invalidCommand(MyString response) {
        response.addNewLines("Invalid command");
    }
}
