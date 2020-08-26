package duke;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {

    private static String BORDER = "-----------------------------------------------------------";
    private static String INDENTATION = "    ";

    /**
     * Prints the formatting for the Duke application.
     */
    public void printBorder() {
        System.out.println(INDENTATION + BORDER);
    }

    /**
     * Greets the user when Duke starts up.
     */
    public void greet() {
        printBorder();
        System.out.println(INDENTATION + "Hello! I'm Duke\n    What can I do for you?");
        printBorder();
    }

    /**
     * Says goodbye to the user before terminating.
     */
    public void exit() {
        printBorder();
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        printBorder();
    }

    /**
     * Displays a list of all the tasks.
     *
     * @param taskList a TaskList object containing a list of tasks
     */
    public void list(TaskList taskList) {
        printBorder();
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (Task task : taskList.getTasks()) {
            int index = taskList.getTasks().indexOf(task) + 1;
            System.out.println(INDENTATION + index + "." + task);
        }
        printBorder();
    }

    /**
     * Mark a task as done.
     *
     * @param taskList a TaskList object containing a list of tasks
     * @param index position of the task in the list of tasks to be marked done
     */
    public void markDone(TaskList taskList, int index) {
        Task oldTask = taskList.getTasks().get(index);
        Task newTask = oldTask.markAsDone();
        taskList.replace(oldTask, newTask);

        printBorder();
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + INDENTATION + newTask.getStatusIcon() + " " + newTask.description);
        printBorder();
    }
}
