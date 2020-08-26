package duke;

import java.util.Scanner;

public class Ui {

    private final static String DIVIDER = "__________________________________________________________";
    private final Scanner SC = new Scanner(System.in);

    private void messageFormatter(String word) {
        System.out.println(DIVIDER);
        System.out.println(word);
        System.out.println(DIVIDER + "\n");
    }

    public String readCommand() {
        return SC.nextLine();
    }

    public void welcomeMessage(String taskList) {
        messageFormatter("Hello! I'm Duke! Welcome back!\n" + taskList);
    }

    public void byeMessage() {
        messageFormatter("Bye. Hope to see you again soon!");
    }

    public void listMessage(String taskList) {
        messageFormatter(taskList);
    }

    public void errorMessage(String error) {
        messageFormatter(error);
    }

    public void markAsDoneMessage(String message) {
        messageFormatter("Nice! I've marked this task as done:\n" + message);
    }

    public void deleteMessage(String task, int size) {
        messageFormatter("Noted. I've removed this task:\n" + task
                + "\nNow you have " + size+ " tasks in the list.");
    }

    public void taskMessage(String task, int size) {
        messageFormatter("Got it. I've added this task:\n" + task +
                "\nNow you have " + size + " tasks in the list.");
    }

}
