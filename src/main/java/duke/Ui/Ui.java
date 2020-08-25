package duke.Ui;

import duke.Tasks.Task;

import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showWelcome() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Welcome to \n" + logo + "\n    Your personal assistant :)");
        showLine();
        showLine();
    }

    public void showLine() {
        System.out.println("    ______________________________________________________");
    }

    public void showLoadingError(String error) {
        System.out.println(error);
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showExit() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public void printTask(int counter, Task task) {
        System.out.println("    " + counter + ": " + task.toString());
    }

    public void addTask(int listSize, Task task) {
        System.out.println("    Got it. I've added this task: \n     " + task.toString());
        System.out.println("    Now you have " + listSize + " tasks in the list.");
    }

    public void markDone(Task task){
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task.toString());
    }

    public void markDelete(int listSize,Task task){
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    " + task.toString());
        System.out.println("    Now you have " + listSize + " tasks in the list.");
    }

}
