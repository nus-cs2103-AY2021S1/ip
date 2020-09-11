import java.util.ArrayList;

/**
 * Represents a ui object that deals with interactions with the user.
 */
public class Ui {
    String message;
    String divider;

    public Ui() {
        divider = "________________________________________________\n";
    }

    public Ui(String message) {
        this.message = message;
        divider = "________________________________________________\n";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Prints the message without borders.
     */
    public String printMessage() {
        return message;
    }

    /**
     * Prints the welcome message.
     */
    public String printWelcome(TaskList taskList) {
        message = "Hello, I'm Duke!\n";
        if (taskList.getTasks().size() < 1) {
            message += "You currently have no tasks. ";
        } else {
            message +=  "Here are your existing tasks:\n";
            for (int i = 0; i < taskList.getTasks().size(); i++) {
                Task task = taskList.getTasks().get(i);
                message += i + 1 + "." + task.toString() + "\n";
            }
            message += "\n";
        }
        message += "What can I do for you?\n";
        setMessage(message);
        return printMessage();
    }

    /**
     * Prints the loading error.
     */
    public String printLoadingError() {
        message = "An error occurred while loading the data...";
        return printMessage();
    }

    /**
     * Prints the tasks of a given array list of tasks.
     *
     * @param taskList Array list of tasks to be printed.
     */
    public String printTasks(ArrayList<Task> taskList) {
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
        return printMessage();
    }

    public String printFind(ArrayList<Task> taskList, String keyword) {
        if (taskList.size() < 1) {
            message = "You currently have no tasks.\n";
        } else {
            int count = 1;
            message = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task.contains(keyword)) {
                    String taskString = count + "." + task.toString() + "\n";
                    message += taskString;
                    count++;
                }
            }
        }
        return printMessage();
    }

    /**
     * Prints the goodbye message.
     */
    public String printGoodbye() {
        message = "Bye. Hope to see you again soon!\n";
        return printMessage();
    }
}
