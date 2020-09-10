/**
 * Represents the user interface that interacts with the user by replying
 * the user accordingly. A Ui objects contains a Scanner object that helps
 * to take in user input.
 */
public class Ui {
    /**
     * Instantiates a Ui object with a scanner object.
     */
    public Ui() { }

    /**
     * Displays the welcome message.
     */
    public static String showWelcome() {
        String welcomeMessage = "HOWDY!!!!! WELCOME TO BIKINI BOTTOM! "
                + "I'm Sandy Cheeks, what can I do for yer?\n"
                + "Try typin' \"help\" to see what commands you can use!";
        return welcomeMessage;
    }

    /**
     * Displays the exit message.
     */
    public String showExit() {
        String exitMessage = "You're leavin' already?!??! Well, see you again! "
                + "BYEEE!!!";
        return exitMessage;
    }

    /**
     * Displays the list of commands available.
     */
    public String showHelp() {
        String helpMessage = "I don't know nothin' about other commands but "
                + "here are the list of commands I understand!\n"
                + "help: displays the list of commands available\n"
                + "\n"
                + "list: displays the list of tasks you have\n"
                + "\n"
                + "find *keyword*: displays the tasks with that keyword\n"
                + "eg find karate\n"
                + "\n"
                + "todo *task description*: adds a task without any\n"
                + "date/time attached to it\n" + "eg todo scold spongebob\n"
                + "\n"
                + "deadline *task description* /by *date+time*: adds a\n"
                + "task that needs to be done before a specific date and time\n"
                + "(date and time to be written in yyyy-mm-dd HHMM format)\n"
                + "eg deadline build spaceship /by 2019-10-15 2359\n"
                + "\n"
                + "event *task description* /at *date+time*: adds a task that\n"
                + "starts at a specific time and ends at a specific time\n"
                + "(date and time to be written in yyyy-mm-dd HHMM format)\n"
                + "eg karate competition /at 2019-10-15 1200\n"
                + "\n"
                + "done *task number*: marks the task with that number as done\n"
                + "eg done 1\n"
                + "\n"
                + "delete *task number*: deletes the task with that number from the list\n"
                + "eg delete 1\n"
                + "\n"
                + "bye: ends the session";
        return helpMessage;
    }

    /**
     * Displays the user's list of tasks.
     */
    public String showList() {
        String listMessage = "Here yer go! These are all your tasks!";
        return listMessage;
    }

    /**
     * Replies the user's command to mark a task as done.
     * @param t Task that is marked as done.
     */
    public String showDone(Task t) {
        String doneMessage = "YEEEEE-HAW!!! You've completed this task!\n" + t;
        return doneMessage;
    }

    /**
     * Replies the user's command to delete a task.
     * @param t Task that is deleted.
     */
    public String showDelete(Task t, TaskList tasks) {
        String deleteMessage = "Got it! I'm removin' this task!\n" + t + "\n"
                + "Now you have " + tasks.getSize() + " tasks in your list!";
        return deleteMessage;
    }

    /**
     * Replies the user's command to add a task.
     * @param t Task to be added.
     */
    public String showAdd(Task t, TaskList tasks) {
        String addMessage = "Ain't no problem! I'm addin' this task:\n" + t + "\n"
                + "Now you have " + tasks.getSize() + " tasks in your list!";
        return addMessage;
    }

    /**
     * Displays the matching tasks found using the keyword.
     */
    public String showFind() {
        String findMessage = "Ain't no problem! I have found the matchin' tasks in your list:";
        return findMessage;
    }

    /**
     * Displays the error message.
     * @param errorMessage Error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

}
