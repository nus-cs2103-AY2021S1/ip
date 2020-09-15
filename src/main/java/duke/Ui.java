package duke;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * A class to deal with the interactions with users.
 */
public class Ui {
    public String showingString;

    protected String logo;
    protected String line;
    protected Scanner sc;

    /**
     * Default constructor of Ui.
     */
    public Ui() {
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.line = "____________________________________________________________\n";
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome.
     */
    public void showWelcome() {
        String finalString = " Hi, I am Duke\n"
                + " Nice to see you. What can I do for you?\n"
                + " If you are first time here, type \"help\" or \"h\" for help.\n";
        System.out.println(line + finalString + line);
        showingString = finalString;
    }

    /**
     * Reads the full command from user's input.
     *
     * @return String of the next input line.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the error message of a DukeException.
     *
     * @param eMsg the error message of the DukeException.
     */
    public void showError(String eMsg) {
        System.out.println(line
                + eMsg + "\n"
                + line);
        showingString = eMsg + "\n";
    }

    private void printLine() {
        System.out.print(line);
    }

    private String convertListToString(List<Task> taskList) {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            listString.append(" ").append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        return listString.toString();
    }

    /**
     * Prints out the list of tasks.
     *
     * @param taskList the list of tasks.
     */
    public void printList(List<Task> taskList) {
        if (taskList.size() == 0) {
            printLine();
            System.out.println(" Oops, no task YET. Try to add one!");
            printLine();
            showingString = " Oops, no task YET. Try to add one!";
            return;
        }
        StringBuffer finalString = new StringBuffer();
        printLine();
        finalString.append(" Here are the tasks in your list:\n");
        finalString.append(convertListToString(taskList));
        System.out.println(finalString);
        printLine();
        showingString = finalString.toString();
    }

    /**
     * Prints out the Done command executed on a certain task.
     *
     * @param task the task to be done.
     */
    public void printDone(Task task) {
        String finalString = " Nice! I've marked this task as done: "
                + "\n   " + task + "\n";
        System.out.println(line
                + finalString
                + line);
        showingString = finalString;
    }

    /**
     * Prints out the Delete command executed on a certain task.
     *
     * @param task the task to be deleted.
     * @param size the size of the task list after deletion.
     */
    public void printDelete(Task task, int size) {
        String finalString = " Noted. I've removed this task: "
                + "\n   " + task
                + "\n Now you have " + size + " tasks in the list.\n";
        System.out.println(line
                + finalString
                + line);
        showingString = finalString;
    }

    /**
     * Prints out the Add command.
     *
     * @param task the task to be added.
     * @param size the size of the task list after addition.
     */
    public void printAdd(Task task, int size) {
        String finalString = " Got it. I've added this task: \n"
                + "   " + task
                + "\n Now you have " + size + " tasks in the list.\n";
        System.out.println(line
                + finalString
                + line);
        showingString = finalString;
    }

    /**
     * Prints out the list of tasks which was found by find command.
     *
     * @param findResult the list of tasks which was found.
     */
    public void printFind(List<Task> findResult) {
        if (findResult.size() == 0) {
            printLine();
            System.out.println(" No task founded. Try to add one!");
            printLine();
            showingString = " No task founded. Try to add one!";
            return;
        }
        StringBuffer finalString = new StringBuffer();
        printLine();
        finalString.append(" Here are the matching tasks in your list:\n");
        finalString.append(convertListToString(findResult));
        System.out.println(finalString);
        printLine();
        showingString = finalString.toString();
    }

    /**
     * Says good bye to user.
     */
    public void bye() {
        String finalString = " Bye. Hope to see you again soon!\n";
        System.out.println(line + finalString + line);
        showingString = finalString;
    }

    /**
     * Give user help.
     */
    public void help() {
        String finalString = " Greeting. Here are some command you can use to chat with me:\n"
                + "  1. todo (your task description)\n"
                + "  (to add a todo task)\n"
                + "  2. list\n"
                + "  (list all the tasks)\n"
                + "  3. done (index)\n"
                + "  (mark done a certain task)\n"
                + " For more help. Please refer to the product website.\n";
        System.out.println(line + finalString + line);
        showingString = finalString;
    }
}
