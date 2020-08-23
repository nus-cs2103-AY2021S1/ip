package duke.ui;

import duke.task.*;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

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

    public void showStartMessage() {
        System.out.println(startLine + "Hello! I'm\n" + logo +
        startLine + "What can I do for you?\n" + endLine);

        showCommands();
    }

    public void showCommands() {
        System.out.println(startLine +
                        "Here are a list of commands you can use:\n" +
                        "1. list (shows all currently listed tasks)\n" +
                        "2. bye (exit the program)\n" +
                        "3. done <task number> (mark the task with <task number> " +
                "as complete)\n" +
                        "4. delete <task number> (delete this task with " +
                "<task number> from list)\n" +
                        "5. check <yyyy-mm-dd> (check tasks that are due or " +
                "occurring on <yyyy-mm-dd>)\n" +
                        "6. todo <description> (adds todo task to list with " +
                "<description>)\n" +
                        "7. deadline <description> /by <time> (adds deadline to list with " +
                "<description> and due by <time>)\n" +
                        "8. event <description> /at <time> (adds event to list with " +
                "<description> and occurs at <time>)\n" +
                endLine);
    }

    public void printEndLine() {
        System.out.println(endLine);
    }

    public void showTask(String taskString) {
        System.out.println(taskString);
    }

    public void showCurrentTasks(ArrayList<Task> tasks) {
        System.out.println(startLine + "Here are the task(s) in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println( (i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println(endLine);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printDoneStatement(String taskString) {
        System.out.println(startLine + "Nice! I've marked this task as done:\n" +
                taskString + "\n" + endLine);
    }

    public void printDeleteStatement(String taskString, int size) {
        System.out.println(startLine + "The following task has been deleted:\n" +
                taskString+ "\n" + "Now you have " + size + " task(s) in the list.\n" + endLine);
    }

    public void printCheckStatement(LocalDate date) {
        System.out.println(startLine + "You have the following" +
                " tasks on " + date + ":");
    }

    public void printAddStatements(String taskString, int size) {
        System.out.println(startLine + "Got it. I've added this task:\n" +
                taskString + "\n" + "Now you have " + size + " task(s) in the list.\n"
                + endLine);
    }

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

    public void sayBye() {
        System.out.println(startLine + "Bye. Hope to see you again soon!\n" +
                endLine);
    }

}
