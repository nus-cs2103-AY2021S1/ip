package duke;

import duke.task.Task;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void printLines(String output) {
        System.out.println("______________________________________________________________________");
        System.out.println(output);
        System.out.println("______________________________________________________________________");
    }

    public void showWelcomeMessage() {
        printLines("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public void showExitMessage() {
        printLines("Bye. I hope to see you again soon!");
    }

    public void showAllTasks(TaskList tasks) {
        if (tasks.getNumOfTasks() == 0) {
            printLines("You currently have no tasks in your list.");
        } else {
            printLines("Here are the tasks in your list:\n" + tasks);
        }
    }

    public void showAddedMessage(Task task, TaskList tasks) {
        printLines("Task added successfully!\n\t" + task + showNumOfTasksMessage(tasks));
    }

    public void showDoneMessage(Task task) {
        printLines("Task completed successfully!\n\t" + task);
    }

    public void showDeletedMessage(Task task, TaskList tasks) {
        printLines("Task deleted successfully!\n\t" + task + showNumOfTasksMessage(tasks));
    }

    public String showNumOfTasksMessage(TaskList tasks) {
        return String.format("\nNow you have %d task(s) in the list.", tasks.getNumOfTasks());
    }

    public void showErrorMessage(Exception e) {
        printLines(e.getMessage());
    }
}