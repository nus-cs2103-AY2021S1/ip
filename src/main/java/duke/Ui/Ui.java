package duke.Ui;

import duke.Task.Task;
import duke.Task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLoadingError() {
        showLine();
        System.out.println("Failed to load tasks. An empty list is created.");
        showLine();
    }

    public void showWelcome() {
        showLine();
        System.out.println(Message.MESSAGE_WELCOME);
        showLine();
    }

    public void showLine() {
        System.out.println(Message.BORDERS);
    }

    public void showError(String message) {
        System.out.printf("\u2639 OOPS!!! %s\n", message);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints a list of tasks
     *
     * @param tasks A list of tasks to be printed
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            showNoTasksMsg();
        } else {
            System.out.println("Here are the tasks in your list:");
            ArrayList<Task> taskList = tasks.getTasks();
            for (int i = 1; i <= taskList.size(); i ++) {
                Task task = taskList.get(i - 1);
                System.out.printf("%d.%s\n", i, task);
            }
        }
    }

    /**
     * Prints a message if there are no tasks in the list
     */
    private void showNoTasksMsg() {
        System.out.println("There are currently no tasks in your list");
    }

    /**
     * Prints a message upon successful addition of a task to the list
     *
     * @param newTask The task to be added to the list
     * @param taskList The list of tasks after the addition
     */
    public void showTaskAdditionMessage(Task newTask, TaskList taskList) {
        System.out.println(Message.ADDED + newTask.toString() + "\n" +
                "Now you have " + taskList.size() +
                (taskList.size() == 1 ? " task " : " tasks ")
                + "in the list");
    }

    /**
     * Prints a message upon successful deletion of a task from the list
     *
     * @param task The task to be removed from the list
     * @param taskList The list of tasks after the deletion
     */
    public void showTaskDeletionMessage(Task task,TaskList taskList) {
        System.out.println(Message.DELETE + task.toString() + "\n" +
                "Now you have " + taskList.size() +
                (taskList.size() == 1 ? " task " : " tasks ")
                + "in the list");
    }

    /**
     * Prints a message upon successful completion of a task in the list
     *
     * @param task The task that is completed in the list
     */
    public void showCompletionMessage(Task task) {
        System.out.println(Message.DONE + task.toString());
    }

    public void showExit() {
        System.out.println(Message.MESSAGE_EXIT);
        scanner.close();
    }

}
