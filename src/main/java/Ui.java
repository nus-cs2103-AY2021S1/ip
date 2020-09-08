import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * A class responsible for interactions with the user and showing success/failure messages.
 */
public class Ui {

    public Scanner sc;

    /**
     * Constructor.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints out the given message.
     * @param message
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads the next line from the scanner.
     * @return the next line
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Prints the loading error message.
     */
    public void showLoadingError() {
        printMessage("Unable to load tasks");
    }

    /**
     * Prints the welcome message for the user.
     */
    public String welcome() {
        return "Wazzup! I am Duke the Nuke \uD83D\uDE08\n"
                + "What do you want?\n";
    }

    /**
     * Prints the exit message for the user.
     */
    public String exit() {

        return "Sayonara!\n";
    }

    /**
     * Prints the "invalid input" error message.
     */
    public String invalidInput() {

        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }

    /**
     * Prints the addTask message.
     */
    public String addTask() {

        return "Got it. I've added this task:\n";
    }

    /**
     * Prints the removeTask message.
     */
    public String removeTask() {

        return "Noted. I've removed this task:\n";
    }

    /**
     * Prints the doneTask message.
     */
    public String doneTask() {

        return "Nice! I've marked this task as done:\n";
    }

    /**
     * Prints the showList message with the list of tasks.
     */
    public String showList(ArrayList<Task> taskList) {

        String header = "Here are the tasks in your list:\n";
        Iterator<Task> iter = taskList.iterator();
        int index = 1;
        String message = "";
        while (iter.hasNext()) {
            Task currentTask = iter.next();
            String next = currentTask.getDescription();
            message = message + index + "." + "[" + currentTask.getType() + "]["
                    + currentTask.getStatusIcon() + "] " + next + "\n";
            index++;
        }
        return header + message;
    }

    /**
     * Prints the statement with the number of tasks.
     * @param tasks the list containing the tasks.
     */
    public String showNumberOfTasks(ArrayList<Task> tasks) {
        return "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Prints the matchingTasks message with the matching tasks.
     */
    public String printMatchingTasks(ArrayList<Task> findTasks) {

        String header = "Here are the matching tasks in your list:\n";
        String found = "";
        int index = 1;
        for (Task tsk : findTasks) {
            String desc =  tsk.getDescription();
            String taskType = tsk.getType();
            String statusIcon = tsk.getStatusIcon();
            found = found + index + "." + "[" + taskType + "][" + statusIcon + "] " + desc + "\n";
            index++;
        }
        return header + found;

    }
}


