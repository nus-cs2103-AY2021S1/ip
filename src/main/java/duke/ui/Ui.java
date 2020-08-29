package duke.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Encapsulates a UI system which is responsible for printing the responses that the
 * user can interact with. It also reads and passes commands to the Duke class.
 */
public class Ui {

    private String startLine = "---------------\n";
    private String endLine = "---------------";
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows a message when program starts to run.
     */
    public void showStartMessage() {
        System.out.println(startLine + "Hello! I'm\n" + logo
                + startLine + "What can I do for you?\n" + endLine);

        showCommands();
    }

    /**
     * Prints the available list of commands.
     */
    public void showCommands() {
        System.out.println(startLine
                + "Here are a list of commands you can use:\n"
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
                + "9. find <word> (list tasks that contain <word> in description)\n"
                + endLine);
    }

    public void printEndLine() {
        System.out.println(endLine);
    }

    public void showTask(String taskString) {
        System.out.println(taskString);
    }

    /**
     * Prints the list of current tasks.
     * @param tasks The list of tasks.
     */
    public void showCurrentTasks(ArrayList<Task> tasks) {
        System.out.println(startLine + "Here are the task(s) in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println(endLine);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a confirmation message that a task is done.
     * @param taskString The toString result of the task.
     */
    public void printDoneStatement(String taskString) {
        System.out.println(startLine + "Nice! I've marked this task as done:\n"
                + taskString + "\n" + endLine);
    }

    /**
     * Prints a confirmation message for a deletion of a task.
     * @param taskString The toString result of the task.
     * @param size The size of the task list.
     */
    public void printDeleteStatement(String taskString, int size) {
        System.out.println(startLine + "The following task has been deleted:\n"
                + taskString + "\n" + "Now you have " + size + " task(s) in the list.\n" + endLine);
    }

    /**
     * Prints the result of checking tasks with the given date.
     * @param date The given date.
     */
    public void printCheckStatement(LocalDate date) {
        System.out.println(startLine + "You have the following"
                + " tasks on " + date + ":");
    }

    public void printFindStatement() {
        System.out.println(startLine + "Here are the matching tasks in your list:\n");
    }

    /**
     * Prints a confirmation that a task has been added.
     * @param taskString The toString result of the task.
     * @param size The number of tasks.
     */
    public void printAddStatements(String taskString, int size) {
        System.out.println(startLine + "Got it. I've added this task:\n"
                + taskString + "\n" + "Now you have " + size + " task(s) in the list.\n"
                + endLine);
    }

    /**
     * Prints a date loading error.
     */
    public void loadDateError() {
        System.out.println(startLine + "Please enter the date in this format: yyyy-mm-dd\n"
                + endLine);
    }

    public void loadFileError() {
        System.out.println(startLine + "File cannot be found. Creating new file now.\n" + endLine);
    }

    public void printExceptions(Exception ex) {
        System.out.println(startLine + ex.getMessage() + "\n" + endLine);
    }

    /**
     * Prints ending comment to user.
     */
    public void sayBye() {
        System.out.println(startLine + "Bye. Hope to see you again soon!\n"
                + endLine);
    }

}
