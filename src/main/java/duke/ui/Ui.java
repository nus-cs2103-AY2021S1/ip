package duke.ui;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class to display messages and interact with user.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Initialises a new Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays greeting message to user.
     *
     * @return Greeting message to user.
     */
    public static String sayGreeting() {
        String greetingMessage = "Hi there, I'm TARS!\nWhat can I do for you today?";
        return greetingMessage;
    }

    /**
     * Displays farewell message to user.
     */
    public String sayFarewell() {
        String farewellMessage = "Bye bye. See you again soon!";
        return farewellMessage;
    }

    /**
     * Reads a command from user.
     *
     * @return Command read from user.
     */
    public String getNextLine() {
        return sc.nextLine();
    }

    /**
     * Display an error message from a DukeException to user.
     *
     * @param e Exception containing the error message.
     * @return
     */
    public String showErrorMsg(DukeException e) {
        return e.getMessage();
    }

    /**
     * Display an error message from a String to user.
     *
     * @param errMessage Exception containing the error message.
     * @return
     */
    public String showErrorMsg(String errMessage) {
        return errMessage;
    }

    /**
     * Display a message to notify user of existing file loaded.
     * @return
     */
    public static String fileLoaded() {
        String fileLoadedMessage = "I found your saved tasks. You can view them using list.";
        return fileLoadedMessage;
    }

    /**
     * Display a message to notify user a new file has been created.
     */
    public static String newFileCreated() {
        String fileCreatedMessage = "I have created a new log file to help me remember what you tell me!";
        return fileCreatedMessage;
    }

    /**
     * Displays taskAdded message to user.
     *  @param newTask New Task to be added to TaskList.
     * @param taskList Current list of Tasks from user.
     * @return
     */
    public String taskAdded(Task newTask, TaskList taskList) {
        String taskAddedMessage = "Got it. I've added this task:\n"
                + newTask + "\nNow you have "
                + taskList.numTask() + " task(s) in the list.";
        return taskAddedMessage;
    }

    /**
     * Displays taskDeleted message to user.
     *
     * @param removeTask Task to be removed from TaskList.
     * @param taskList Current list of Tasks from user.
     */
    public String taskDeleted(Task removeTask, TaskList taskList) {
        String taskDeletedMessage = "Poof! I've removed this task:\n"
                + removeTask + "\nNow you have "
                + taskList.numTask() + " task(s) in the list.";
        return taskDeletedMessage;
    }

    /**
     * Displays markAsDone message to user.
     *
     * @param index Index of the Task from TaskList to be marked as done.
     * @param taskList Current list of Tasks from user.
     */
    public String markAsDone(int index, TaskList taskList) {
        String markedDoneMessage = "Nice! I've marked this task as done:\n" + taskList.get(index);
        return markedDoneMessage;
    }

    /**
     * Displays a list of Tasks from the TaskList.
     *
     * @param taskList Current list of Tasks from user.
     */
    public String listTasks(TaskList taskList) {
        String listOfTasks = "Let's see what we have here:\n"
                + taskList.list();
        return listOfTasks;
    }

    /**
     * Displays a list of Tasks from the TaskList that matches the keywords.
     *
     * @param foundTasks List of Tasks that match the keywords given by user.
     * @param keyword Keyword to be used in finding.
     */
    public String FoundItems(TaskList foundTasks, String keyword) {
        String listOfFoundTasks = "Here are the tasks that matched with \""
                + keyword + "\" in your " + "list:\n" + foundTasks.list();
        return listOfFoundTasks;
    }

}
