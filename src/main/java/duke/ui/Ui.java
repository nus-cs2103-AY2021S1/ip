package duke.ui;

import duke.tasklist.TaskList;
import duke.tasks.Task;

import java.time.LocalDate;

import java.util.Scanner;

/** This class deals with messages sent to the user. */
public class Ui {
    
    /** The message to be sent to the user through the GUI. */
    private String message;
    private boolean isError;

    public String getMessage() {
        return this.message;
    }

    /**
     * Saves all tasks on the specified date to the message.
     * If no date is specified, saves all tasks to the message.
     *
     * @param taskList The TaskList containing all tasks.
     * @param date The date used to filter tasks.
     */
    public void saveListMessage(TaskList taskList, LocalDate date) {
        int i = 1;
        StringBuilder sb = new StringBuilder();

        if (date == null) {
            for (Task t : taskList.getList()) {
                sb.append(i + "." + t + "\n");
                i++;
            }
        } else {
            for (Task t : taskList.getList()) {
                if (t.getDate() != null && t.getDate().equals(date)) {
                    sb.append(i + "." + t + "\n");
                    i++;
                }
            }
        }

        this.message = sb.toString();
    }

    /**
     * Saves all tasks containing the keyword to the message.
     *
     * @param taskList The TaskList containing all tasks.
     * @param keyword The keyword filter.
     */
    public void saveFindMessage(TaskList taskList, String keyword) {
        int i = 1;
        assert i >= 0 : "index of taskList must be greater or equal to zero";
        assert i < taskList.size() : "index of taskList must be less than size of taskList";
        StringBuilder sb = new StringBuilder();

        for (Task t : taskList.getList()) {
            if (t.getDescription().indexOf(keyword) != -1) {
                sb.append(i++ + "." + t + "\n");
            }
        }

        this.message = sb.toString();
    }

    /**
     * Saves the done message with the task that has just been marked as done.
     *
     * @param task The task marked as done.
     */
    public void saveDoneMessage(Task task) {
        assert task != null : "task cannot be null";
        this.message = "Nice! I've marked this task as done:\n\t" + task;
    }

    /**
     * Saves the delete message with the task that has just been deleted.
     *
     * @param task The deleted task.
     */
    public void saveDeleteMessage(Task task) {
        assert task != null : "task cannot be null";
        this.message = "Noted. I've removed this task:\n\t" + task;
    }

    /**
     * Saves the add message with the task that has just been added.
     *
     * @param task The added task.
     * @param size The size of the TaskList.
     */
    public void saveAddMessage(Task task, int size) {
        assert task != null : "task cannot be null";
        this.message = "Got it. I've added this task:\n\t" + task + "\n"
                + "Now you have " + size + " tasks in the list";
    }

    public void saveUpdateMessage(Task task, int taskIndex) {
        this.message = "Got it. I've updated task " + taskIndex + " to:\n\t" + task;
    }

    /**
     * Saves error messages.
     *
     * @param error The error message.
     */
    public void saveErrorMessage(String error) {
        this.message = error;
        this.isError = true;
    }

    public boolean getIsError() {
        return this.isError;
    }

    public void resetIsError() {
        this.isError = false;
    }
}
