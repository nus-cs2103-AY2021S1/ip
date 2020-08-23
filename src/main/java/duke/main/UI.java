package duke.main;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private static Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    private void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    private void showCmd(String s) {
        showLine();
        System.out.println(s);
        showLine();
    }

    public void showWelcome() {
        showCmd(" Hello! I'm Duke!");
    }

    public void showGoodbye() {
        showCmd(" Bye. Hope to see you again soon!");
    }

    public void showError(String error) {
        showCmd(error);
    }

    public void addTask(String task, int n) {
        String s = " Got it. I've added this task:\n   " + task + "\n";
        showCmd(s.concat(displayListSize(n)));
    }

    public void displayList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            showCmd("You have no tasks left! Good job my child!");
        } else {
            String s = "";
            if (taskList.size() == 1) {
                s = s.concat(" Here is the task in your list:\n");
            } else {
                s = s.concat(" Here are the tasks in your list:\n");
            }
            for (int i = 0; i < taskList.size(); i++) {
                s = s.concat(" " + (i+1) + "." + taskList.get(i) + "\n");
            }
            s = s.substring(0, s.length() - 1);
            showCmd(s);
        }
    }

    public String displayListSize(int n) {
        if (n == 0) {
            return (" Great job son! You're left with no more tasks!");
        }
        else if (n == 1) {
            return String.format(" Now you have %d task in the list.", n);
        }
        else {
            return String.format(" Now you have %d tasks in the list.", n);
        }
    }

    public void startSaving() {
        System.out.println("Saving...");
    }

    public void saveSuccess() {
        System.out.println("Saved successfully!");
    }

    public void startLoading() {
        System.out.println("Fetching old data...");
    }

    public void loadSuccess() {
        System.out.println("Data successfully loaded! ^^");
    }

    public void doneTask(String task) {
        showCmd(String.format("Great job! I'll mark '%s' as done for you. ^^", task));
    }

    public void deleteTask(String task, int n) {
        String s = " Noted. I've removed this task:\n  " + task + "\n";
        showCmd(s.concat(displayListSize(n)));
    }

    public void showHelp() {
        showCmd("Here are the list of commands you can use:\n" +
                "help\nlist\nsave\nbye\ntodo 'TASK'\ndeadline 'TASK' /by 'DATE & TIME'\n" +
                "event 'TASK' /by 'DATE & TIME'\ndone 'n'\ndelete 'n'");
    }
}
