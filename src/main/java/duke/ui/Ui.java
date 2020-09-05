package duke.ui;

import duke.task.NumberedTask;

import java.util.Scanner;
import java.util.List;

/**
 * Formats Strings to be sent to the GUI to be displayed.
 */
public class Ui {

    private final String LINE_SEPARATOR = "***********************";

    public Ui() {

    }

    /**
     * Formats a String message by adding line separator Strings to before and after the message.
     *
     * @param msg the message to be formatted
     * @return Formatted String with line separators added
     */
    public String formatMessage(String msg) {
        String result = String.format("%s \n %s \n %s", LINE_SEPARATOR, msg, LINE_SEPARATOR);
        return result;
    }

    private String listAllTasks(List<NumberedTask> taskList) {
        StringBuilder tasks = new StringBuilder("");
        for (int i = 0; i < taskList.size(); i++) {
            tasks.append(taskList.get(i).toString());
            if (i != taskList.size() - 1) {
                tasks.append('\n');
            }
        }
        return tasks.toString();
    }

    /**
     * Formats a String to display to user all Tasks and the taskNumber mapped to them in a given List of NumberedTasks.
     *
     * @param taskList List containing NumberedTasks
     * @return String that provides feedback to users and displays all Tasks and their taskNumbers in the given List of
     *         NumberedTasks
     */
    public String allTasksToString(List<NumberedTask> taskList) {
        StringBuilder tasks = new StringBuilder("Here are the tasks in your list: \n");
        return tasks.append(listAllTasks(taskList)).toString();
    }

    /**
     * Formats a String to display to user all Tasks that match a keyword and the taskNumber mapped to them.
     *
     * @param taskList List containing NumberedTasks mappings where the Task in the mapping matches a keyword
     * @return String that provides feedback to users and displays all Tasks and their taskNumbers in the given List of
     *         NumberedTasks
     */
    public String foundTasksToString(List<NumberedTask> taskList) {
        StringBuilder tasks = new StringBuilder("Here are the matching tasks in your list: \n");
        return tasks.append(listAllTasks(taskList)).toString();
    }
}
