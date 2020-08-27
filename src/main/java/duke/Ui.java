package duke;

import duke.task.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Ui {

    public static String divider = "______________________________________________________________________________";
    public static String logo = "             *\n"
            + "      o  o  / \\  o  o\n"
            + "      |\\/ \\/   \\/ \\/|\n"
            + "      |             |\n"
            + "      |ooooooooooooo|\n"
            + " __  _  ____  ____    ____      ____    ___   ____\n"
            + "|  |/ ||    ||    \\  /    |    |    \\  /   \\ |    \\\n"
            + "|  ' /  |  | |  _  ||   __|    |  o  )|     ||  o  )\n"
            + "|    \\  |  | |  |  ||  |  |    |     ||  O  ||     |\n"
            + "|     | |  | |  |  ||  |_ |    |  O  ||     ||  O  |\n"
            + "|  .  | |  | |  |  ||     |    |     ||     ||     |\n"
            + "|__|\\_||____||__|__||___,_|    |_____| \\___/ |_____|\n";
    private final Scanner scanner = new Scanner(System.in);


    public void showList(List<Task> list) {
        System.out.println(divider);
        System.out.println("   Banana! So many tasks?");
        Task task;
        for (int i = 0; i < list.size(); i++) {
            task = list.get(i);
            System.out.println("   " + (i + 1) + ". " + task.toString());
        }
        System.out.println(divider + "\n");
    }

    public void greet() {
        System.out.println("Bello from the Majestic\n" + logo);
        System.out.println("Banana! What can King Bob do for you?\n" + divider + "\n");
    }

    public void wrapMessage(String message) {
        System.out.println(divider);
        System.out.println("   " + message);
        System.out.println(divider + "\n");
    }

    public void byeMessage() {
        wrapMessage("Banana! King Bob is sad to see you go. Farewell my friend!");
    }

    public void addedMessage(Task task, int n) {
        wrapMessage("Banana! Banana has been added to your list!\n"
                + "      " + task.toString() + "\n"
                + "   Now you have " + n + " banana(s) in your list! Nom nom..");
    }

    public void deletedMessage(Task task, int n) {
        wrapMessage("Banana! Banana has been eaten. Burp!\n"
                + "      " + task.toString() + "\n"
                + "   Now you have " + (n - 1) + " banana(s) in your list! Nom nom..");
    }

    public void doneMessage(Task task) {
        wrapMessage("Banana! I've marked this task as done:\n"
                + "      " + task.toString());
    }

    public void loadingErrorMessage() {
        wrapMessage("Banana! There is a loading error...");
    }

    public void findTask(LocalDate date, List<Task> list) {
        System.out.println(divider);
        for (Task t : list) {
            if (t.getDate().equals(date)) {
                System.out.println("   " + t.toString());
            }
        }
        System.out.println(divider + "\n");
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
