package duke;

import duke.tool.TaskList;
import duke.tasks.Task;

import java.util.Scanner;

/**
 * The class deals with interactions with the user.
 */
public class Ui {
    /**
     * The method to show the logo of Duke.
     */
    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * Seperate line of the printed items
     * @return
     */
    public String seperateLine() {
        return "    _______________________________________";
    }

    /**
     * The formatted space.
     * @return
     */
    public String spaceBeforeOder() {
        return "      ";
    }

    /**
     * Print the sentence in Duke's format.
     * @param sentence
     */
    public void printFormmat(String sentence) {
        System.out.println(seperateLine());
        System.out.println(sentence);
        System.out.println(seperateLine());
    }

    /**
     * Print the greeting message.
     */
    public void showGreeting() {
        printFormmat(spaceBeforeOder() + "Hello! I'm Duke yy\n      What can I do for you?");
    }

    /**
     * Print the loading error message.
     */
    public void showLoadingError() {
        System.out.println("Loading error!");
    }

    /**
     * Get the input from the user.
     * @return
     */
    public String getOrder() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Print the message showing the task is done.
     * @param tasklist
     * @param i ith task.
     */
    public void showDoneMessage(TaskList tasklist, int i) {
        printFormmat(spaceBeforeOder() + "Nice! I've marked this task as done:\n" +
                spaceBeforeOder() + tasklist.getTask(i) + "\n" + spaceBeforeOder() + "Now you have " +
                tasklist.getNumOfTasks() + " tasks in the list.");
    }

    /**
     * Print the message showing the task is deleted.
     * @param tasklist
     * @param removed the removed task.
     */
    public void showDeleteMessage(TaskList tasklist, Task removed) {
        printFormmat(spaceBeforeOder() + "Noted. I've removed this task:\n" +
                spaceBeforeOder() + removed + "\n" + spaceBeforeOder() + "Now you have " +
                tasklist.getNumOfTasks() + " tasks in the list.");
    }

    /**
     * Print the message showing the task is added.
     * @param tasklist
     * @param num current number of tasks in the list.
     */
    public void showAddedMessage(TaskList tasklist, int num) {
        printFormmat(spaceBeforeOder() + "Got it. I've added this task:\n" +
                spaceBeforeOder() + tasklist.getTask(num) +
                "\n" + spaceBeforeOder() + "Now you have " +
                (num + 1) + " tasks in the list.");
    }

    /**
     * Print the tasks in the list.
     * @param tasklist
     */
    public void listTasks(TaskList tasklist) {
        System.out.println(seperateLine());
        System.out.println(spaceBeforeOder() + "Here are the tasks in your list:");
        for (int i = 0; i < tasklist.getNumOfTasks(); i++) {
            System.out.println(spaceBeforeOder() + (i + 1) + ". " +
                    tasklist.getTask(i));
        }
        System.out.println(seperateLine());
    }

    /**
     * Print the tasks in the list which contain the certain string.
     * @param tasklist
     * @param toFind
     */
    public void listMatchedTasks(TaskList tasklist, String toFind) {
        System.out.println(seperateLine());
        System.out.println(spaceBeforeOder() + "Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < tasklist.getNumOfTasks(); i++){
            if (tasklist.getTask(i).getName().contains(toFind)) {
                System.out.println(spaceBeforeOder() + count + ". " +
                        tasklist.getTask(i));
                count++;
            }
        }
        System.out.println(seperateLine());
    }

    /**
     * Print the goodbye message.
     */
    public void showGoodbye() {
        printFormmat(spaceBeforeOder() + "Bye. Very nice to meet you!\n" +
                spaceBeforeOder() + "Hope to see you again soon!");
    }
}
