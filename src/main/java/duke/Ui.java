package duke;

/**
 * interact with user
 */
public class Ui {

    public Ui() {

    }

    /**
     * greet user
     */
    public String sayHi() {
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

    /**
     * bye user
     */
    public String sayBye() {
        return ("Bye. Hope to see you again soon!");
    }
}
