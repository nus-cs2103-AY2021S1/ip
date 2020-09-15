package bob.common;

import bob.data.task.Task;
import bob.data.task.Tasklist;
import bob.exceptions.BobException;

public class MsgGenerator {
    private static String genFindMessage(String input, String tasksFound) {
        return "I found these tasks containing \"" + input + "\" in your list.\n" + tasksFound;
    }

    private static String genNoTaskFoundMessage(String input) {
        return "I can't find any task containing \"" + input + "\" in your list.\n";
    }

    /**
     * Generates error message.
     *
     * @param e Exception caught.
     */
    public static String generateError(BobException e) {
        return e.toString();
    }

    /**
     * Generates add message.
     *
     * @param task Task to include in the message.
     * @param tasks Tasklist of Bob.
     * @return Add message.
     */
    public static String generateAddMessage(Task task, Tasklist tasks) {
        // Starting line
        String start = Messages.ADD_MSG;
        // Task
        String taskMsg = task + "\n";
        // Ending line
        String end = "Currently you have " + tasks.getListSize() + " tasks in your list.\n";
        return start + taskMsg + end;
    }

    /**
     * Generates done message.
     *
     * @param task Task to include in the message.
     * @return Done message.
     */
    public static String generateDoneMessage(Task task) {
        String taskMsg = task + "\n";
        return Messages.DONE_MSG + taskMsg;
    }

    public static String generateListMessage(Tasklist tasks) {
        return tasks.toString();
    }

    /**
     * Generates delete message.
     *
     * @param task Deleted task to include in the message.
     * @return Delete message.
     */
    public static String generateDeleteMessage(Task task) {
        String taskMsg = "Deleted: " + task + "\n";
        return Messages.DELETE_MSG + taskMsg;
    }

    /**
     * Generates reminder message.
     * @param tasklist Bob's Tasklist.
     * @return Reminder message.
     */
    public static String generateReminderMessages(Tasklist tasklist) {
        String header = Messages.REMINDER_HEADER;
        String unfinishedTasks = tasklist.getUnfinishedTasks();
        boolean noTasks = unfinishedTasks.length() == 0;
        if (noTasks) {
            return header + Messages.NO_UNFINISHED_TASKS_REMINDER;
        } else {
            return header + Messages.REMINDER_MSG + unfinishedTasks;
        }
    }

    public static String generateTasksFoundMessage(String input, String tasksFound) {
        return genFindMessage(input, tasksFound);
    }

    public static String generateNoTaskFoundMessage(String input) {
        return genNoTaskFoundMessage(input);
    }

    /**
     * Generates a ListIndexOutOfBounds error message.
     *
     * @param totalNoOfTasks Total number of tasks.
     * @param taskNo Task number.
     * @param action The command that was supposed to be executed.
     * @return List index out of bounds error message.
     */
    public static String generateListIndexOutOfBoundsMsg(int totalNoOfTasks, int taskNo, String action) {
        return "Erm, you are asking me to " + action + " task " + taskNo + " but there is/are only "
                + totalNoOfTasks + " tasks. Please enter the correct number instead.";
    }

}
