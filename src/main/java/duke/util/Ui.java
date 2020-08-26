package duke.util;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * This class handles the user-specific inputs and outputs.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructs a new UI object, and displays a welcome message.
     */
    public Ui() {
        intro();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays output messages enclosed in top and bottom horizontal lines.
     *
     * @param message message to be output.
     */
    private static void println(String... message) {
        System.out.println("\t____________________________________________________________");
        for (String s : message) {
            System.out.println("\t" + s);
        }
        System.out.println("\t____________________________________________________________");
    }

    private void intro() {
        println("Hello! I'm KING!", "Let me load the stored file ~~ ");
    }

    /**
     * Displays the page where retrieving from data file is successful and prints the list of task
     *      currently within the task list.
     *
     * @param taskList The list of tasks.
     */
    public void showLoadSuccess(TaskList taskList) {
        printList(taskList);
    }

    /**
     * Displays the page where retrieving from data file is not successful.
     */
    public void showLoadingError() {
        println("I'm unable to retrieve the stored file.");
    }

    /**
     * Displays the list of task currently in task list.
     *
     * @param taskList The list of tasks.
     */
    public void printList(TaskList taskList) {
        if (taskList.size() == 0) {
            println("I can't find any task in your list...",
                    "Try adding some task using \"todo\", \"deadline\" and \"event\" command");
            return;
        }

        String[] output = new String[taskList.size() + 1];
        output[0] = " Here are the tasks in your list:";

        for (int i = 1; i <= taskList.size(); i++) {
            output[i] = i + "." + taskList.get(i-1);
        }

        println(output);
    }

    /**
     * Displays the exit page.
     */
    public void showExit() {
        println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the page where addition of task is successful.
     *
     * @param task The task newly added into the list.
     * @param size The size of the task list after adding.
     */
    public void showAddTask(Task task, int size) {
        println(
                "Got it. I've added this task: ", task.toString(),
                "Now you have " + size + " tasks in the list."
        );
    }

    /**
     * Displays the page where removal of task is successful.
     *
     * @param task The task to be removed from the list.
     * @param size The size of the task list after removal.
     */
    public void showRemoveTask(Task task, int size) {
        println( "Noted. I've removed this task: ", task.toString(),
                "Now you have " + size + " tasks in the list." );
    }

    /**
     * Displays the page where a task has been marked as completed.
     *
     * @param task The task that was marked completed.
     */
    public void showDone(Task task) {
        println("Nice! I've marked this task as done: ", task.toString());
    }

    /**
     * Displays the error message to the user's console.
     *
     * @param de The exception encountered during execution.
     */
    public void printError(DukeException de) {
        println(de.getMessage());
    }

    /**
     * Reads the user input.
     *
     * @return The user's input as String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public void printSearchResult(String searchTerm, List<Integer> idxList, List<Task> tasks) {
        if (tasks.size() == 0) {
            println("I can't find any task named \"" + searchTerm + "\" in the list...");
            return;
        }

        String[] output = new String[tasks.size() + 1];
        output[0] = " Here are the matching tasks according to your search keyword: \"" + searchTerm + "\"";

        for (int i = 1; i <= tasks.size(); i++) {
            output[i] = idxList.get(i-1) + "." + tasks.get(i-1);
        }

        println(output);
    }
}
