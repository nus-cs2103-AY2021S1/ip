package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class to display messages and interact with user.
 */
public class Ui {
    /**
     * Initialises a new Ui object.
     */
    public Ui() {}

    /**
     * Displays greeting message to user.
     *
     * @return Greeting message to user.
     */
    public static String sayGreeting() {
        String greetingMessage = "Hi there, I'm DUKE-T!\n"
                + "What can I do for you today?\n"
                + "I can do manage todo, events and deadlines!\n"
                + "Type 'help' or '?' to find out more!";
        return greetingMessage;
    }

    /**
     * Displays farewell message to user.
     *
     * @return Farewell message to user.
     */
    public String sayFarewell() {
        String farewellMessage = "Bye bye. Will miss you!";
        return farewellMessage;
    }

    /**
     * Display an error message from a DukeException to user.
     *
     * @param e Exception containing the error message.
     * @return Error message arising from the exception.
     */
    public String showErrorMsg(DukeException e) {
        return e.getMessage();
    }

    /**
     * Display an error message from a String to user.
     *
     * @param errMessage Exception containing the error message.
     * @return Error message arising from the exception.
     */
    public String showErrorMsg(String errMessage) {
        return errMessage;
    }

    /**
     * Display a message to notify user of existing file loaded.
     *
     * @return Existing file loaded message to user.
     */
    public static String fileLoaded() {
        String fileLoadedMessage = "I found your saved tasks. You can view them using list.";
        return fileLoadedMessage;
    }

    /**
     * Display a message to notify user a new file has been created.
     *
     * @return New file created message to user.
     */
    public static String newFileCreated() {
        String fileCreatedMessage = "I have created a new log file to help me remember what you tell me!";
        return fileCreatedMessage;
    }

    /**
     * Displays taskAdded message to user.
     *
     * @param newTask New Task to be added to TaskList.
     * @param taskList Current list of Tasks from user.
     * @return Task has been added message to user.
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
     * @return Task has been deleted message to user.
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
     * @return Task has been marked as done message to user.
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
     * @return List of Tasks in the TaskList.
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
     * @return List of Tasks with the keyword found in the TaskList.
     */
    public String foundItems(TaskList foundTasks, String keyword) {
        String listOfFoundTasks = "Here are the tasks that matched with \""
                + keyword + "\" in your " + "list:\n" + foundTasks.list();
        return listOfFoundTasks;
    }

    /**
     * Displays help message to guide user.
     *
     * @return UI Message for help.
     */
    public String helpMessage() {
        String helpMessage = "Oh look who's here! Read all about the commands you can give here!\n\n";
        String addTaskGuide = "There are 3 types of tasks you can add: ToDos, Deadlines and Events\n\n"
                + "Let me show you an example for each of them:\n";
        String addToDoGuide = "To add a ToDo, type in your command as:\n"
                + " todo <task description>\n"
                + "Eg. 'todo throw rubbish'\n\n";
        String addDeadlineGuide = "To add a Deadline, type in your command as:\n"
                + "deadline <task description> /by <date in YYYY-MM-DD format>\n"
                + "Eg. 'deadline math quiz /by 10-10-2020'\n\n";
        String addEventGuide = "To add an Event, type in your command as:\n"
                + "event <task description> /at <date in YYYY-MM-DD format>\n"
                + "Eg. 'event meeting /at 2020-11-11'\n\n";
        String markDoneGuide = "To mark a task as completed, type in:\n"
                + "done <task number>\n"
                + "Eg. 'done 1'\n\n";
        String deleteTaskGuide = "To remove any task, type in:\n"
                + "delete <task number>\n"
                + "Eg. 'delete 2'\n\n";
        String listTaskGuide = "To view the current list of tasks, type in:\n"
                + "'list'\n\n";
        String findTaskGuide = "To find a task containing a certain keyword, type in:\n"
                + "'find <keyword>'\n"
                + "Eg. 'find meeting'\n\n";
        String exitGuide = "To say byebye, type in:\n"
                + "'bye'\n";

        String helpMessageToDisplay = helpMessage + addTaskGuide + addToDoGuide
                + addDeadlineGuide + addEventGuide + markDoneGuide + deleteTaskGuide + listTaskGuide
                + findTaskGuide + exitGuide;
        return helpMessageToDisplay;
    }

}
