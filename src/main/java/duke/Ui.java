package duke;

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

    private void showNoTasksMsg() {
        System.out.println("There are currently no tasks in your list");
    }

    public void showTaskAdditionMessage(Task newTask, TaskList taskList) {
        System.out.println(Message.ADDED + newTask.toString() + "\n" +
                "Now you have " + taskList.size() +
                (taskList.size() == 1 ? " task " : " tasks ")
                + "in the list");
    }

    public void showTaskDeletionMessage(Task task,TaskList taskList) {
        System.out.println(Message.DELETE + task.toString() + "\n" +
                "Now you have " + taskList.size() +
                (taskList.size() == 1 ? " task " : " tasks ")
                + "in the list");
    }

    public void showCompletionMessage(Task task) {
        System.out.println(Message.DONE + task.toString());
    }

    public void showExit() {
        System.out.println(Message.MESSAGE_EXIT);
        scanner.close();
    }

}
