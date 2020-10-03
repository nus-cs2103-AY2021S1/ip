package dd.ui;

import dd.tasks.Task;

/**
 * A Ui deals with interaction with the user and prints out
 * appropriate statements.
 */
public class Ui {
    private final String smileyLine = "*********************"
            + "*****************************";

    /**
     * Indicate data created at given file name.
     *
     * @param fName File name that data is created in.
     * @return String that indicates file is created.
     */
    public String dataCreate(String fName) {
        return "New data created: " + fName;
    }

    /**
     * Indicate data already exists.
     *
     * @return String that indicates data exists.
     */
    public String dataExists() {
        return "Data already exists.";
    }

    /**
     * Returns exit output.
     *
     * @return DD exit string.
     */
    public String exit() {
        return "You're leaving? Bye :( Come back soon!";
    }

    /**
     * Returns greeting output.
     *
     * @return DD greeting string.
     */
    public String greeting() {
        return "Hi! I'm Timmi, your personal Task Manager!\n"
                + "Type 'help' for a list of possible commands!";
    }

    /**
     * Returns all possible user commands.
     *
     * @return Possible user commands for user.
     */
    public String printAllCommands() {
        String lineBreak = "----------------------------------------------------------"
                + "-------------------------------------------------------\n";
        return "Here are all the possible commands and how to use them:\n"
                + smileyLine + "\n"
                + "   To add a todo, enter: \"todo <item name>\". \n"
                + "     For example: \"todo borrow book\". \n"
                + "     Commas( , ) are not allowed in item name.\n"
                + lineBreak
                + "   To add a deadline, enter: \"deadline <item name> /by <date> <(OPTIONAL) time>\". \n"
                + "     For example: \"deadline return book /by 31-12-2020\". \n"
                + "     Commas( , ) are not allowed in item name.\n"
                + lineBreak
                + "   To add an event, enter: \"event <item name> /at <date> <(OPTIONAL) time>\". \n"
                + "     For example: \"event team meeting /at 31-12-2020 1800\". \n"
                + "     Commas( , ) are not allowed in item name.\n"
                + lineBreak
                + "   To check your list of tasks, enter: \"list\". \n"
                + lineBreak
                + "   To check your list of tasks on a particular date, enter: \"check <date>\". \n"
                + lineBreak
                + "   To find list of tasks related to a word, enter: \"find <your query word>\". \n"
                + lineBreak
                + "   To mark a task as done, enter: \"done <task number in list>\". \n"
                + lineBreak
                + "   To delete a task, enter: \"delete <task number in list>\". \n"
                + lineBreak
                + "   And if you want to leave and save your data, you can just say \"bye\" I guess......\n";
    }

    /**
     * Indicate task given is deleted.
     *
     * @param t Task that is deleted.
     * @return String that confirms deleted task.
     */
    public String printDeletedTask(Task t) {
        return "Alright! I've deleted the task:\n  " + t;
    }

    /**
     * Indicate task given is marked as done.
     *
     * @param t Task that is marked as done.
     * @return String that confirms task that is mark as done.
     */
    public String printDoneTask(Task t) {
        return "Wow!! Good job!!\n  " + t;
    }

    /**
     * Returns string for listing of task according to task index and Task given.
     *
     * @param taskIndex Index of task.
     * @param t Task to be printed.
     * @return String of task details.
     */
    public String printTask(int taskIndex, Task t) {
        return taskIndex + ". " + t;
    }

    /**
     * Indicates number of tasks in list.
     *
     * @param taskSize Number of tasks in list.
     * @return String to indicate the number of tasks in list.
     */
    public String printTasksSize(int taskSize) {
        return "You now have " + taskSize + " task(s) in your list!";
    }

    /**
     * Returns error message given.
     *
     * @param msg Error message to be printed.
     * @return Error message.
     */
    public String showError(String msg) {
        return msg;
    }

    /**
     * Indicates loading error.
     *
     * @return String to show that loading error occurred.
     */
    public String showLoadingError() {
        return "An error occurred, unable to load prior data. New list created.";
    }

    /**
     * Indicate to-do given is added.
     *
     * @param t To-do that is added.
     * @return String to confirm to-do task added.
     */
    public String startAddTodo(Task t) {
        return "Ok, To-do added:\n  " + t;
    }

    /**
     * Indicate deadline given is added.
     *
     * @param t Deadline that is added.
     * @return String to confirm deadline task added.
     */
    public String startAddDeadline(Task t) {
        return "Ok, Deadline added:\n  " + t;
    }

    /**
     * Indicate event given is added.
     *
     * @param t Event that is added.
     * @return String to confirm event task added.
     */
    public String startAddEvent(Task t) {
        return "Ok, Event added:\n  " + t;
    }

    /**
     * Indicate date that is being checked.
     *
     * @param date Date that is being checked.
     * @return String to confirm date checked.
     */
    public String startCheckDate(String date) {
        return "Here is your list of task(s) on " + date + ":\n"
                + smileyLine;
    }

    /**
     * Indicate description that is being checked.
     *
     * @param desc Description that is being checked.
     * @return String to confirm description being checked.
     */
    public String startCheckDesc(String desc) {
        return "Here is the list of task(s) related to " + desc + ":\n"
                + smileyLine;
    }

    /**
     * Indicate start of listing of tasks.
     *
     * @return String to show the start of list.
     */
    public String startList() {
        return "Here is your current list of task(s)!\n"
                + smileyLine;
    }

    /**
     * Indicate data is updated.
     *
     * @return String to indicate that data is updated.
     */
    public String updateData() {
        return "Updated your data!";
    }
}
