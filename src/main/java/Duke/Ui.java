package Duke;
import java.util.Scanner;

public class Ui {
    String command;
    Scanner sc = new Scanner(System.in);

    public Ui() {
        System.out.println("Hello! I'm meimei ^_^\nI could scream at you all day!");
    }

    public String ask() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("File not found >w<");
    }

    public static void bye() {
        System.out.println("Bye! Meimei will miss u :(");
    }

    public static void addedTask(Task task, int size) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void deletedTask(Task task) {
        System.out.println("Meimei will forget about this task:\n" + task);
    }
}
