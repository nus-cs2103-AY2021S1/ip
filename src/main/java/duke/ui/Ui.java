package duke.ui;

import duke.storage.TaskList;
import duke.task.Task;

/**
 * Displays the user interface and deals with interacting with the user by printing responding to
 * user commands.
 */
public class Ui {
    private static final String CHATBOT = "Bob: ";
    private static final String SKIPLINE = "\n";
    private boolean hasExit;

    /**
     * Creates a new user interface object to deal with user interactions.
     */
    public Ui() {
        this.hasExit = false;
    }

    /**
     * Displays the standard greetings "Bob" gives to users when the program starts up.
     */
    public String showGreetings() {
        return SKIPLINE + "Hey there! I'm Bob" + SKIPLINE + "What can I do for you today?";
    }

    /**
     * Displays the standard goodbye message "Bob" gives to users when users exit the program.
     */
    public String showGoodbyeMessage() {
        this.hasExit = true;
        return "Goodbye! Have a nice day :D";
    }

    /**
     * Displays the task list saved in the local storage.
     *
     * @param tasks Task list saved in the local storage.
     */
    public String showTaskList(TaskList tasks) {
        String output = CHATBOT;

        // If list is empty
        if (tasks.getTotalNumberOfTasks() == 0) {
            output += SKIPLINE + "List is empty :(";
        }

        if (tasks.getTotalNumberOfTasks() > 0) {
            output += SKIPLINE + "Your current task list is as follows:" + SKIPLINE + tasks.toString();
        }
        return output;
        // System.out.println(USER);
    }

    /**
     * Displays the task list containing tasks that match a keyword.
     *
     * @param tasks Task list containing the tasks that match the keyword.
     * @param keyword Keyword found in the filtered tasks.
     */
    public String showFilteredByKeywordTaskList(TaskList tasks, String keyword) {
        String output = CHATBOT;

        if (tasks.getTotalNumberOfTasks() == 0) {
            output += SKIPLINE + "No tasks matched with '" + keyword + "' :(";
        }

        if (tasks.getTotalNumberOfTasks() > 0) {
            output += SKIPLINE + "Here are the tasks that matched with '" + keyword + "':" + SKIPLINE
                    + tasks.toString();
        }
        return output;
        // System.out.println(USER);
    }

    /**
     * Displays the saved task list when the program starts up.
     *
     * @param tasks Task list saved in the local storage
     */
    public String loadTaskList(TaskList tasks) {
        String output = CHATBOT + SKIPLINE + "Here is your current task list:";
        // System.out.println("Here is your current task list:");
        if (tasks.getTotalNumberOfTasks() == 0) {
            output += SKIPLINE + "List is empty :(" + SKIPLINE;
            // System.out.print("List is empty :(");
            // System.out.println(SKIPLINE);
        }

        if (tasks.getTotalNumberOfTasks() > 0) {
            // System.out.println(tasks);
            output += tasks.toString();
        }
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
     * Informs user that a new task has been added to the task list and has successfully been added.
     *
     * @param newTask New task added to the task list.
     * @param tasks Task list saved in the local storage.
     */
    public String showAddedNewTaskMessage(Task newTask, TaskList tasks) {
        String output = CHATBOT + SKIPLINE;

        // Bob's response
        output += "Noted! I have added the following task to your list:" + SKIPLINE;
        output += newTask.toString() + SKIPLINE;
        output += "You now have " + tasks.getTotalNumberOfTasks() + " task(s) in your list";
        output += SKIPLINE + showSuccessfullySavedMessage();
        // System.out.println(USER);
        return output;
    }

    /**
     * Informs user that indicated task has been deleted from the task list and has successfully
     * been deleted from the task list.
     *
     * @param deletedTask Task to be deleted from the task list.
     * @param tasks Task list saved in the local storage.
     */
    public String showDeleteTaskMessage(Task deletedTask, TaskList tasks) {
        String output = CHATBOT + SKIPLINE;

        // Bob's response
        output += "Noted! I have deleted this task from your list:" + SKIPLINE;
        output += deletedTask.toString() + SKIPLINE;
        output += "You now have " + tasks.getTotalNumberOfTasks() + " task(s) in your list";
        return output;
    }

    /**
     * Informs user that indicated task has been successfully marked as done.
     *
     * @param doneTask Task to be marked done.
     */
    public String showMarkDoneMessage(Task doneTask) {
        String output = CHATBOT + SKIPLINE;

        // Bob's response
        output += "Good job completing this task! I've marked this task as done:" + SKIPLINE;
        output += doneTask.toString() + SKIPLINE;
        output += "Keep up the good work :)";
        return output;
    }

    /**
     * Informs user that indicated task is already marked as done.
     *
     * @param doneTask Task that is already marked done.
     */
    public String showAlreadyMarkDoneMessage(Task doneTask) {
        String output = CHATBOT + SKIPLINE;

        // Bob's response
        output += "OOPS. It seems like this task has already been completed:" + SKIPLINE;
        output += doneTask.toString() + SKIPLINE;
        return output;
    }

    /**
     * Displays the appropriate error message depending on the type of Exception is passed into the
     * parameters.
     *
     * @param e Exception caught.
     */
    public String showErrorMessage(Exception e) {
        String output = CHATBOT + SKIPLINE;
        output += e.getLocalizedMessage();
        return output;
    }

    /**
     * Informs user that the updated task list has been successfully saved to the file.
     */
    public String showSuccessfullySavedMessage() {
        return SKIPLINE + "Successfully saved task list to file :)";
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
