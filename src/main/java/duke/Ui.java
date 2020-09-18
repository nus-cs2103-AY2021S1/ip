package duke;

import java.time.LocalDate;
import java.util.List;

import duke.task.Task;

/**
 * Stores all User Interface related methods and objects.
 */
public class Ui {

    private StringBuilder responseMessage = new StringBuilder("");

    /**
     * Resets responseMessage to empty StringBuilder.
     */
    private void resetMessage() {
        responseMessage = new StringBuilder("");
    }

    /**
     * Sets responseMessage to list of tasks.
     * @param tasks tasks of user.
     */
    public void setShowListMessage(List<Task> tasks) {
        resetMessage();
        responseMessage.append("Banana! So many tasks?\n\n");
        Task task;
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            responseMessage.append((i + 1) + ". " + task.toString() + "\n");
        }
    }

    /**
     * Sets responseMessage to bye message.
     */
    public void setByeMessage() {
        resetMessage();
        responseMessage.append("Banana! KING BOB is sad to see you go. Farewell my friend!");
    }

    /**
     * Sets responseMessage to added message.
     * @param task Task that is added.
     * @param tasksNumber number of tasks.
     */
    public void setAddedMessage(Task task, int tasksNumber) {
        resetMessage();
        responseMessage.append("Banana! Banana has been added to your list!\n"
                + "     " + task.toString() + "\n"
                + "Now you have " + tasksNumber + " banana(s) in your list! Nom nom..");
    }

    /**
     * Sets responseMessage to duplicate message.
     */
    public void setDuplicateMessage() {
        resetMessage();
        responseMessage.append("Banana! There is a duplicate in the list!");
    }

    /**
     * Sets responseMessage to deleted message.
     * @param task Task that is deleted.
     * @param tasksNumber Number of tasks.
     */
    public void setDeletedMessage(Task task, int tasksNumber) {
        resetMessage();
        responseMessage.append("Banana! Banana has been eaten. Burp!\n"
                + "     " + task.toString() + "\n"
                + "Now you have " + (tasksNumber - 1) + " banana(s) in your list! Nom nom..");
    }

    /**
     * Sets responseMessage to done message.
     * @param task Task that is done.
     */
    public void setDoneMessage(Task task) {
        resetMessage();
        responseMessage.append("Banana! I've marked this task as done:\n"
                + "     " + task.toString());
    }

    /**
     * Sets responseMessage to loading error message.
     */
    public void setLoadingErrorMessage() {
        resetMessage();
        responseMessage.append("Banana! There is a loading error...");
    }

    /**
     * Sets responseMessage to tasks happening/due this date.
     * @param date date requested by user.
     * @param tasks tasks of user.
     */
    public void setFindTaskByDateMessage(LocalDate date, List<Task> tasks) {
        resetMessage();
        responseMessage.append("Banana! Here are your bananas..\n");
        for (Task t : tasks) {
            if (t.getDate().equals(date)) {
                responseMessage.append("     " + t.toString() + "\n");
            }
        }
    }

    /**
     * Sets responseMessage to tasks containing this keyword.
     * @param keyword Keyword input by user.
     * @param tasks Tasks of user.
     */
    public void setFindTaskByKeywordMessage(String keyword, List<Task> tasks) {
        resetMessage();
        responseMessage.append("Banana! Here are your bananas..\n");
        for (Task t : tasks) {
            if (t.toString().contains(keyword)) {
                responseMessage.append("     " + t.toString() + "\n");
            }
        }
    }

    /**
     * Returns the responseMessage.
     * @return responseMessage of this Ui Object.
     */
    public String getResponseMessage() {
        assert responseMessage != null : "There is no response message";
        return responseMessage.toString();
    }

}
