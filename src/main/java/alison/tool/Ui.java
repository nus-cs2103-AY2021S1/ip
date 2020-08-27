package alison.tool;

import alison.exception.AlisonException;
import alison.task.Task;

import java.util.Scanner;

public class Ui {
    public void showLoadingError() {
        System.out.println(AlisonException.loadingException().getMessage());
    }

    public void printBorder() {
        System.out.println("________________________________________________________________");
    }

    /**
     * Hello from Alison bot.
     */
    public void greeting() {
        String logo =
                    "       d8888 888      8888888  .d8888b.   .d88888b.  888b    888 \n" +
                    "      d88888 888        888   d88P  Y88b d88P\" \"Y88b 8888b   888 \n" +
                    "     d88P888 888        888   Y88b.      888     888 88888b  888 \n" +
                    "    d88P 888 888        888    \"Y888b.   888     888 888Y88b 888 \n" +
                    "   d88P  888 888        888       \"Y88b. 888     888 888 Y88b888 \n" +
                    "  d88P   888 888        888         \"888 888     888 888  Y88888 \n" +
                    " d8888888888 888        888   Y88b  d88P Y88b. .d88P 888   Y8888 \n" +
                    "d88P     888 88888888 8888888  \"Y8888P\"   \"Y88888P\"  888    Y888";
        printBorder();
        System.out.println("Hello from\n" + logo);
        printBorder();
        String greet = "Hello! I'm Alison.\n" + "What can I do for you?";
        System.out.println(greet);
        printBorder();
    }

    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(String.format("%d.%s", i+1, task));
        }
    }

    public void markDoneMsg(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" + task);
    }

    public void addTaskMsg(Task added, TaskList tasks) {
        System.out.println("I've added this task: \n "
                + added + "\n"
                + String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    public void removeTaskMsg(Task removed, TaskList tasks) {
        System.out.println("Noted. I've removed this task: \n "
                + removed + "\n"
                + String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    /**
     * This method takes in user's input and returns a String.
     * @return String entered by the user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
