/**
 * Encapsulates a User Interface
 * Deals with interactions with the user
 */

public class Ui {


    /**
     * Returns the farewell message
     *
     * @return String
     */
    protected String farewell() {
        return "Bye. Hope to see you again soon!";
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
        return "Nice! I've marked this task as done: " + "\n"
                + chosen;
    }

    /**
     * Prints out the message when a Task is deleted
     */
    protected String returnDeleteMsg(int i, Task chosen) {
        return " Noted. I've removed this task: " + "\n"
                            + chosen + "\n"
                            + " Now you have " + i + " tasks in the list.";
    }

    /**
     * Returns the message when a Task is added to the TaskList
     *
     * @return String message
     */
    protected String addTaskToTasklistMsg(Task task, int i) {
        return " Got it. I've added this task: " + "\n"
                    + "  " + task + "\n"
                    + " Now you have " + i + " tasks in the list.";
    }

    /**
     * Returns the message when results are found in String form
     *
     * @return String
     */
    protected String findMsg() {
        return "Here are the matching tasks in your list:";
    }

    /**
     * Returns the message when dupes are found and removed
     *
     * @return String
     */
    protected String removedDupes() {
        return "All duplicates have been removed.";
    }

    /**
     * Returns the message when there are no dupes
     *
     * @return String
     */
    protected String noDupes() {
        return "No dupes were found.";
    }

}
