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

        this.lines = "    ____________________________________________________________";
        this.defaultGreeting = this.lines + "\n" + "     Hello! I'm Chatty Bot ^~^ \n" + "     "
                + "What can I do for you?\n" + "     Type 'help' if you wish to know more about"
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
     * Prints welcome message to user.
     *
     */
    public void printWelcome() {
        System.out.println(defaultGreeting);
    }

    /**
     * Prints UI message when loading storage files.
     *
     */
    public void loadFile() {
        System.out.println(this.lines);
        System.out.println("     Previously saved list (if any) loaded. You may enter your commands now:");
        System.out.println(this.lines);
    }

    /**
     * Prints UI message when adding a new directory to store bot data.
     *
     */
    public static void addDirectory() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     I see that you do not have a directory to store data. "
                + "Created one for you before we proceed.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints UI message when addign a new data file to store bot data.
     *
     */
    public static void addDataFile() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     I see that this is your first time using Chatty Bot, "
                + "I have created a file to log your history from now on!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints UI message when a task is marked as done.
     *
     * @param taskIndex Index of task marked as done
     * @param currList Task list with updated list of tasks.
     */
    public void markAsDone(int taskIndex, TaskList currList) {
        System.out.println(this.lines);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + currList.get(taskIndex));
        System.out.println(this.lines);
    }

    /**
     * Prints UI message when a task has been added to the task list.
     *
     * @param newTask Task added.
     * @param currList Task list with updated list of tasks.
     */
    public void addTask(Task newTask, TaskList currList) {
        System.out.println(this.lines + "\n" + "     Got it. I've added this task:");
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + String.valueOf(currList.getNumTask())
                + " task(s) in the list.");
        System.out.println(this.lines);
    }

    /**
     * Prints UI message to list out tasks in the current task list.
     *
     * @param currList Task list with updated list of tasks.
     */
    public void listItems(TaskList currList) {
        System.out.println(this.lines);
        System.out.println("     Here are the tasks in your list:");
        currList.listItems();
        System.out.println(this.lines);
    }

    /**
     * Prints UI message when a task is removed.
     *
     * @param removeTask Removed task.
     * @param currList Task list with updated list of tasks.
     */
    public void deleteTask(Task removeTask, TaskList currList) {
        System.out.println(this.lines);
        System.out.println("     Alright, the following task has been removed");
        System.out.println("     " + removeTask);
        System.out.println("     Now you have " + String.valueOf(currList.getNumTask())
                + " task(s) in the list.");
        System.out.println(this.lines);
    }

    /**
     * Prints UI message when users input an invalid command.
     *
     * @param ex Relevant invalid command message.
     */
    public static void commandError(InvalidCommand ex) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + ex);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints UI message when users close the bot.
     *
     */
    public void goodBye() {
        String endGreeting = this.lines + "\n" + "     Bye. Hope to see you again soon!\n" + this.lines;
        System.out.println(endGreeting);
    }

    /**
     * Prints UI message after tasks are found using a keyword.
     *
     * @param foundTasks Task list with tasks found.
     * @param keyword Keyword used to find tasks.
     */
    public void printFoundItems(TaskList foundTasks, String keyword) {
        System.out.println(this.lines);
        System.out.println("     Here are the matching tasks for keyword \"" + keyword + "\" in your list:");
        foundTasks.listItems();
        System.out.println(lines);
    }

    /**
     * Prints help message to user.
     *
     */
    public void helpMessage() {
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
        System.out.println(lines + "\n" + helpMessage);
        System.out.println(addTasksInstructions + addToDoInstructions + "\n" + addDeadlineInstructions);
        System.out.println(addEventInstructions + "\n" + deleteTaskInstructions);
        System.out.println(listTasksInstructions + "\n" + findTasksInstructions);
        System.out.println(markTaskAsDoneInstructions + "\n" + exitBotInstructions + "\n" + lines);
    }
}
