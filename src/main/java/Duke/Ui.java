package duke;

import java.util.List;

/**
 * Encapsulates the user interaction.
 */
public class Ui {
    private String line = "    ____________________________________________________________";

    /**
     * Displays the loading error.
     * @return String result for GUI
     */
    public String showLoadingError() {
        return "Failed to load the file.\n";
    }

    /**
     * Displays the number format error.
     * @return String result for GUI
     */
    public String showNumberFormatError() {
        return line + "\n      OOPS!!! Please enter a number\n" + line + "\n";
    }

    /**
     * Displays the potential error when running the Duke bot.
     * @param e an exception object which contains the error message.
     * @return String result for GUI
     */
    public String showDukeError(DukeException e) {
        return line + "\n" + e.toString().substring(19) + "\n" + line + "\n";
    }

    /**
     * Displays the file not found error.
     * @return String result for GUI
     */
    public String showFileNotFoundError() {
        return line + "\n      OOPS!!! File is not found.\n" + line + "\n";
    }

    /**
     * Displays the list of the todo events.
     * @param data the list of the todo events.
     * @return String result for GUI
     */
    public String printList(List<Task> data) {
        String res = "";
        res += line + "\n";
        res += "     Here are the tasks in your list:\n";
        for (int i = 0; i < data.size(); i++) {
            res += "     " + (i + 1) + "." + data.get(i).toString() + "\n";
        }
        res += line + "\n";
        return res;
    }

    /**
     * Displays the message to inform the success of Done operation.
     * @param data the list of the events.
     * @param n the label of the event that will set to be done.
     * @return String result for GUI
     */
    public String printDone(List<Task> data, int n) {
        return line + "\n" + "    Nice! I've marked this task as done: \n     "
                + data.get(n).toString() + "\n" + line + "\n";
    }

    /**
     * Displays the first part of the message to inform the success of Delete operation.
     * @param data the list of the events.
     * @param n the label of the event that will be deleted.
     * @return String result for GUI
     */
    public String printDeletePre(List<Task> data, int n) {
        return line + "\n" + "     Noted. I've removed this task: \n     "
                + data.get(n).toString() + "\n";
    }

    /**
     * Displays the second part of the message to inform the success of Delete operation.
     * @param data the list of the events.
     * @param n the label of the event that will be deleted.
     * @return String result for GUI
     */
    public String printDeletePost(List<Task> data, int n) {
        return "     Now you have " + data.size() + " tasks in the list.\n"
            + line + "\n";
    }

    /**
     * Displays the message to inform the success of adding task operation.
     * @param data the list of the task.
     * @param t the task that will be added.
     * @return String result for GUI.
     */
    public String printTask(List<Task> data, Task t) {
        return line + "\n" + "     Got it. I've added this task: \n       " + t.toString() + "\n"
                + "     Now you have " + data.size() + " tasks in the list.\n" + line + "\n";
    }

    /**
     * Displays the result of all the tasks that contains the keyword.
     * @param data the list of the events.
     * @return String result for GUI
     */
    public String printFind(List<Task> data) {
        String res = "";
        res += line + "\n";
        res += "     Here are the matching tasks in your list:\n";
        for (int i = 0; i < data.size(); i++) {
            res += "     " + (i + 1) + "." + data.get(i).toString() + "\n";
        }
        res += line + "\n";
        return res;
    }
}
