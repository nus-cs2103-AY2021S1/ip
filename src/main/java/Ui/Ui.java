package Ui;

import exceptions.DukeException;
import task.Task;
import task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "===================================================";

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        String welcome = logo + "\n"
                + DIVIDER + "\n"
                + "Hello! I'm Ray\n" + "Machine learning and AI powers me\n"
                + DIVIDER + "\n";
        System.out.println(welcome);
    }

    public String readCommand() {
        System.out.println(DIVIDER);
        System.out.println("Please enter a command");
        return in.nextLine().trim();

    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void printAddTaskMessage(TaskList taskList, Task task) {
        String message = "Got it. I've added this task:\n " + task + "\nNow you have "
                + taskList.size() + " in the list";
        System.out.println(message);
    }

    public void printDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n " + task);
    }

    public void printDeleteMessage(TaskList taskList, Task task) {
        System.out.println("Noted. I've removed this task:\n " + task
                        + "\nNow you have " + taskList.size() + " in the list.");
    }

    public void printAllTasks(TaskList taskList) {
            System.out.println("Here are the tasks in your list");
        listAllTasks(taskList);
    }
    public void printDatabaseTasks(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks currently");
        } else {
            System.out.println("Here are the saved from your last session");
            listAllTasks(taskList);
        }
    }

    private void listAllTasks(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            try {
                String output = (i + 1) + "." + taskList.getTask(i + 1);
                System.out.println(output);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void displayErrorMessage(String message) {
        System.out.println(message + ":((((");
    }


}
