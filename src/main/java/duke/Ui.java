package duke;

/** Utility methods for generating stylised CLI outputs */
public class Ui {
    private static final String LINE_DIVIDER = "\n";
    private static final String LOGO = " ____\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Prints out the greeting */
    public static String greet() {
        StringBuilder s = new StringBuilder("Hello from\n" + LOGO);
        s.append("Hello! I'm Duke\nWhat can I do for you?");
        return s.toString();
    }

    /** Prints out message to indicate Task is updated */
    public static String updateTaskText(String update, Task taskToUpdate, int size) {
        return Ui.prettyPrint("Got it. I've " + update + " this task: \n"
                + "\t" + taskToUpdate + "\n"
                + "\tNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints the given string with additional wrappings
     *
     * @param string String to print
     */
    public static String prettyPrint(String string) {
        return (LINE_DIVIDER + "\t" + string + "\n" + LINE_DIVIDER);
    }

    /**
     * Prints the given array list with additional effects
     *
     * @param tasks Tasks to print
     */
    public static String prettyPrint(TaskList tasks) {
        StringBuilder s = new StringBuilder(LINE_DIVIDER + "\tHere are the tasks in your list:\n");
        for (int i = 0; i < tasks.length(); i++) {
            s.append("\t" + (i + 1) + "." + tasks.getTask(i));
            s.append("\n");
        }
        s.append(LINE_DIVIDER);
        return s.toString();
    }
}
