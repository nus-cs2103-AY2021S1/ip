package dd.ui;

import dd.tasks.Task;

import java.util.Scanner;

public class Ui {

    private final Scanner sc = new Scanner(System.in);

    public void dataCreate(String fName) {
        System.out.println("New data created: " + fName);
    }

    public void dataExists() {
        System.out.println("Data already exists.");
    }

    public void exit() {
        System.out.println("You're leaving? Bye :( Come back soon!"
                + "\n_________________________________________");
    }

    public void greeting() {
        String logo = " ____   ____\n"
                + "|  _ \\ |  _ \\\n"
                + "| | | || | | |\n"
                + "| |_| || |_| |\n"
                + "|____/ |____/\n";
        System.out.println("Hi! I'm\n" + logo + "How can I help you? :)\n"
                + "_________________________________________");
    }

    public void printDeletedTask(Task t) {
        System.out.println("Alright! I've deleted the task:\n  " + t);
    }

    public void printDoneTask(Task t) {
        System.out.println("Wow!! Good job!!\n  " + t);
    }

    public void printLine() {
        System.out.println("_________________________________________");
    }

    public void printTask(int taskIndex, Task t) {
        System.out.println(taskIndex + ". " + t);
    }

    public void printTasksSize(int taskSize) {
        System.out.println("You now have " + taskSize + " task(s) in your list!");
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showLoadingError() {
        System.out.println("An error occurred, unable to load prior data. New list created.");
    }

    public void startAddTodo(Task t) {
        System.out.println("Ok, To-do added:\n  " + t);
    }

    public void startAddDeadline(Task t) {
        System.out.println("Ok, Deadline added:\n  " + t);
    }

    public void startAddEvent(Task t) {
        System.out.println("Ok, Event added:\n  " + t);
    }

    public void startCheckDate(String date) {
        System.out.println("Here is your list of task(s) on " + date + ":");
    }

    public void startList() {
        System.out.println("Here is your current list of task(s)!");
    }

    public void updateData() {
        System.out.println("Updated your data!");
    }
}
