package duke;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {

    private static final String BORDER = "-----------------------------------------------------------";
    private static final String INDENTATION = "    ";

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
        System.out.println(INDENTATION + "Hello! I'm Mushy\n    What can I do for you?");
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
        assert taskList.getTasks().size() >= 0;
        if (taskList.getTasks().size() == 0) {
            System.out.println(INDENTATION + "You have no tasks in your list!");
        } else {
            System.out.println(INDENTATION + "Here are the tasks in your list:");
            for (Task task : taskList.getTasks()) {
                int index = taskList.getTasks().indexOf(task) + 1;
                System.out.println(INDENTATION + index + "." + task);
            }
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
        assert taskList.getTasks() != null;
        Task oldTask = taskList.getTasks().get(index);
        Task newTask = oldTask.markAsDone();
        taskList.replace(oldTask, newTask);

        printBorder();
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + INDENTATION + newTask.getStatusIcon() + " " + newTask.description);
        printBorder();
    }

    /**
     * Find all tasks containing search keyword(s).
     *
     * @param taskList a TaskList object containing a list of tasks
     * @param textToMatch the text to be used to find matching tasks
     */
    public void findMatching(TaskList taskList, String textToMatch) {
        printBorder();
        boolean canFind = false;
        String output = (INDENTATION + "Here are the matching tasks in your list:\n");
        int index = 1;
        for (Task task : taskList.getTasks()) {
            String description = task.getDescription();
            if (description.contains(textToMatch)) {
                output += (INDENTATION + index + "." + task + "\n");
                index++;
                canFind = true;
            }
        }
        if (!canFind) {
            output = (INDENTATION + "There are no matching tasks in your list!");
        }
        System.out.println(output);
        printBorder();
    }

    /**
     * Displays help menu.
     */
    public void help() {
        String output = " Command │       Description        │                   Usage\n"
                + "─────────┼──────────────────────────┼───────────────────────────────────────────\n"
                + "bye      │ exits the program        │ bye\n"
                + "list     │ list all current tasks   │ list\n"
                + "done     │ marks a task done        │ done [task number]\n"
                + "delete   │ deletes a task           │ delete [task number]\n"
                + "find     │ finds all matching tasks │ find [text to match]\n"
                + "help     │ displays help menu       │ help\n"
                + "todo     │ adds a todo task         │ todo [task name]\n"
                + "deadline │ adds a deadline task     │ deadline [task name] /by [dd/MM/yyyy HHmm]\n"
                + "event    │ adds an event task       │ event [task name] /at [duration]";
        System.out.println(output);
    }
}
