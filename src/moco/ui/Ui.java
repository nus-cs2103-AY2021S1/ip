package moco.ui;

import moco.logic.TaskList;
import moco.task.Task;
import moco.logic.MocoException;

import java.util.Scanner;

public class Ui {
    private Scanner userInput;
    private String lastError;

    public Ui() {
        userInput = new Scanner(System.in);
    }

    public String getInput() {
        return userInput.nextLine();
    }

    public void startBot() {
        printBorder();
        System.out.println("Hello I'm Moco, a task list bot to help you stay on top of your tasks!\n");
        System.out.println("What can I do for you?\n");
        printBorder();
    }

    public void stopBot() {
        printBorder();
        System.out.println("Bye. Moco hopes to see you again soon!");
        printBorder();
    }

    public void printGreeting() {
        printBorder();
        System.out.println("Hello! My name is Moco, I am excited to help! c:");
        printBorder();
    }

    public void printError(String error) {
        this.lastError = error;
        System.out.println(error);
    }

    public void printBorder() {
        System.out.println("____________________________________________________________\n");
    }

    public void printTaskList(TaskList tasks) {
        printBorder();
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
        printBorder();
    }

    public void doneTask(Task t) {
        printBorder();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        printBorder();
    }


    public void deleteTask(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n" + task);
        printBorder();
    }

    public void addTask(Task task, TaskList tasks) throws MocoException {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
        printBorder();
    }
}