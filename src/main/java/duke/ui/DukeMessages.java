package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Ui is a class that prints messages to the user.
 */
public class DukeMessages {
    /**
     * Prints a welcome message.
     * @return A string containing a welcome message.
     */
    public static String printWelcomeMessage() {
        String introduction = "Hello! I'm NEKOBOT!!\n";
        String question = "What can I do for you :>";
        return introduction + question;
    }

    /**
     * Prints a goodbye message.
     * @return A string containing a goodbye message.
     */
    public static String printGoodbyeMessage() {
        return "Bye~ Hope to see you again soon ;w;";
    }

    /**
     * Prints all tasks in the TaskList.
     * @param taskList An ArrayList containing all existing Tasks.
     * @return A string containing all tasks in the TaskList.
     */
    public static String printListMessage(ArrayList<Task> taskList) {
        String preface = "Here are the tasks in your list!!\n";
        StringBuilder tasks = new StringBuilder();
        int index = 1;
        for (Task task : taskList) {
            tasks.append(index).append(". ").append(task).append("\n");
            index++;
        }
        return preface + tasks;
    }

    /**
     * Prints all tasks whose date is set as the current date (today).
     * @param taskList An ArrayList containing all existing Tasks.
     * @return A string containing all tasks whose date is set as the current date (today).
     */
    public static String printTodayMessage(ArrayList<Task> taskList) {
        String preface = "Here are today's tasks!!\n";
        StringBuilder tasks = new StringBuilder();
        int index = 1;
        for (Task task : taskList) {
            tasks.append(index).append(". ").append(task).append("\n");
            index++;
        }
        return preface + tasks;
    }

    /**
     * Prints all tasks that match the search term provided.
     * @param taskList An ArrayList containing all Tasks that match the search term provided.
     * @return A string containing all tasks that match the search term provided.
     */
    public static String printFindMessage(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            return "Oh dear, I couldn't find any matching tasks :o";
        }

        String preface = "Here are your search results!!\n";
        StringBuilder tasks = new StringBuilder();
        int index = 1;
        for (Task task : taskList) {
            tasks.append(index).append(". ").append(task).append("\n");
            index++;
        }
        return preface + tasks;
    }

    /**
     * Prints a message indicating the specified task has been added.
     * @param task The Task that has been added.
     * @param size An integer value representing the new size of the TaskList.
     * @return A string confirming the addition of a specified task.
     */
    public static String printAddTaskMessage(Task task, int size) {
        String preface = "Okies! I've added this task~\n";
        String taskString = task.toString() + "\n";
        String currentSize = "Now you have " + size + " tasks in the list uwu";
        return preface + taskString + currentSize;
    }

    /**
     * Prints a message indicating the specified task has been marked completed.
     * @param task The Task that has been marked completed.
     * @return A string confirming that the specified task has been marked completed.
     */
    public static String printDoneTaskMessage(Task task) {
        String preface = "Yay! I've marked this task as done :3\n";
        String taskString = task.toString();
        return preface + taskString;
    }

    /**
     * Prints a message indicating the specified task has been deleted.
     * @param task The Task that has been deleted.
     * @param size An integer value representing the new size of the TaskList.
     * @return A string confirming that the specified task has been deleted.
     */
    public static String printDeleteTaskMessage(Task task, int size) {
        String preface = "Got it! I'll remove this task :>\n";
        String taskString = task.toString() + "\n";
        String currentSize = "Only " + size + " tasks left!!";
        return preface + taskString + currentSize;
    }

    /**
     * Prints out the error message of the exception that occurred.
     * @param uiMessage The error message of the exception that occurred.
     * @return A string that explains the exception that occurred.
     */
    public static String printErrorMessage(String uiMessage) {
        return uiMessage;
    }
}
