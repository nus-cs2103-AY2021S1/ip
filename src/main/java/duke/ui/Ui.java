package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Represents Ui class and consists of methods related to user interaction.
 */
public class Ui {

    private TaskList taskList;
    private static final String LINE_DIVIDER = "     _____________________________________";
    private static final String LOGO = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";

    /**
     * Constructs an ui object.
     *
     * @param taskList The TaskList object.
     */
    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints the message in a particular form, the first and last line
     * is the line divider.
     *
     * @param message The message to be printed out.
     */
    public void messageFormat(String... message) {
        System.out.println(LINE_DIVIDER);
        for (String i : message) {
            System.out.println(i);
        }
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Displays greetings to the user.
     */
    public void displayGreeting() {
        messageFormat("     Hi, I am\n", LOGO,
         "     Is there anything I could help with?");
    }

    /**
     * Displays exit message to the user.
     */
    public void displayExit() {
        messageFormat("     Bye! I look forward to meeting you next time!");
    }

    /**
     * Displays the list of tasks to the user.
     */
    public void getList() {
        ArrayList<Task> listOfTasks = taskList.getListOfTasks();
        int noOfTasks = listOfTasks.size();
        if (noOfTasks == 0) {
            messageFormat("     There is no task in the list yet!");
        } else {
            System.out.println(LINE_DIVIDER);
            System.out.println("     Here are the tasks in the list:");
            for (int i = 0; i < noOfTasks; i++) {
                Task task = listOfTasks.get(i);
                System.out.println("     " + (i + 1) + "." + task);
            }
            System.out.println(LINE_DIVIDER);
        }
    }

    /**
     * Displays error message to the user.
     *
     * @param message The error message.
     */
    public void displayError(String message) {
        messageFormat("     " + message);
    }

    /**
     * Displays the message that a particular task is marked as done
     * to the user.
     *
     * @param task The particular task that is marked as done.
     */
    public void displayDone(Task task) {
        messageFormat("     Great! The task below is marked as done:\n"
                + "        " + task.toString());
    }

    /**
     * Displays the message that a particular task is deleted to the user.
     *
     * @param task The particular task that is deleted.
     */
    public void displayDeletion(Task task) {
        ArrayList<Task> listOfTasks = taskList.getListOfTasks();
        int noOfTasks = listOfTasks.size();
        System.out.println(LINE_DIVIDER);
        System.out.println("     Okay. The task below is deleted from your list:\n"
                + "        " + task.toString());
        if (noOfTasks == 1) {
            System.out.println("     Now there is 1 task in total in your list.");
        } else {
            System.out.println("     Now there are " + listOfTasks.size() + " tasks "
                    + "in total in your list.");
        }
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Displays the message that a particular task is added
     * in to the list to the user.
     *
     * @param task The particular task that is added to the list.
     */
    public void displayAddition(Task task) {
        ArrayList<Task> listOfTasks = taskList.getListOfTasks();
        int noOfTasks = listOfTasks.size();
        System.out.println(LINE_DIVIDER);
        System.out.println("     Noted! The task below is added into the list:\n"
                + "        " + task);
        if (noOfTasks == 1) {
            System.out.println("     There is 1 task in total in your list.");
        } else {
            System.out.println("     There are " + listOfTasks.size()
                    + " tasks in total in your list.");
        }
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Displays the message that the tasks tasks matching the keyword
     * is found to the user, listing the matching tasks.
     *
     * @param matchingTasks The tasks matching the keyword.
     */
    public void displayFinding(ArrayList<Task> matchingTasks) {
        int noOfTasks = matchingTasks.size();
        if (noOfTasks == 0) {
            messageFormat("     There is no task that match with this keyword!");
        } else {
            System.out.println(LINE_DIVIDER);
            System.out.println("     Here are the task or tasks that match with this keyword:");
            for (int i = 0; i < noOfTasks; i++) {
                Task task = matchingTasks.get(i);
                System.out.println("     " + (i + 1) + "." + task);
            }
            System.out.println(LINE_DIVIDER);
        }
    }



}
