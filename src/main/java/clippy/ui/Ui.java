package clippy.ui;

import clippy.task.Task;

/**
 * Represents a User Interface object that deals with interactions with the user.
 */
public class Ui {
    private static final String MSG_GREET = "Hello! I'm Clippy \n" + "What can I do for you today?\n";
    private static final String MSG_EXIT = "Bye! Hope to see you again soon!";
    private static final String MSG_ADDED_TASK = "Got it. I've added this task:\n";
    private static final String MSG_LIST_TASK_NO_TASK = "You have no tasks in your list.";
    private static final String MSG_LIST_TASK_HAVE_TASK = "Here are the tasks in your list:";
    private static final String MSG_MARK_DONE = "Nice! I've marked this task as done:\n";
    private static final String MSG_DELETED_TASK = "Noted. I've removed this task:\n";
    private static final String MSG_FOUND_MATCHING_TASK = "Here are the matching tasks in your list:";
    private static final String MSG_NO_MATCHING_TASK = "No matching tasks are found in your list.";
    private static final String MSG_UPDATED_TASK = "Ok! I have updated the task as follows:\n";
    private static final String MSG_NOW_NO_TASK = "Now you have no tasks in the list.";
    private static final String MSG_NOW_ONE_TASK = "Now you have 1 task in the list.";
    private static final String MSG_HELP = "Refer to the user guide at https://wang-jun-hao.github.io/ip/";

    /**
     * Returns welcome message.
     * 
     * @return Welcome message.
     */
    public static String showWelcome() {
        return MSG_GREET;
    }

    /**
     * Returns exit message.
     * 
     * @return Exit message.
     */
    public String showExit() {
        return MSG_EXIT;
    }

    /**
     * Returns message of given exception.
     * 
     * @param e Exception encountered.
     * @return Message of exception.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Returns help message.
     * 
     * @return Help message.
     */
    public String showHelp() {
        return MSG_HELP;
    }

    /**
     * Returns header of list of tasks.
     * 
     * @return Header of list of tasks.
     */
    public String showListWithTasksHeader() {
        return MSG_LIST_TASK_HAVE_TASK;
    }

    /**
     * Returns no tasks in list message.
     * 
     * @return No tasks in list message.
     */
    public String showListNoTasks() {
        return MSG_LIST_TASK_NO_TASK;
    }

    /**
     * Returns a String with the details of the given task numbered according the given index.
     * 
     * @param i Index of the task in the list.
     * @param task Task whose details is to be shown.
     * @return A String with the details of the given task numbered according the given index.
     */
    public String showTaskWithIndex(int i, Task task) {
        return i + "." + task;
    }

    /**
     * Returns a String showing successful deletion of the given task and the number of tasks remaining in the list.
     * 
     * @param task Task that was deleted.
     * @param numOfRemainingTasks Number of tasks remaining in the list after deletion.
     * @return A String showing successful deletion of the given task and the number of tasks remaining in the list.
     */
    public String showDeleted(Task task, int numOfRemainingTasks) {
        String numOfRemainingTaskDescription = generateNumOfTasksNowStatement(numOfRemainingTasks);
        
        return MSG_DELETED_TASK + task + "\n" + numOfRemainingTaskDescription;
    }
    
    private String generateNumOfTasksNowStatement(int numOfTasks) {
        assert numOfTasks >= 0 : "Negative number of remaining tasks";
        
        switch (numOfTasks) {
        case 0:
            return MSG_NOW_NO_TASK;
        case 1:
            return MSG_NOW_ONE_TASK;
        default:
            return "Now you have " + numOfTasks + " tasks in the list.";
        }
    }

    /**
     * Returns a confirmation message of marking a task as done.
     * 
     * @param task Task that was marked as done.
     * @return A confirmation message of marking a task as done.
     */
    public String showDone(Task task) {
        return MSG_MARK_DONE + task;
    }

    /**
     * Returns a confirmation message of adding a task into the task list.
     * 
     * @param task Task that was added into the task list.
     * @param numOfTasks Number of tasks in the list after adding the task.
     * @return A confirmation message of adding a task into the task list.
     */
    public String showAdded(Task task, int numOfTasks) {
        assert numOfTasks >= 0 : "Negative number of tasks";
        
        String numOfTaskDescription = generateNumOfTasksNowStatement(numOfTasks);
        
        return MSG_ADDED_TASK + task + "\n" + numOfTaskDescription;
    }

    /**
     * Returns a message showing no tasks were matched with the keyword.
     * 
     * @return A message showing no tasks were matched with the keyword.
     */
    public String showNoMatch() {
        return MSG_NO_MATCHING_TASK;
    }

    /**
     * Returns the header of a list of matched tasks.
     * 
     * @return Header of a list of matched tasks.
     */
    public String showMatchingTaskHeader() {
        return MSG_FOUND_MATCHING_TASK;
    }

    /**
     * Returns a confirmation message of updating the details of a task.
     * 
     * @param indexOfUpdatedTask Index of updated task as per the full list.
     * @param updatedTask Task that was updated successfully.
     * @return A confirmation message of updating the details of a task.
     */
    public String showUpdated(int indexOfUpdatedTask, Task updatedTask) {
        return MSG_UPDATED_TASK + showTaskWithIndex(indexOfUpdatedTask, updatedTask);
    }
    
}
