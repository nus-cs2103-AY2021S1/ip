package duke.ui;

import duke.task.Task;
import duke.task.TaskList;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    static String ADD_TASK_LINE = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
    static String DONE_TASK_LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    static String LIST_TASK_LINE = "________________________________________________________";
    static String BYE_LINE = "========================================================";
    static String INDENT = "    ";
    
    public Ui() {
        scanner = new Scanner(System.in);
    }
    
    public void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello my name\n" + logo + "\nHow may I help?");
    }
    
    public void displayTaskList(TaskList tasks) {
        ArrayList<Task> list = tasks.getList();
        System.out.println(INDENT + LIST_TASK_LINE);
        for (Task task : list) {
            System.out.println(INDENT + (list.indexOf(task) + 1) + "." + task.toString()
            );
        }
        if (list.size() == 0) {
            System.out.println(INDENT + "None");
        }
        System.out.println(INDENT + LIST_TASK_LINE);
    }
    
    public void displayDoneMessage(Task task) {
        System.out.println(
            INDENT + DONE_TASK_LINE + "\n"
            + INDENT + "The following task has been marked as done:\n"
            + INDENT + task.toString()
            + "\n" + INDENT + DONE_TASK_LINE
        );
    }
    
    public void displayDeletedTaskMessage(Task task, int taskCount) {
        System.out.println(
                INDENT + DONE_TASK_LINE + "\n"
                + INDENT + "The following task has been removed:\n"
                + INDENT + INDENT + task.toString() + "\n"
                + INDENT + "You now have " + taskCount + " task(s) in the list.\n"
                + INDENT + DONE_TASK_LINE
        );
    }

    public void displayAddTaskSuccess(Task task, int taskCount) {
        System.out.println(
            INDENT + ADD_TASK_LINE + "\n"
            + INDENT + "Added task:" +"\n"
            + INDENT + INDENT + task.toString() + "\n"
            + INDENT + "You now have " + taskCount + " task(s) in the list.\n"
            + INDENT + ADD_TASK_LINE
        );
    }
    
    public void displayError(String errorMessage) {
        System.out.println(INDENT + errorMessage);
    }
    
    public void displayGoodbye() {
        System.out.println(
            INDENT + BYE_LINE + "\n"
            + INDENT + "Goodbye\n"
            + INDENT + BYE_LINE
        );
    }
    
    public String readInput() {
        return scanner.nextLine();
    }
    
    public void exit() {
        scanner.close();
    }
}
