import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        printMessage("Unable to load tasks");
    }

    public void welcome() {
        printMessage("Wazzup! I am Duke the Nuke \uD83D\uDE08\n"
                + "What do you want?");
    }

    public void exit() {
        printMessage("Sayonara!");
    }

    public void invalidInput() {
        printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void addTask() {
        printMessage("Got it. I've added this task:");
    }

    public void removeTask() {
        printMessage("Noted. I've removed this task:");
    }

    public void doneTask() {
        printMessage("Nice! I've marked this task as done:");
    }

    public void showList() {
        printMessage("Here are the tasks in your list:");
    }

    public void showNumberOfTasks(ArrayList<Task> tasks) {
        printMessage("Now you have " + tasks.size() + " tasks in the list.");
    }
}


