package duke.frontend;

/**
 * Deals with basic interaction with the user.
 * Contains methods that greets user on start and exit of program.
 */
public class Ui {
    private static final String STARLINE = "**************************************************************************";
    private static final String LOGO = " ____        _   \n" 
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    /**
     * Prints welcome greeting to user at start of program.
     */
    public static void greet() {
        System.out.println(STARLINE
                + "\nWelcome! I am\n" + LOGO
                + "\nHere are some magic words to get you going:"
                + "\nTo add a todo, say 'todo <task description>'."
                + "\nTo add a deadline, say 'deadline <task description> /by <yyyy-mm-dd>'."
                + "\nTo add an event, say 'event <event description> /at <event location>'"
                + "\nTo view your tasks, say 'list'."
                + "\nTo check off a task, say 'done <task number>'."
                + "\nTo leave, say 'bye'.\n"
                + STARLINE);
    }

    /**
     * Prints farewell greeting to user at end of program.
     */
    public static void exit() {
        System.out.println( " _____  ___  ___  _____\n"
                + "|  __ \\ \\  \\/  / |  ___|\n"
                + "| | / /  \\    /  |  |__\n"
                + "| | \\ \\   |  |   |  ___|\n"
                + "| |_/ /   |  |   |  |__\n"
                + "|____/    |__|   |_____|\n"
        );
        System.out.println("Hope you have a productive day ahead! :))");
    }
}
