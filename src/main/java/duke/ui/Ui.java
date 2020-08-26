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
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void startupMsg() {
        System.out.println(logo);
        System.out.println(duke + "Hi I'm Duke!");
        System.out.println(duke + "What can I do for you?");
    }

    public String readInput() {
        System.out.print(cmd);
        return scanner.nextLine();
    }

    public void showHelp() {
        String helpMessage = "Here's what I can do:\n";
        String availableCommands =
                "Available Commands: \n"
                        + "'todo' \n"
                        + "'deadline' \n"
                        + "'event' \n"
                        + "'list' \n"
                        + "'delete' \n"
                        + "'bye'";
        System.out.println(helpMessage + availableCommands);
    }

    public void showTaskList(TaskList tasks) {
        int index = 0;
        for (Task t : tasks.getList()) {
            System.out.println(++index + ". " + t.toString());
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

    public void showTaskAddedMsg(Task task) {
        System.out.println(duke + "I've added '" + task.getTaskName() + "' to your Task List");
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
}
