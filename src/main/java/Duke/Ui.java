package Duke;

import Duke.Tasks.Task;
import Duke.Tool.TaskList;

import java.util.Scanner;

public class Ui {
    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public String seperateLine() {
        return "    _______________________________________";
    }

    public String spaceBeforeOder() {
        return "      ";
    }

    public void printFormmat(String sentence) {
        System.out.println(seperateLine());
        System.out.println(sentence);
        System.out.println(seperateLine());
    }

    public void showGreeting() {
        printFormmat(spaceBeforeOder() + "Hello! I'm Duke yy\n      What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("Loading error!");
    }

    public String getOrder() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showDoneMessage(TaskList tasklist, int i) {
        printFormmat(spaceBeforeOder() + "Nice! I've marked this task as done:\n" +
                spaceBeforeOder() + tasklist.getTask(i) + "\n" + spaceBeforeOder() + "Now you have " +
                tasklist.getNumOfTasks() + " tasks in the list.");
    }

    public void showDeleteMessage(TaskList tasklist, Task removed) {
        printFormmat(spaceBeforeOder() + "Noted. I've removed this task:\n" +
                spaceBeforeOder() + removed + "\n" + spaceBeforeOder() + "Now you have " +
                tasklist.getNumOfTasks() + " tasks in the list.");
    }

    public void showAddedMessage(TaskList tasklist, int num) {
        printFormmat(spaceBeforeOder() + "Got it. I've added this task:\n" +
                spaceBeforeOder() + tasklist.getTask(num) +
                "\n" + spaceBeforeOder() + "Now you have " +
                (num + 1) + " tasks in the list.");
    }

    public void listTasks(TaskList tasklist) {
        System.out.println(seperateLine());
        System.out.println(spaceBeforeOder() + "Here are the tasks in your list:");
        for (int i = 0; i < tasklist.getNumOfTasks(); i++){
            System.out.println(spaceBeforeOder() + (i + 1) + ". " +
                    tasklist.getTask(i));
        }
        System.out.println(seperateLine());
    }

    public void showGoodbye() {
        printFormmat(spaceBeforeOder() + "Bye. Very nice to meet you!\n" +
                spaceBeforeOder() + "Hope to see you again soon!");
    }
}
