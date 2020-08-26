package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static String duke = "Duke> ";
    private static String cmd = ">> ";
    private static String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void startupMsg() {
        System.out.println(logo);
        System.out.println(duke + "Hi I'm Duke!");
        System.out.println(duke + "What can I do for you?");
    }

    public String readInput() {
        System.out.print(cmd);
        return sc.nextLine();
    }

    public void showHelp() {
        String s = "Here's what I can do:\n";
        String msg =
                "Available Commands: \n"
                        + "'todo' \n"
                        + "'deadline' \n"
                        + "'event' \n"
                        + "'list' \n"
                        + "'delete' \n"
                        + "'bye'";
        System.out.println(s + msg);
    }

    public void showTaskList(TaskList list) {
        int idx = 0;
        for (Task t : list.getList()) {
            System.out.println(++idx + ". " + t.toString());
        }
    }

    public void showErrorMsg(Exception e) {
        System.out.println(duke + e.getMessage());
    }

    public void showListEmptyMsg() {
        System.out.println(duke + "Your List is Empty.");
    }

    public void showListMsg() {
        System.out.println(duke + "Here's your Task List:");
    }

    public void showListDoneAskMsg() {
        System.out.println(duke + "Choose the task(s) to be marked as 'Done'");
    }

    public void showListDoneMsg() {
        System.out.println(duke + "Nice! I've marked the following as done:");
    }

    public void showTaskAddAskMsg() {
        System.out.println(duke + "Enter task details:");
    }

    public void showTaskAddedMsg(Task t) {
        System.out.println(
                duke + "I've added '" + t.getTaskName() + "' to your Task List");
    }

    public void showTaskDeleteAskMsg() {
        System.out.println("Choose the task(s) to be deleted.");
    }

    public void showTaskDeleteMsg() {
        System.out.println(duke + "I've deleted the task(s) you specified:");
    }

    public void showByeMsg() {
        System.out.println(duke + "See you soon!");
    }

    public void showFindPromptMsg() {
        System.out.println(duke + "Enter a keyword:");
    }

    public void showFoundMsg(String keyword) {
        System.out.println(duke + "Here are the task(s) matching the keyword: " + keyword);
    }

    public void showNotFoundMsg(String keyword) {
        System.out.println(duke + "Found no task(s) matching the keyword: " + keyword);
    }
}
