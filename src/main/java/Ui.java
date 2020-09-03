import java.util.Scanner;

/**
 * Represents the user interface that interacts with the user by replying
 * the user accordingly. A Ui objects contains a Scanner object that helps
 * to take in user input.
 */
public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "---------------------------------------------------------";
    private Scanner sc;

    /**
     * Instantiates a Ui object with a scanner object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads the input of the user.
     *
     * @return User input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the welcome message.
     */
    public static String showWelcome() {
        String welcomeMessage = "hElLoOOoOOoO! WELCOME TO BIKINI BOTTOM! "
                + "I'm Sandy Cheeks, how can I help you today?\n"
                + "Type \"help\" to view the list of commands you can use!";
        return welcomeMessage;
    }

    /**
     * Displays the exit message.
     */
    public String showExit() {
        return "BYEEE!! SEE YOU AGAIN!!! >O<";
    }

    /**
     * Displays the list of commands available.
     */
    public String showHelp() {
        String helpMessage = "Here are the list of commands you can use! =D\n"
                + "help: displays the list of commands available\n"
                + "\n"
                + "list: displays the list of tasks you have\n"
                + "\n"
                + "find *keyword*: displays the tasks with that keyword\n"
                + "eg find book\n"
                + "\n"
                + "todo *task description*: adds a task without any\n"
                + "date/time attached to it\n" + "eg todo read book\n"
                + "\n"
                + "deadline *task description* /by *date+time*: adds a\n"
                + "task that needs to be done before a specific date and time\n"
                + "(date and time to be written in yyyy-mm-dd HHMM format)\n"
                + "eg deadline return book /by 2019-10-15 2359\n"
                + "\n"
                + "event *task description* /at *date+time*: adds a task that\n"
                + "starts at a specific time and ends at a specific time\n"
                + "(date and time to be written in yyyy-mm-dd HHMM format)\n"
                + "eg event meeting /at 2019-10-15 1200\n"
                + "\n"
                + "done *task number*: marks the task with that number as\n"
                + "done eg done 1\n"
                + "\n"
                + "delete *task number*: deletes the task with that number\n"
                + "from the list eg delete 1\n"
                + "\n"
                + "bye: ends the session";
        return helpMessage;
    }

    /**
     * Displays the user's list of tasks.
     */
    public String showList() {
        return "Here are your tasks! JIAYOU! =D";
    }

    /**
     * Replies the user's command to mark a task as done.
     * @param t Task that is marked as done.
     */
    public String showDone(Task t) {
        String doneMessage = "Nicee!! You've completed this task!\n" + t;
        return doneMessage;
    }

    /**
     * Replies the user's command to delete a task.
     * @param t Task that is deleted.
     */
    public String showDelete(Task t, TaskList tasks) {
        String deleteMessage = "Oki! I've removed this task!\n" + t + "\n"
                + "Now you have " + tasks.getSize() + " tasks in your list!";
        return deleteMessage;
    }

    /**
     * Replies the user's command to add a task.
     * @param t Task to be added.
     */
    public String showAdd(Task t, TaskList tasks) {
        String addMessage = "Oki! I have added this task:\n" + t + "\n"
                + "Now you have " + tasks.getSize() + " tasks in your list!";
        return addMessage;
    }

    /**
     * Displays the matching tasks found using the keyword.
     */
    public String showFind() {
        return "Oki! I have found the matching tasks in your list:";
    }

    /**
     * Displays the error message.
     * @param err Error message.
     */
    public String showError(String err) {
        return err;
    }

    /**
     * Displays the line that acts as a border.
     */
    public String showLine() {
        return LINE;
    }
}
