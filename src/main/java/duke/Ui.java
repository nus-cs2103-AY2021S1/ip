package duke;

import exception.DukeException;
import task.Task;

import java.util.Scanner;

public class Ui {
    private String line = "____________________________________________________________";
    String welcomeMessage = "Hello! I'm Duke\n What can I do for you?";
    String goodbyeMessage = "Bye! Message me anytime!!";
    Scanner sc = new Scanner(System.in);

    public void showLine() {
        System.out.println(this.line);
    }

    public void showWelcome() {
        System.out.println(line);
        System.out.println(this.welcomeMessage);
        System.out.println(line);
    }

    public void showGoodbye() {
        System.out.println(this.goodbyeMessage);
    }

    public void showDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
    }

    public void showAdd(Task task, int size) {
        System.out.println("Added task!");
        System.out.println(task);
        showTaskQty(size);

    }

    public void showTaskQty(int size) {
        System.out.println(String.format("You have %s remaining tasks left, work on them soon!", size));
    }

    public void showDelete(Task task, int size) {
        System.out.println("Deleting task: ");
        System.out.println(task);
        showTaskQty(size);
    }

    public void showError(String errMsg) {
        System.out.println(errMsg);
    }

    public void displayTaskList(TaskList taskList) {
        System.out.println("Here are your current tasks:");
        for (int i = 0; i < taskList.getTaskList().size(); i++) {
            System.out.println(i + 1 + "." + taskList.getTaskList().get(i));
        }
    }

    public String readCommand() throws DukeException {
        String line = sc.nextLine();
        if (line != null) {
            return line.trim();
        } else {
            throw new DukeException("Please type in a command!");
        }
    }
}
