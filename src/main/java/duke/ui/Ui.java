package duke.ui;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Encapsulates a UI system which is responsible for printing the responses that the
 * user can interact with. It also reads and passes commands to the Duke class.
 */
public class Ui {

    /**
     * Shows a message when program starts to run.
     */
    public String showStartMessage() {
        return "Hello! I'm Duke!\n"
                + "What can I do for you?\n"
                + "\n" + showCommands();
    }

    /**
     * Prints the available list of commands.
     */
    public String showCommands() {
        return "Here are a list of commands you can use:\n"
                + "1. list (shows all currently listed tasks)\n"
                + "2. bye (exit the program)\n"
                + "3. done <task number> (mark the task with <task number> as complete)\n"
                + "4. delete <task number> (delete this task with <task number> from list)\n"
                + "5. check <yyyy-mm-dd> (check tasks that are due or occurring on <yyyy-mm-dd>)\n"
                + "6. todo <description> (adds todo task to list with <description>)\n"
                + "7. deadline <description> /by <time> (adds deadline to list with "
                + "<description> and due by <time>)\n"
                + "8. event <description> /at <time> (adds event to list with "
                + "<description> and occurs at <time>)\n"
                + "9. find <word> (list tasks that contain <word> in description)";
    }

    public String showTask(String taskString) {
        return taskString + "\n";
    }

    /**
     * Prints the list of current tasks.
     * @param tasks The list of tasks.
     */
    public String showCurrentTasks(ArrayList<Task> tasks) {
        String start = "Here are the task(s) in your list:\n";
        String append = "";

        for (int i = 0; i < tasks.size(); i++) {
            append += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return start + append;
    }

    /**
     * Prints a confirmation message that a task is done.
     * @param taskString The toString result of the task.
     */
    public String printDoneStatement(String taskString) {
        return "Nice! I've marked this task as done:\n"
                + taskString;
    }

    /**
     * Prints a confirmation message for a deletion of a task.
     * @param taskString The toString result of the task.
     * @param size The size of the task list.
     */
    public String printDeleteStatement(String taskString, int size) {
        return "The following task has been deleted:\n"
                + taskString + "\n" + "Now you have " + size + " task(s) in the list.";
    }

    /**
     * Prints the result of checking tasks with the given date.
     * @param date The given date.
     */
    public String printCheckStatement(LocalDate date) {
        return "You have the following"
                + " tasks on " + date + ":\n";
    }

    public String printFindStatement() {
        return "Here are the matching tasks in your list:\n";
    }

    /**
     * Prints a confirmation that a task has been added.
     * @param taskString The toString result of the task.
     * @param size The number of tasks.
     */
    public String printAddStatements(String taskString, int size) {
        return "Got it. I've added this task:\n"
                + taskString + "\n" + "Now you have " + size + " task(s) in the list.\n";
    }

    /**
     * Prints a date loading error.
     */
    public String loadDateError() {
        return "Please enter the date in this format: yyyy-mm-dd\n";
    }

    public String loadFileError() {
        return "File cannot be found. Creating new file now.\n";
    }

    public String printExceptions(Exception ex) {
        return ex.getMessage() + "\n";
    }

    /**
     * Prints ending comment to user.
     */
    public String sayBye() {
        return "Bye. Hope to see you again soon!\n";
    }

}
