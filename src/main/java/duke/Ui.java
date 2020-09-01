package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    public void printWithDivider(String print) {
        System.out.println("__________________________________________________");
        System.out.println(print);
        System.out.println("__________________________________________________");
    }

    public void printWelcome() {
        printWithDivider("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void printExit() {
        printWithDivider("Bye. Hope to see you again soon!");
    }

    public void printList(TaskList taskList) {
        printWithDivider("Here are the tasks in your list:" + taskList);
    }

    public void printDoneTask(Task task) {
        printWithDivider("Nice! I've marked this task as done:\n\t" + task);
    }

    public void printDeleteTask(Task task, TaskList taskList) {
        printWithDivider("Noted. I've removed this task:\n\t" + task + getNumOfTasks(taskList));
    }

    public void printAddTask(Task task, TaskList taskList) {
        printWithDivider("Got it. I've added this task:\n\t" + task + getNumOfTasks(taskList));
    }

    /**
     * Prints a numbered list of the tasks with the specified keyword.
     * @param keyword is the keyword used to find the tasks.
     * @param taskList is the task list to find the tasks with keyword from.
     */
    public void printFindTasks(String keyword, TaskList taskList) {
        printWithDivider("Here are the matching tasks in your list:" + taskList.getMatchingTasks(keyword));
    }

    public String getNumOfTasks(TaskList taskList) {
        int numOfTasks = taskList.getNumOfTasks();
        String msg = "";
        if (numOfTasks == 1) {
            msg = String.format("\nNow you have %d task in the list.", numOfTasks);
        } else {
            msg = String.format("\nNow you have %d tasks in the list.", numOfTasks);
        }
        return msg;
    }

    public void showError(Exception e) {
        printWithDivider(e.getMessage());
    }
}
