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
     * @param message message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the loading error message.
     */
    public void showLoadingError() {
        printMessage("Unable to load tasks");
    }

    /**
     * @return the exit message for the user.
     */
    public String exit() {

        return "Sayonara! \uD83D\uDE1C \uD83D\uDE1C \uD83D\uDE1C\n";
    }

    /**
     * @return the "invalid input" error message.
     */
    public String invalidInput() {

        return "I don't know what that means. Please try again \uD83D\uDE11\n";
    }

    /**
     * @return the addTask message.
     */
    public String addTask() {

        return "\uD83D\uDE0E Got it. I've added this task:\n";
    }

    /**
     * @return the removeTask message.
     */
    public String removeTask() {

        return "\uD83D\uDE0E Noted. I've removed this task:\n";
    }

    /**
     * @return the doneTask message.
     */
    public String doneTask() {

        return "\uD83D\uDE0E Nice! I've marked this task as done:\n";
    }

    /**
     * Lists out the tasks.
     * @param taskList list containing the tasks.
     * @return the showList message with the list of tasks.
     */
    public String showList(ArrayList<Task> taskList) {

        String header = "\uD83D\uDE0E Here are the tasks in your list:\n";
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
     * Shows total number of tasks.
     * @param tasks the list containing the tasks.
     * @return the statement with the number of tasks.
     */
    public String showNumberOfTasks(ArrayList<Task> tasks) {
        return "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Finds matching tasks.
     * @param findTasks the list containing the tasks.
     * @return the matchingTasks message with the matching tasks.
     */
    public String printMatchingTasks(ArrayList<Task> findTasks) {

        String header = "\uD83D\uDE0E Here are the matching tasks in your list:\n";
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

    /**
     * Handles the possible matching tasks.
     * @param keyWord the string to match.
     * @param originalList original list of tasks.
     * @param newList new list for the tasks to be added.
     */
    public void handleMatchingTasks(String keyWord, ArrayList<Task> originalList, ArrayList<Task> newList) {
        for (Task task : originalList) {
            if (task.getDescription().contains(keyWord) && !newList.contains(task)) {
                newList.add(task);
            }
        }
    }

    /**
     * @return the "incorrect index" message.
     */
    public String printIncorrectIndexMessage() {
        return "Please enter the correct index of the task \uD83D\uDE11\n";
    }

    /**
     * @return the "incorrect task and date" message.
     */
    public String printIncorrectTaskOrDateMessage() {
        return "Please enter the correct format for the task and date \uD83D\uDE11\n";
    }
}


