package duke;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {

    private static String border = "-----------------------------------------------------------";
    private static String indentation = "    ";

    /**
     * Prints the formatting for the Duke application.
     */
    public void printBorder() {
        System.out.println(indentation + border);
    }

    /**
     * Greets the user when Duke starts up.
     */
    public void greet() {
        printBorder();
        System.out.println(indentation + "Hello! I'm Duke\n    What can I do for you?");
        printBorder();
    }

    /**
     * Says goodbye to the user before terminating.
     */
    public void exit() {
        printBorder();
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        printBorder();
    }

    /**
     * Displays a list of all the tasks.
     *
     * @param taskList a TaskList object containing a list of tasks
     */
    public void list(TaskList taskList) {
        printBorder();
        System.out.println(indentation + "Here are the tasks in your list:");
        for (Task task : taskList.getTasks()) {
            int index = taskList.getTasks().indexOf(task) + 1;
            System.out.println(indentation + index + "." + task);
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
        System.out.println(indentation + "Nice! I've marked this task as done:");
        System.out.println(indentation + indentation + newTask.getStatusIcon() + " " + newTask.description);
        printBorder();
    }

    /**
     * find all tasks containing search keyword(s)
     *
     * @param taskList a TaskList object containing a list of tasks
     * @param textToMatch the text to be used to find matching tasks
     */
    public void findMatching(TaskList taskList, String textToMatch) {
        printBorder();
        System.out.println(indentation + "Here are the matching tasks in your list:");
        int index = 1;
        for (Task task : taskList.getTasks()) {
            String description = task.getDescription();
            if (description.contains(textToMatch)) {
                System.out.println(indentation + index + "." + task);
                index++;
            }
        }
        printBorder();
    }
}
