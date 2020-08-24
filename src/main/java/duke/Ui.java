package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {

    protected Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        String userInput = this.sc.nextLine();
        return userInput;
    }

    public void showLine() {
        System.out.println("\t\u25A0_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\u25A0");
    }

    public void showTotalTasks(int total) {
        System.out.println("\t Now you have " + total + " tasks in the task list.");
    }

    public void showTaskAdded(TaskList taskList) {
        showLine();
        System.out.println("\t Got it. I've added this task: " + "\n\t  "
                + taskList.getTask(taskList.totalTask() - 1).toString());
        showTotalTasks(taskList.totalTask());
        showLine();
    }

    public void showDone(Task task) {
        showLine();
        System.out.println("\t Nice! I've marked this task as done: " + "\n\t   " + task.toString());
        showLine();
    }

    public void showDelete(TaskList taskList, Task task) {
        showLine();
        System.out.println("\t Noted. I've removed this task: " + "\n\t   " + task.toString());
        showTotalTasks(taskList.totalTask());
        showLine();
    }

    public void printList(TaskList taskList) {
        showLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskList.totalTask(); i++) {
            System.out.println("\t " + (i + 1) + "." + taskList.getTask(i).toString());
        }
        showLine();
    }

    public void showError(String errorMessage) {
        showLine();
        System.out.println("\t " + errorMessage);
        showLine();
    }

    public void showLoadingError() {
        showLine();
        System.out.println("\tSomething wrong with loading from your file.. Proceeding to create new empty TaskList");
        showLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ \uD83D\uDD34 \uD83D\uDD34 \\\n"
                + "| |_| | |_| |   <  \\__/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm\n" + logo);
    }
}
