package duke;

import java.util.Scanner;

import duke.exception.InvalidCommand;
import duke.parser.Parser;
import duke.tasks.Task;

/**
 * Represents UI engine for the bot.
 */
public class Ui {
    private static final Scanner sc = new Scanner(System.in);
    private String logo;
    private String lines;
    private String defaultGreeting;
    private Parser commandParser;

    /**
     * Creates a UI object with storage object and current task list.
     *
     * @param listStorage Storage for bot data.
     * @param taskList Task list with current tasks.
     */
    public Ui(Storage listStorage, TaskList taskList) {
        this.logo = " .d8888b.  888               888    888                  888888b.            888    \n"
                + "d88P  Y88b 888               888    888                  888  \"88b           888    \n"
                + "888    888 888               888    888                  888  .88P           888    \n"
                + "888        88888b.   8888b.  888888 888888 888  888      8888888K.   .d88b.  888888 \n"
                + "888        888 \"88b     \"88b 888    888    888  888      888  \"Y88b d88\"\"88b 888    "
                + "\n" + "888    888 888  888 .d888888 888    888    888  888      888    888 888  888 888  "
                + "  \n" + "Y88b  d88P 888  888 888  888 Y88b.  Y88b.  Y88b 888      "
                + "888   d88P Y88..88P Y88b. " + " \n" + " \"Y8888P\"  888  888 \"Y888888  \"Y888  \"Y888  "
                + "\"Y88888      8888888P\"   " + "\"Y88P\"   " + "\"Y888 \n" + "                   "
                + "                             888" + "                            " + "     " + "\n" + "  "
                + "                                       " + "  Y8b d88P                                 \n"
                + "                                       "
                + "     \"Y88P\"                                  ";

        this.lines = "______________________________________________________";
        this.defaultGreeting = this.lines + "\n" + "     Hello! I'm Chatty Bot ^~^ \n"
                + "     What can I do for you?\n" + "Type 'help' if you wish to know more about"
                + " what I can do!" + "\n" + lines + "\n";
        this.commandParser = new Parser();
    }

    /**
     * Gets and returns user inputs to be parsed.
     *
     * @return String to be parse
     */
    public String getInput() {
        String nextLine = sc.nextLine();
        return nextLine;
    }

    /**
     * Returns welcome message to user.
     *
     * @return Welcome message.
     */
    public String printWelcome() {
        return this.defaultGreeting;
    }

    /**
     * Returns UI message when loading storage files.
     *
     * @return Load files message.
     */
    public String loadFile() {
        String loadFileMessage = this.lines + "\n" + "     Previously saved list (if any) loaded."
                + " You may enter your commands now:" + "\n" + this.lines;
        return loadFileMessage;
    }

    /**
     * Returns UI message when adding a new directory to store bot data.
     *
     * @return Directory added message.
     */
    public static String addDirectory() {
        String addDirectorymessage = "______________________________________________________"
                + "\n" + "     I see that you do not have a directory to store data. "
                + "Created one for you before we proceed." + "\n"
                + "______________________________________________________";
        return addDirectorymessage;
    }

    /**
     * Returns UI message when adding a new data file to store bot data.
     *
     * @return Adds data file message.
     */
    public static String addDataFile() {
        String addDataFileMessage = "______________________________________________________" + "\n"
                + "     I see that this is your first time using Chatty Bot, "
                + "I have created a file to log your history from now on!" + "\n"
                + "______________________________________________________";
        return addDataFileMessage;
    }

    /**
     * Returns UI message when a task is marked as done.
     *
     * @param taskIndex Index of task marked as done
     * @param currList Task list with updated list of tasks.
     * @return UI message for done task.
     */
    public String markAsDone(int taskIndex, TaskList currList) {
        assert currList.contains(currList.get(taskIndex)) : "Something went wrong while marking as done!";
        String markedDoneMessage = this.lines + "\n" + "     Nice! I've marked this task as done:"
                + "\n" + "       " + currList.get(taskIndex) + "\n" + this.lines;
        return markedDoneMessage;
    }

    /**
     * Returns UI message when a task has been added to the task list.
     *
     * @param newTask Task added.
     * @param currList Task list with updated list of tasks.
     * @return UI message for task added.
     */
    public String addTask(Task newTask, TaskList currList) {
        assert currList.getNumTask() >= 0 : "Something went wrong in your task list!";
        String taskAddedUi = this.lines + "\n" + "     Got it. I've added this task:" + "\n" + "       "
                + newTask + "\n" + "     Now you have " + String.valueOf(currList.getNumTask())
                + " task(s) in the list." + "\n" + this.lines;
        return taskAddedUi;
    }

    /**
     * Returns UI message to list out tasks in the current task list.
     *
     * @param currList Task list with updated list of tasks.
     * @return UI message of task list.
     */
    public String listItems(TaskList currList) {
        String listInString = this.lines + "\n" + "     Here are the tasks in your list:" + "\n"
                + currList.listItems() + this.lines + "\n";
        return listInString;
    }

    /**
     * Returns UI message when a task is removed.
     *
     * @param removeTask Removed task.
     * @param currList Task list with updated list of tasks.
     * @return UI message of removed task.
     */
    public String deleteTask(Task removeTask, TaskList currList) {
        String deleteMessage = this.lines + "\n" + "     Alright, the following task has been removed" + "\n"
                + "       " + removeTask + "\n" + "     Now you have " + String.valueOf(currList.getNumTask())
                + " task(s) in the list." + "\n" + this.lines;
        return deleteMessage;
    }

    /**
     * Returns UI message when users input an invalid command.
     *
     * @param ex Relevant invalid command message.
     * @return String representation of invalid action attempted.
     */
    public static String commandError(InvalidCommand ex) {
        String invalidCommandMessage = "______________________________________________________" + "\n"
                + "     " + ex + "\n" + "______________________________________________________";
        return invalidCommandMessage;
    }

    /**
     * Returns UI message when users close the bot.
     *
     * @return UI message for goodbye.
     */
    public String goodBye() {
        String endGreeting = this.lines + "\n" + "     Bye. Hope to see you again soon!\n" + this.lines;
        return endGreeting;
    }

    /**
     * Returns UI message after tasks are found using a keyword.
     * @param foundTasks Task list with tasks found.
     * @param keyword Keyword used to find tasks.
     * @return UI message for found tasks.
     */
    public String printFoundItems(TaskList foundTasks, String keyword) {
        String foundItemsMessage = this.lines + "\n" + "     Here are the matching tasks for keyword \""
                + keyword + "\" in your list:" + "\n" + foundTasks.listItems() + this.lines;
        return foundItemsMessage;
    }

    /**
     * Returns help message to user.
     *
     * @return UI help message.
     */
    public String helpMessage() {
        String helpMessage = "     Seems like you need some help! There are a few things I can do for you";
        String addTasksInstructions = "     There are three types of tasks you can insert, namely To-Do,"
                + " Deadline and Event. The instructions for each task is as follows:" + "\n";
        String addToDoInstructions = "     To add a To-Do task, write your command as todo <task name> e.g."
                + " 'todo read books' to reflect 'read books' task.";
        String addDeadlineInstructions = "     To add a Deadline task, write your command as deadline"
                + " <task name> /by <date in YYYY-MM-DD> e.g. 'deadline homework /by 2020-10-05'"
                + " to reflect homework is due on 5th October 2020.";
        String addEventInstructions = "     To add an Event task, write your command as event <task name> /at"
                + " <date in YYYY-MM-DD> e.g. 'event party /at 2020-01-01' to reflect a party event on"
                + " 1st January 2020.";
        String deleteTaskInstructions = "     If you have completed a task or add a task by mistake into the"
                + " tasklist, you may remove your task by using delete <task number> e.g. 'delete 3' to remove"
                + " task 3 on the current tasklist.";
        String listTasksInstructions = "     You can view the tasks in your task list with the"
                + " 'list' command";
        String findTasksInstructions = "     You can do a quick search of tasks in your task list using the"
                + " 'find' command in the form of done <task number>. e.g. 'find book' to find tasks"
                + " related to the command 'book'";
        String markTaskAsDoneInstructions = "     You can mark a task as done using the done command"
                + " in the form of done <task number>. e.g. 'done 3' marks the 3rd task in the task list"
                + " as done.";
        String exitBotInstructions = "     If you are done editing your task list, feel free to close down"
                + "  this bot with the bye command by typing 'bye'.";
        String helpMessageToBePrinted = this.lines + "\n" + helpMessage + "\n" + addTasksInstructions
                + addToDoInstructions + "\n" + addDeadlineInstructions + "\n" + addEventInstructions
                + "\n" + deleteTaskInstructions + "\n" + listTasksInstructions + "\n" + findTasksInstructions
                + "\n" + markTaskAsDoneInstructions + "\n" + exitBotInstructions + "\n" + lines + "\n";
        return helpMessageToBePrinted;
    }

    /**
     * Returns UI message when bot loads data from storage.
     *
     * @param loadData
     * @return UI message for loading data.
     */
    public String loadStorage(String loadData) {
        return loadData;
    }
}
