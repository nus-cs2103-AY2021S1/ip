package duke.ui;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Encapsulates a UI system which is responsible for printing the responses that the
 * user can interact with. It also reads and passes commands to the Duke class.
 */
public class Ui {

    private String startLine = "=====================\n";
    private String endLine = "=====================";

    /**
     * Shows a message when program starts to run.
     * @return The starting message.
     */
    public String showStartMessage() {
        return startLine + "Hello! I'm Duke!\n"
                + "What can I do for you?\n"
                + "\n" + showCommands();
    }

    /**
     * Prints the available list of commands.
     * @return The list of commands.
     */
    public String showCommands() {
        return "Here are a list of commands you can use "
                + "(You can type the letter labelled next to any of the following commands "
                + "as a shortcut, instead of their full name):\n"
                + "1. (l) list (shows all currently listed tasks)\n"
                + "2. (b) bye (exit the program)\n"
                + "3. (o) done <task number> (mark the task with <task number> as complete)\n"
                + "4. (x) delete <task number> (delete this task with <task number> from list)\n"
                + "5. (c) check <yyyy-mm-dd> (check tasks that are due or occurring on <yyyy-mm-dd>)\n"
                + "6. (t) todo <description> (adds todo task to list with <description>)\n"
                + "7. (d) deadline <description> /by <time> (adds deadline to list with "
                + "<description> and due by <time>)\n"
                + "8. (e) event <description> /at <time> (adds event to list with "
                + "<description> and occurs at <time>)\n"
                + "9. (f) find <word> (list tasks that contain <word> in description)\n"
                + "10. (h) help (show this instruction list again)\n"
                + endLine;
    }

    public String showTask(String taskString) {
        return taskString + "\n";
    }

    /**
     * Prints the list of current tasks.
     * @param tasks The list of tasks.
     * @return The string representation of the task list.
     */
    public String showCurrentTasks(ArrayList<Task> tasks) {
        String start = startLine + "Here are the task(s) in your list:\n";
        String append = "";

        for (int i = 0; i < tasks.size(); i++) {
            append += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return start + append + endLine;
    }

    public String printEndLine() {
        return endLine;
    }

    /**
     * Prints a confirmation message that a task is done.
     * @param taskString The toString result of the task.
     * @return A confirmation message.
     */
    public String printDoneStatement(String taskString) {
        return startLine + "Nice! I've marked this task as done:\n"
                + taskString + "\n" + endLine;
    }

    /**
     * Prints a confirmation message for a deletion of a task.
     * @param taskString The toString result of the task.
     * @param size The size of the task list.
     * @return A confirmation message.
     */
    public String printDeleteStatement(String taskString, int size) {
        return startLine + "The following task has been deleted:\n"
                + taskString + "\n" + "Now you have " + size + " task(s) in the list.\n"
                + endLine;
    }

    /**
     * Prints the result of checking tasks with the given date.
     * @param date The given date.
     * @return A statement.
     */
    public String printCheckStatement(LocalDate date) {
        return startLine + "You have the following"
                + " tasks on " + date + ":\n";
    }

    public String printFindStatement() {
        return startLine + "Here are the matching tasks in your list:\n";
    }

    /**
     * Prints a confirmation that a task has been added.
     * @param taskString The toString result of the task.
     * @param size The number of tasks.
     * @return A confirmation message
     */
    public String printAddStatements(String taskString, int size) {
        return startLine + "Got it. I've added this task:\n"
                + taskString + "\n" + "Now you have " + size + " task(s) in the list.\n"
                + endLine;
    }

    /**
     * Prints a date loading error.
     * @return An error message.
     */
    public String loadDateError() {
        return startLine + "Please enter the date in this format: yyyy-mm-dd\n"
                + endLine;
    }

    /**
     * Prints a file load error.
     * @return An error message.
     */
    public String loadFileError() {
        return startLine + "File cannot be found. Creating new file now.\n"
                + endLine;
    }

    public String printExceptions(Exception ex) {
        return startLine + ex.getMessage() + "\n" + endLine;
    }

    /**
     * Prints ending comment to user.
     * @return A farewell message.
     */
    public String showEndMessage() {
        return startLine + "Bye. Hope to see you again soon!\n"
            + endLine;
    }

}
