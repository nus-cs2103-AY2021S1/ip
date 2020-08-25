package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * A class to deal with the interactions with users.
 */
public class Ui {
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
     * Print welcome.
     */
    public void showWelcome() {
        System.out.println(line
                + logo
                + "\n Hello, I'm Duke \n What can I do for you?\n"
                + line);
    }

    /**
     * Read the full command from user's input.
     *
     * @return String of the next input line.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Print the error message of a DukeException.
     *
     * @param eMsg the error message of the DukeException.
     */
    public void showError(String eMsg) {
        System.out.println(line
                + eMsg + "\n"
                + line);
    }

    private void printLine() {
        System.out.print(line);
    }

    /**
     * Print out the list of tasks.
     *
     * @param taskList the list of tasks.
     */
    public void printList(List<Task> taskList) {
        if (taskList.size() == 0) {
            printLine();
            System.out.println(" Oops, no task YET. Try to add one!");
            printLine();
            return;
        }
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + taskList.get(i));
        }
        printLine();
    }

    /**
     * Print out the Done command executed on a certain task.
     *
     * @param task the task to be done.
     */
    public void printDone(Task task) {
        System.out.println(line
                + " Nice! I've marked this task as done: "
                + "\n   " + task + "\n"
                + line);
    }

    /**
     * Print out the Delete command executed on a certain task.
     *
     * @param task the task to be deleted.
     * @param size the size of the task list after deletion.
     */
    public void printDelete(Task task, int size) {
        System.out.println(line
                + " Noted. I've removed this task: "
                + "\n   " + task
                + "\n Now you have " + size + " tasks in the list.\n"
                + line);
    }

    /**
     * Print out the Add command.
     *
     * @param task the task to be added.
     * @param size the size of the task list after addition.
     */
    public void printAdd(Task task, int size) {
        System.out.println(line
                + " Got it. I've added this task: ");
        System.out.println("   " + task
                + "\n Now you have " + size + " tasks in the list.\n"
                + line);
    }

    /**
     * Print out the list of tasks which was found by find command.
     *
     * @param findResult the list of tasks which was found.
     */
    public void printFind(List<Task> findResult) {
        if (findResult.size() == 0) {
            printLine();
            System.out.println(" No task founded. Try to add one!");
            printLine();
            return;
        }
        printLine();
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < findResult.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + findResult.get(i));
        }
        printLine();
    }

    /**
     * Say good bye to user.
     */
    public void bye() {
        System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
    }
}
