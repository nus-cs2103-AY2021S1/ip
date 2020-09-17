package duke.util;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the printing outputs according to the user commands.
 */
public class Ui {
    Scanner scanner;

    /**
     * Constructor.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a welcome message when duke.Duke runs.
     */
    public String printWelcomeMessage() {
        String string = "Hello! I'm duke.Duke baby :)\n" + "What can I do for you?";
        return string;
    }

    /**
     * Reads the next line of the input.
     * @return String next line.
     */
    public String readNextLine() {
        String query = "";
        query = scanner.nextLine();
        return query;
    }

    /**
     * Prints a find message of the list of tasks that matches the find description.
     * @param taskList The list of tasks that matches the find description.
     */
    public String printFindMessage(TaskList taskList) {
        String string;
        if (taskList.size() == 0) {
            string = "I can't find anything that match!";
            return string;
        }
        string = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            String stringCount = String.valueOf(i + 1);
            string += stringCount + ". " + taskList.get(i);
        }
        return string;
    }

    /**
     * Prints a message for task that just had its priority marked.
     * @param taskToMark Task that has priority marked.
     */
    public String printPriorityMessage(Task taskToMark) {
        String string;
        string = "Nice! I've marked this task priority as such:\n";
        string += "\t" + taskToMark;
        return string;
    }

    /**
     * Prints a done message for the given Task.
     * @param doneTask Task that is marked as completed.
     */
    public String printDoneMessage(Task doneTask) {
        String string;
        string = "Nice! I've marked this task as done:\n";
        string += "\t" + doneTask;
        return string;
    }

    /**
     * Print an add message for the given Task.
     * @param newTask Task that has just been added.
     * @param taskList current TaskList.
     */
    public String printAddMessage(Task newTask, TaskList taskList) {
        String string;
        string = "Got it. I've added this task:\n";
        string += "\t" + newTask + "\n";
        string += "Now you have " + String.valueOf(taskList.size()) + " tasks in your list.";
        return string;
    }

    /**
     * Prints a delete message for the deleted Task.
     * @param deletedTask Task that was deleted.
     * @param taskList current TaskList.
     */
    public String printDeleteMessage(Task deletedTask, TaskList taskList) {
        String string;
        string = "Noted. I've removed this task:\n";
        string += "\t" + deletedTask + "\n";
        string += "Now you have " + String.valueOf(taskList.size()) + " tasks in the list.";
        return string;
    }

    /**
     * Prints an exit message when duke.Duke exists.
     */
    public String printExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints an error message when file is not loaded.
     */
    public String showLoadingError() {
        return "Unable to load file. New file is created.";
    }

    /**
     * Prints an error message.
     * @param errorMessage error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }
}
