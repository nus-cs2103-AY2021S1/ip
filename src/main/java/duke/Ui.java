package duke;

/**
 * UI class displays information to answer clients' queries.
 */
public class Ui {
    /**
     * Shows welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ______________________________________");
        System.out.println("        Hello! I'm Duke");
        System.out.println("        What can I do for you?");
        System.out.println("    ______________________________________");
    }

    /**
     * Shows goodbye message.
     */
    public String sayGoodbye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Shows invalid format command error message.
     */
    public String showInvalidFormatCommandDescription() {
        return "☹ OOPS!!! The description of a task cannot be empty or in wrong format\n";
    }

    /**
     * Shows meaningless command message.
     */
    public String showMeaninglessCommandDescription() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }

    /**
     * Prints out line background.
     */
    public String printLineBackground() {
        return "    _______________________________________________________________________________\n";
    }
}
