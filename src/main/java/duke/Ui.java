package duke;

import duke.task.Task;

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

    public String printHelp() {
        message = "I see that you need some help!\n\n"
                + "Here are a list of Duke commands ([] indicates user input):\n\n"
                + "1. list - shows a list of your existing tasks\n"
                + "2. find [keyword] - shows a list of existing tasks which contain the given keyword\n"
                + "3. todo [description] - adds a todo with a description\n"
                + "4. deadline [description] /by [date in yyyy-mm-dd format] - adds a deadline with a description and a deadline\n"
                + "5. event [description] /at [date in yyyy-mm-dd format] - adds an event with a description and an event date\n"
                + "6. done [index] - marks the specified task as done\n"
                + "7. delete [index] - deletes the specified task from the task list\n"
                + "8. help - shows the help page\n"
                + "9. bye - exits the application";
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
