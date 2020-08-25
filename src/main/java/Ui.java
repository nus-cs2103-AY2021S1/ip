/**
 * Handles interactions with the user.
 */
public class Ui {

    public Ui() {

    }

    /**
     * Prints Duke's introduction message.
     */
    public void printIntroduction() {
        String divider = "____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String welcome = "Hello! I'm Duke.\n";
        System.out.println(divider + logo + welcome + divider);
    }

    /**
     * Prints message.
     *
     * @param message Message.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a warning message.
     *
     * @param warningMessage Warning message.
     */
    public void printWarning(String warningMessage) {
        System.out.println("WARNING: " + warningMessage);
    }

    /**
     * Prints an error message.
     *
     * @param errorMessage Error message.
     */
    public void printError(String errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }

    /**
     * Prints the entire list of tasks.
     *
     * @param taskList Task list.
     */
    public void printTaskList(TaskList taskList) {
        int numOfTasks = taskList.getNumOfTasks();
        if (numOfTasks == 0) {
            System.out.println("There are no tasks in your list.\n");
        } else {
            if (numOfTasks == 1) {
                System.out.println("This is the only task in your list:");
            } else {
                System.out.println("Here are the " + numOfTasks + " tasks in your list:");
            }

            for (int i = 0; i < numOfTasks; i++) {
                int number = i + 1;
                System.out.println(number + ". " + taskList.getTask(i).toString());
            }
            System.out.println();
        }
    }
}
