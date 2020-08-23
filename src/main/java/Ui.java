public class Ui {
    private static final String LINEDIVIDER = "\t____________________________________________________________\n";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // Prints out the greeting
    public static void greet() {
        System.out.println("Hello from\n" + LOGO);
        Ui.prettyPrint("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    public static void updateTaskText(String update, Task taskToUpdate, int size) {
        Ui.prettyPrint("Got it. I've " + update + " this task: \n" +
                "\t" + taskToUpdate + "\n" +
                "\tNow you have " + size +" tasks in the list.");
    }

    /**
     * Prints the given string with additional wrappings
     *
     * @param string String to print
     */
    public static void prettyPrint(String string) {
        System.out.println(LINEDIVIDER + "\t" + string + "\n" + LINEDIVIDER);
    }

    /**
     * Prints the given array list with additional effects
     *
     * @param tasks Tasks to print
     */
    public static void prettyPrint(TaskList tasks) {
        System.out.println(LINEDIVIDER + "\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.length(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(LINEDIVIDER);
    }
}