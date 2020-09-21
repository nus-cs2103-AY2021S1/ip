package duke.ui;

import java.util.Scanner;

import duke.command.HelpCommand;
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
        String greetingMessage = "Hi there, I'm TARS!\nWhat can I do for you today?\nType 'help' or '?' to " +
                "look at the guides!";
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
        boolean taskExist = false;
        if (taskList.get(index) != null) {
            taskExist = true;
        }
        assert taskExist : "Current task could not be marked as done!";
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

    /**
     * Displays help message to guide user.
     *
     * @return UI Message for help/
     */
    public String helpMessage() {
        String helpMessage = "Oh look who's here! Read all about the commands you can give here!\n";
        String userGuideLink = "A detailed overview can be found here: https://github" +
                ".com/jeffreytjs/ip/tree/master/docs#features\n";
        String addTaskGuide = "There are 3 types of tasks you can add: ToDos, Deadlines and Events. Let" +
                " me show you an example for each of them:\n";
        String addToDoGuide = "To add a ToDo, type in:\n"
                + " todo <task description>\n"
                + "Eg. 'todo throw rubbish' : This adds a task to throw rubbish.\n";
        String addDeadlineGuide = "To add a Deadline, type in your command:\n"
                + "deadline <task description> /by <date in YYYY-MM-DD format>\n"
                + "Eg. 'deadline math quiz /by 2020-10-10' : This adds a math quiz that is due on 10th " +
                "October 2020.\n";
        String addEventGuide = "To add an Event, type in:\n"
                + "event <task description> /at <date in YYYY-MM-DD format>\n"
                + "Eg. 'event meeting /at 2020-11-11' : This adds an event meeting that is on 11th November" +
                " 2020.\n";
        String markDoneGuide = "To mark a task as completed, type in:\n"
                + "done <task number>\n"
                + "'done 1' : This marks task 1 from current list of tasks as done.\n";
        String deleteTaskGuide = "To remove any task, type in:\n"
                + "delete <task number>\n"
                + "Eg. 'delete 2' : This deletes task 2 from the current list of tasks.\n";
        String listTaskGuide = "To view the current list of tasks, type in:\n"
                + "'list' command\n";
        String findTaskGuide = "To find a task containing a certain keyword, type in:\n"
                + "'find <keyword>'\n"
                + "Eg. 'find meeting' : This displays all tasks that have 'meeting' in their description.\n";
        String exitGuide = "To shut down the bot, type in:\n"
                + "'bye' command\n";

        String helpMessageToDisplay = helpMessage + userGuideLink + addTaskGuide + addToDoGuide
                + addDeadlineGuide + addEventGuide + markDoneGuide + deleteTaskGuide + listTaskGuide
                + findTaskGuide + exitGuide;
            return helpMessageToDisplay;
    }

}
