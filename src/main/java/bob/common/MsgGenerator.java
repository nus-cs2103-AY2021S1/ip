package bob.common;

import bob.data.task.Task;
import bob.data.task.Tasklist;
import bob.exceptions.BobException;

public class MsgGenerator {
    /**
     * Shows message(s) to user.
     *
     * @param message Message to be shown.
     */
    private static String generateMsg(String ... message) {
        String response = "";
        for (String m : message) {
            response += m;
        }
        return response;
    }

    private static String genFindMessage(String input) {
        return "I found these tasks containing \"" + input + "\" in your list.\n";
    }

    private static String genNoTaskFoundMessage(String input) {
        return "I can't find any task containing \"" + input + "\" in your list.\n";
    }



    /**
     * Shows introduction message to user.
     */
    public static String generateIntroMessage() {
        return generateMsg(Messages.INTRO);
    }

    /**
     * Shows exit message to user.
     */
    public static String generateExitMessage() {
        return generateMsg(Messages.OUTRO);
    }

    /**
     * Shows error message to user.
     *
     * @param e Exception caught.
     */
    public static String generateError(BobException e) {
        return generateMsg(e.toString());
    }

    /**
     * Shows add message to user.
     *
     * @param task Task to include in the message.
     * @param tasks Tasklist of Bob.
     */
    public static String generateAddMessage(Task task, Tasklist tasks) {
        // Starting line
        String start = Messages.ADD_MSG;
        // Task
        String taskMsg = task + "\n";
        // Ending line
        String end = "Currently you have " + tasks.getListSize() + " tasks in your list.\n";
        return generateMsg(start, taskMsg, end);
    }

    /**
     * Shows done message to user.
     *
     * @param task Task to include in the message.
     */
    public static String generateDoneMessage(Task task) {
        String taskMsg = task + "\n";
        return generateMsg(Messages.DONE_MSG, taskMsg);
    }

    public static String generateListMessage(Tasklist tasks) {
        return generateMsg(tasks.toString());
    }

    /**
     * Shows delete message to user.
     *
     * @param task
     */
    public static String generateDeleteMessage(Task task) {
        String taskMsg = "Deleted: " + task + "\n";
        return generateMsg(Messages.DELETE_MSG, taskMsg);
    }

    /**
     * Shows loading error to user.
     */
    public static String generateLoadingError() {
        return generateMsg(Messages.LOADING_ERROR);
    }

    /**
     * Shows updating error to user.
     */
    public static String generateUpdatingError() {
        return generateMsg(Messages.UPDATE_ERROR);
    }

    public static String generateTasksFoundMessage(String input, String tasksFound) {
        return generateMsg(genFindMessage(input), tasksFound);
    }

    public static String generateNoTaskFoundMessage(String input) {
        return generateMsg(genNoTaskFoundMessage(input));
    }

    /**
     * Generates a ListIndexOutOfBounds error message.
     *
     * @param totalNoOfTasks Total number of tasks.
     * @param taskNo Task number.
     * @param action The command that was supposed to be executed.
     * @return String message.
     */
    public static String generateListIndexOutOfBoundsMsg(int totalNoOfTasks, int taskNo, String action) {
        return "Erm, you are asking me to " + action + " task " + taskNo + " but there is/are only "
                + totalNoOfTasks + " tasks. Please enter the correct number instead.";
    }

}
