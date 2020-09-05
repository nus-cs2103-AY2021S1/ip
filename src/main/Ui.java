import java.util.ArrayList;

/**
 * Represents a ui object that deals with interactions with the user.
 */
public class Ui {
    String message;
    String divider;

    public Ui() {
        divider = "____________________________________________________\n";
    }

    public Ui(String message) {
        this.message = message;
        divider = "____________________________________________________\n";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Prints the message without borders.
     */
    public void printMessage() {
        System.out.println(message);
    }

    /**
     * Prints the message with borders.
     */
    public void printMessageWithBorders() {
        System.out.println(divider);
        System.out.println(message);
        System.out.println(divider);
    }

    /**
     * Prints the Duke logo.
     */
    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcome(ArrayList<Task> taskList) {
        message = "Hello, I'm Duke!\n\n";
        if (taskList.size() < 1) {
            message += "You currently have no tasks. ";
        } else {
            message +=  "Here are your existing tasks:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                message += i + 1 + "." + task.toString() + "\n";
            }
            message += "\n";
        }
        message += "What can I do for you?\n";
        setMessage(message);
        printMessageWithBorders();
    }

    /**
     * Prints the loading error.
     */
    public void printLoadingError() {
        message = "An error occurred while loading the data...";
        printMessage();
    }

    /**
     * Prints the tasks of a given array list of tasks.
     *
     * @param taskList Array list of tasks to be printed.
     */
    public void printTasks(ArrayList<Task> taskList) {
        if (taskList.size() < 1) {
            message = "You currently have no tasks.\n";
        } else {
            message = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String taskString = i + 1 + "." + task.toString() + "\n";
                message += taskString;
            }
        }
        printMessageWithBorders();
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbye() {
        message = "Bye. Hope to see you again soon!\n";
        printMessageWithBorders();
    }
}
