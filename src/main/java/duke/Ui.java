package duke;

import duke.task.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Stores all User Interface related methods and objects.
 */
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

    /**
     * Displays list of tasks to user.
     * @param tasks tasks of user.
     */
    public void showList(List<Task> tasks) {
        System.out.println(divider);
        System.out.println("   Banana! So many tasks?");
        Task task;
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            System.out.println("   " + (i + 1) + ". " + task.toString());
        }
        System.out.println(divider + "\n");
    }

    /**
     * Prints greetings to user.
     */
    public void greet() {
        System.out.println("Bello from the Majestic\n" + logo);
        System.out.println("Banana! What can King Bob do for you?\n" + divider + "\n");
    }

    /**
     * Wraps message in dividers.
     * @param message Message to be wrapped in dividers.
     */
    public void wrapMessage(String message) {
        System.out.println(divider);
        System.out.println("   " + message);
        System.out.println(divider + "\n");
    }

    /**
     * Prints bye message to user.
     */
    public void byeMessage() {
        wrapMessage("Banana! King Bob is sad to see you go. Farewell my friend!");
    }

    /**
     * Prints added message to user.
     * @param task Task that is added.
     * @param tasksNumber number of tasks.
     */
    public void addedMessage(Task task, int tasksNumber) {
        wrapMessage("Banana! Banana has been added to your list!\n"
                + "      " + task.toString() + "\n"
                + "   Now you have " + tasksNumber + " banana(s) in your list! Nom nom..");
    }

    /**
     * Prints deleted message to user.
     * @param task Task that is deleted.
     * @param tasksNumber Number of tasks.
     */
    public void deletedMessage(Task task, int tasksNumber) {
        wrapMessage("Banana! Banana has been eaten. Burp!\n"
                + "      " + task.toString() + "\n"
                + "   Now you have " + (tasksNumber - 1) + " banana(s) in your list! Nom nom..");
    }

    /**
     * Prints done message to user.
     * @param task Task that is done.
     */
    public void doneMessage(Task task) {
        wrapMessage("Banana! I've marked this task as done:\n"
                + "      " + task.toString());
    }

    /**
     * Prints loading error message to user.
     */
    public void loadingErrorMessage() {
        wrapMessage("Banana! There is a loading error...");
    }

    /**
     * Prints tasks happening/due this date.
     * @param date date requested by user.
     * @param tasks tasks of user.
     */
    public void findTask(LocalDate date, List<Task> tasks) {
        System.out.println(divider);
        for (Task t : tasks) {
            if (t.getDate().equals(date)) {
                System.out.println("   " + t.toString());
            }
        }
        System.out.println(divider + "\n");
    }

    /**
     * Reads command of user.
     * @return string command of user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
