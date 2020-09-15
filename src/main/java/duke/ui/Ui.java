package duke.ui;

import duke.storage.TaskList;
import duke.task.Task;

/**
 * Displays the user interface and deals with interacting with the user by printing responding to
 * user commands.
 */
public class Ui {
    private static final String SKIPLINE = "\n";
    private static final String EMPTY_RESPONSE = "";
    private boolean hasExit;

    /**
     * Constructs a new user interface object to deal with user interactions.
     */
    public Ui() {
        this.hasExit = false;
    }

    /**
     * Displays the standard greetings "Bob" gives to users when the program starts up.
     *
     * @return Greetings by "Bob".
     */
    public String showGreetings() {
        return SKIPLINE + "Hey there! I'm Bob" + SKIPLINE + "What can I do for you today?";
    }

    /**
     * Displays to the user that they have already exited the program and is required to close and reopen the program if
     * the user wishes to continue using the program.
     *
     * @return Message notifying user to close and reopen program to continue using the program.
     */
    public String showAlreadyExitedMessage() {
        return "Why are you still here? You have already said bye to me....." + SKIPLINE
                + "If you want to continue to talk please close and reopen me :D";
    }

    /**
     * Displays the standard goodbye message "Bob" gives to users when users exit the program.
     *
     * @return Goodbye message by "Bob".
     */
    public String showGoodbyeMessage() {
        this.hasExit = true;
        return "Goodbye! Have a nice day :D";
    }

    /**
     * Displays to the user that their task list is currently empty.
     *
     * @return Message to notify users that their task list is empty.
     */
    private String showEmptyTaskListMessage() {
        return "List is empty :(";
    }

    /**
     * Retrieves the current status of the user's task list.
     * If the task list is empty, it notifies users that their task list is empty.
     * Otherwise, it displays the tasks in the task list.
     *
     * @param tasks Task list saved in the local storage.
     * @param message "Bob's" response which changes depending on the user command.
     * @return Task list saved in local storage.
     */
    private String generateTaskList(TaskList tasks, String message) {
        String tasklist = EMPTY_RESPONSE;

        // If list is empty
        if (tasks.getTotalNumberOfTasks() == 0) {
            tasklist += showEmptyTaskListMessage();
        } else if (tasks.getTotalNumberOfTasks() > 0) {
            tasklist += message + tasks.toString();
        } else {
            // Should not reach this line
            assert false;
        }

        return tasklist;
    }

    /**
     * Displays the task list saved in the local storage.
     *
     * @param tasks Task list saved in the local storage.
     * @return Task list saved in the local storage.
     */
    public String showTaskList(TaskList tasks) {
        String chatbotResponse = "Your current task list is as follows:" + SKIPLINE;
        String output = generateTaskList(tasks, chatbotResponse);

        assert !output.equals(EMPTY_RESPONSE);
        return output;
    }

    /**
     * Displays the task list containing tasks that match a keyword.
     *
     * @param tasks Task list containing the tasks that match the keyword.
     * @param keyword Keyword found in the filtered tasks.
     * @return Task list containing tasks that match the keyword.
     */
    public String showFilteredByKeywordTaskList(TaskList tasks, String keyword) {
        String chatbotResponse = "Here are the tasks that matched with '" + keyword + "':" + SKIPLINE;
        String output = generateTaskList(tasks, chatbotResponse);

        assert !output.equals(EMPTY_RESPONSE);
        return output;
    }

    /**
     * Displays the saved task list when the program starts up.
     *
     * @param tasks Task list saved in the local storage.
     * @return Task list saved in the local storage when the program starts up.
     */
    public String loadTaskList(TaskList tasks) {
        String chatbotResponse = "Here is your current task list:" + SKIPLINE;
        String output = generateTaskList(tasks, chatbotResponse);

        assert !output.equals("Here is your current task list:");
        return output;
    }

    /**
     * Returns the error message when there is an invalid user command.
     *
     * @param userCommand Invalid user command.
     * @return Error message to inform users that userCommand is invalid.
     */
    public String showInvalidUserCommand(String userCommand) {
        return "Sorry but I don't understand what '" + userCommand + "' means :(";
    }


    /**
     * Returns message notifying users that a task has been added or deleted from the task list.
     *
     * @param taskIndicator Indicates whether a task has been added or deleted from the task list.
     * @return Message notifying users that a task has been added or deleted from the task list.
     */
    private String getNotedUpdateInTaskListMessage(String taskIndicator) {
        return "Noted! I have " + taskIndicator + " the following task to your list:" + SKIPLINE;
    }

    /**
     * Returns the total number of tasks currently saved in the task list.
     *
     * @param tasks Task list saved in the local storage.
     * @return Total number of tasks currently saved in the task list.
     */
    private String getNumberOfTasksInTaskList(TaskList tasks) {
        return SKIPLINE + "You now have " + tasks.getTotalNumberOfTasks() + " task(s) in your list" + SKIPLINE;
    }

    /**
     * Informs user that the updated task list has been successfully saved to the file.
     */
    private String getSuccessfullySavedMessage() {
        return SKIPLINE + "Successfully saved task list to file :)";
    }

    /**
     * Retrieves "Bob's" response to a change in the task list.
     *
     * @param tasks Task list saved in the local storage.
     * @param task Task that is either added into the task list or deleted from the task list.
     * @param taskIndicator Indicates if the task is being added to the task list or deleted from the task list.
     * @return Message notifying users that the task has been added/deleted.
     */
    private String getUpdatedTaskListMessage(TaskList tasks, Task task, String taskIndicator) {
        String message = getNotedUpdateInTaskListMessage(taskIndicator);
        message += task.toString();
        message += getNumberOfTasksInTaskList(tasks);
        message += getSuccessfullySavedMessage();
        return message;
    }

    /**
     * Informs user that a new task has been added to the task list and has successfully been added.
     *
     * @param newTask New task added to the task list.
     * @param tasks Task list saved in the local storage.
     * @return Message informing user that a new task has been successfully added to the task list.
     */
    public String showAddedNewTaskMessage(Task newTask, TaskList tasks) {
        String output = getUpdatedTaskListMessage(tasks, newTask, "added");
        return output;
    }

    /**
     * Informs user that indicated task has been deleted from the task list and has successfully
     * been deleted from the task list.
     *
     * @param deletedTask Task to be deleted from the task list.
     * @param tasks Task list saved in the local storage.
     * @return Message informing that user that the indicated task has been successfully deleted from the task list.
     */
    public String showDeleteTaskMessage(Task deletedTask, TaskList tasks) {
        String output = getUpdatedTaskListMessage(tasks, deletedTask, "deleted");
        return output;
    }

    /**
     * Informs user that indicated task has been successfully marked as done.
     *
     * @param doneTask Task to be marked done.
     * @return Message informing user that the indicated task has been successfully marked as done.
     */
    public String showMarkDoneMessage(Task doneTask) {
        String output = "Good job completing this task! I've marked this task as done:" + SKIPLINE;
        output += doneTask.toString() + SKIPLINE;
        output += "Keep up the good work :)";
        return output;
    }

    /**
     * Informs user that indicated task is already marked as done.
     *
     * @param doneTask Task that is already marked done.
     * @return Message informing user that the indicated task is already marked as done.
     */
    public String showAlreadyMarkDoneMessage(Task doneTask) {
        String output = "OOPS. It seems like this task has already been completed:" + SKIPLINE;
        output += doneTask.toString() + SKIPLINE;
        return output;
    }

    /**
     * Informs user that indicated task already exists in the task list.
     *
     * @param duplicateTask Task that already exists in the task list.
     * @return Message informing user that indicated task already exists in the task list.
     */
    public String showTaskIsAlreadyInTaskListMessage(Task duplicateTask) {
        String output = "OOPS. It seems like this task already exists:" + SKIPLINE;
        output += duplicateTask.toString() + SKIPLINE;
        return output;
    }

    /**
     * Displays the appropriate error message depending on the type of Exception is passed into the
     * parameters.
     *
     * @param e Exception caught.
     */
    public String showErrorMessage(Exception e) {
        String output = e.getLocalizedMessage();
        return output;
    }

    /**
     * Determines whether the user has exited the program.
     *
     * @return True if user exits the program, otherwise false.
     */
    public boolean hasExited() {
        return this.hasExit;
    }
}
