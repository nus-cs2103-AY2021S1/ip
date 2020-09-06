/**
 * Encapsulates a User Interface
 * Deals with interactions with the user
 */

public class Ui {

    protected static final String LINE = "______________________________________________________";

    /**
     * Returns the farewell message
     *
     * @return String
     */
    protected String farewell() {
        return this.LINE + "\n" + "Bye. Hope to see you again soon!" + "\n" + this.LINE;
    }

    /**
     * Returns a line in String form
     *
     * @return String of lines
     */
    protected String lines() {
        return this.LINE;
    }

    /**
     * Returns the message when TaskList is shown in String form
     *
     * @return String
     */
    protected String listMsg() {
        return "Here are the tasks in your list:";
    }

    /**
     * Returns the message when a Task is marked as done
     *
     * @return String
     */
    protected String markAsDoneMsg(Task chosen) {
        return this.LINE + "\n"
                + "Nice! I've marked this task as done: " + "\n"
                + chosen + "\n"
                + this.LINE;
    }

    /**
     * Prints out the message when a Task is deleted
     */
    protected String returnDeleteMsg(int i, Task chosen) {
        return this.LINE + "\n"
                            + " Noted. I've removed this task: " + "\n"
                            + chosen + "\n"
                            + " Now you have " + i + " tasks in the list." + "\n"
                            + this.LINE;
    }

    /**
     * Returns the message when a Task is added to the TaskList
     *
     * @return String message
     */
    protected String addTaskToTasklistMsg(Task task, int i) {
        return this.LINE + "\n"
                    + " Got it. I've added this task: " + "\n"
                    + "  " + task + "\n"
                    + " Now you have " + i + " tasks in the list." + "\n"
                    + this.LINE;
    }

    /**
     * Returns the message when results are found in String form
     *
     * @return String
     */
    protected String findMsg() {
        return " Here are the matching tasks in your list:";
    }

}
