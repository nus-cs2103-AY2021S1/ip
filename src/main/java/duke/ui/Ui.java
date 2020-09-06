package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;
import duke.tool.TaskList;

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
    public String showGreeting() {
        return  "Hello! I'm Duke DuiDui\nWhat can I do for you?";
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
     * @return
     */
    public String showDoneMessage(TaskList tasklist, int i) {
        return "Nice! I've marked this task as done:\n"
                + spaceBeforeOder() + tasklist.getTask(i) + "\n" + spaceBeforeOder() + "Now you have "
                + tasklist.getNumOfTasks() + " tasks in the list.";
    }

    /**
     * Print the message showing the task is deleted.
     * @param tasklist
     * @param removed the removed task.
     * @return
     */
    public String showDeleteMessage(TaskList tasklist, Task removed) {
        return "Noted. I've removed this task:\n"
                + spaceBeforeOder() + removed + "\n" + spaceBeforeOder() + "Now you have "
                + tasklist.getNumOfTasks() + " tasks in the list.";
    }

    /**
     * Print the message showing the task is added.
     * @param tasklist
     * @param num current number of tasks in the list.
     */
    public String showAddedMessage(TaskList tasklist, int num) {
        return "Got it. I've added this task:\n"
                + spaceBeforeOder() + tasklist.getTask(num)
                + "\n" + spaceBeforeOder() + "Now you have "
                + (num + 1) + " tasks in the list.";
    }

    /**
     * Returns string representation of the tasks in the list.
     *
     * @param task
     * @param taskList
     * @return the string representation of the task.
     */
    public String presentTask(Task task, TaskList taskList) {
        return spaceBeforeOder() + (taskList.getTaskList().indexOf(task) + 1)
                + ". " + task + "\n";
    }

    /**
     * Returns the string represents the whole task list
     * starting with the first sentence.
     *
     * @param firstSentence
     * @param taskList
     * @return the string representation.
     */
    public String UseStreamListTasks(String firstSentence, TaskList taskList) {
        return taskList.getTaskList().stream()
                .reduce(firstSentence,
                        (string, task) -> string + presentTask(task, taskList),
                        (string1, string2) -> string1 + string2);
    }

    /**
     * Print the tasks in the list.
     * @param tasklist
     * @return
     */
    public String listTasks(TaskList tasklist) {
        String firstSentence = "Here are the tasks in your list:\n";
        return UseStreamListTasks(firstSentence, tasklist);
    }

    /**
     * Print the tasks in the list which contain the certain string.
     * @param tasklist
     * @param toFind
     * @return
     */
    public String listMatchedTasks(TaskList tasklist, String toFind) {
        String firstSentence = "Here are the matching tasks in your list:\n";
        TaskList listWithKeyword = new TaskList(new ArrayList<>());
        tasklist.getTaskList().stream()
                .filter(task -> task.getName().contains(toFind))
                .forEach(task -> listWithKeyword.add(task));
        return UseStreamListTasks(firstSentence, listWithKeyword);
    }

    /**
     * Print the goodbye message.
     * @return
     */
    public String showGoodbye() {
        return "Bye. Very nice to meet you!\n"
                + "Do remember my name is DuiDui.\n"
                + "Hope to see you again soon!";
    }
}
