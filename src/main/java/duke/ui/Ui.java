package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Represents Ui class and consists of methods related to user interaction.
 */
public class Ui {

    private TaskList taskList;
    private static final String EMPTY_SPACE_BEFORE_TASK = "     ";
    private static final String GREETING_PART_ONE = "Hi, I am\n";
    private static final String GREETING_PART_TWO = "Is there anything I could help with?\n";
    private static final String EXIT_MESSAGE = "Bye! I look forward to meeting you next time!\n";
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
        for (String i : message) {
            System.out.println(i);
        }
    }

    /**
     * Displays greetings to the user.
     *
     * @return Greeting message.
     */
    public String displayGreeting() {
        return GREETING_PART_ONE + LOGO + GREETING_PART_TWO;
    }

    /**
     * Displays exit message to the user.
     *
     * @return Exit message.
     */
    public String displayExit() {
        return EXIT_MESSAGE;
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @return The list of tasks.
     */
    public String getList() {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Task> listOfTasks = taskList.getListOfTasks();
        int noOfTasks = listOfTasks.size();
        if (noOfTasks == 0) {
            stringBuilder.append("There is no task in the list yet!\n");
        } else {
            stringBuilder.append("Here are the tasks in the list:\n");
            for (int i = 0; i < noOfTasks; i++) {
                Task task = listOfTasks.get(i);
                stringBuilder.append((i + 1) + "." + task + "\n");
            }
        }
        return stringBuilder.toString();
    }


    /**
     * Displays error message to the user.
     *
     * @param message The error message.
     * @return The error message to be displayed.
     */
    public String displayError(String message) {
        return message + "\n";
    }

    /**
     * Displays the message that a particular task is marked as done
     * to the user.
     *
     * @param task The particular task that is marked as done.
     * @return The message that a particular task is marked as done.
     */
    public String displayDone(Task task) {
        return "Great! The task below is marked as done:\n"
                + EMPTY_SPACE_BEFORE_TASK + task.toString() + "\n";
    }

    /**
     * Displays the message that a particular task is deleted to the user.
     *
     * @param task The particular task that is deleted.
     * @return The message that a particular task is deleted.
     */
    public String displayDeletion(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Task> listOfTasks = taskList.getListOfTasks();
        int noOfTasks = listOfTasks.size();
        stringBuilder.append("Okay. The task below is deleted from your list:\n"
                + EMPTY_SPACE_BEFORE_TASK + task.toString() + "\n");
        if (noOfTasks == 1) {
            stringBuilder.append("Now there is 1 task in total in your list.\n");
        } else {
            System.out.println("Now there are " + listOfTasks.size() + " tasks "
                    + "in total in your list.\n");
        }
        return stringBuilder.toString();

    }

    /**
     * Displays the message that a particular task is added
     * in to the list to the user.
     *
     * @param task The particular task that is added to the list.
     * @return The message that a particular task is added into the list.
     */
    public String displayAddition(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Task> listOfTasks = taskList.getListOfTasks();
        int noOfTasks = listOfTasks.size();
        stringBuilder.append("Noted! The task below is added into the list:\n"
                + EMPTY_SPACE_BEFORE_TASK + task + "\n");
        if (noOfTasks == 1) {
            stringBuilder.append("There is 1 task in total in your list.\n");
        } else {
            stringBuilder.append("There are " + listOfTasks.size()
                    + " tasks in total in your list.\n");
        }
        return stringBuilder.toString();

    }

    /**
     * Displays the message that the tasks tasks matching the keyword
     * is found to the user, listing the matching tasks.
     *
     * @param matchingTasks The tasks matching the keyword.
     * @return The message that lists the matching tasks.
     */
    public String displayFinding(ArrayList<Task> matchingTasks) {
        StringBuilder stringBuilder = new StringBuilder();
        int noOfTasks = matchingTasks.size();
        if (noOfTasks == 0) {
            stringBuilder.append("There is no task that match with this keyword!\n");
        } else {
            stringBuilder.append("Here are the task or tasks that match with this keyword:\n");
            for (int i = 0; i < noOfTasks; i++) {
                Task task = matchingTasks.get(i);
                stringBuilder.append((i + 1) + "." + task + "\n");
            }
        }
        return stringBuilder.toString();
    }



}
