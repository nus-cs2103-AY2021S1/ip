package duke.ui;

import java.util.Scanner;

import duke.storage.TaskList;
import duke.task.Task;

/**
 * Displays the user interface and deals with interacting with the user by printing responding to
 * user commands.
 */
public class Ui {
    private static final String CHATBOT = "Bob: ";
    private static final String SKIPLINE = "\n";
    private static final String USER = SKIPLINE + "You: ";
    private static final String DIVIDER = SKIPLINE + CHATBOT;
    private static boolean hasExit = false;
    private final Scanner scan;

    /**
     * Creates a new user interface object to deal with user interactions.
     */
    public Ui() {
        this.scan = new Scanner(System.in);
    }

    /**
     * Reads user commands.
     *
     * @return User command.
     */
    public String readUserCommand() {
        return this.scan.nextLine();
    }

    /**
     * Displays the standard greetings "Bob" gives to users when the program starts up.
     */
    public String showGreetings() {
        // System.out.println(CHATBOT + "Hey there! I'm Bob" + SKIPLINE + "What can I do for you today?");
        // System.out.println(USER);
        return CHATBOT + "Hey there! I'm Bob" + SKIPLINE + "What can I do for you today?" + USER;
    }

    /**
     * Displays the standard goodbye message "Bob" gives to users when users exit the program.
     */
    public String showGoodbyeMessage() {
        // System.out.println(DIVIDER + "Goodbye! Have a nice day :D");
        this.scan.close();
        hasExit = true;
        return DIVIDER + "Goodbye! Have a nice day :D";
    }

    /**
     * Displays the task list saved in the local storage.
     *
     * @param tasks Task list saved in the local storage.
     */
    public String showTaskList(TaskList tasks) {
        String output = DIVIDER;
        // System.out.println(DIVIDER);

        // If list is empty
        if (tasks.totalNumberOfTasks() == 0) {
            // System.out.print("List is empty :(");
            output += SKIPLINE + "List is empty :(";
        }

        if (tasks.totalNumberOfTasks() > 0) {
            // System.out.println("Your current tasklist is as follows:");
            // System.out.println(tasks);
            output += SKIPLINE + "Your current tasklist is as follows:" + SKIPLINE + tasks.toString();
        }

        output += USER;
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
        String output = DIVIDER;
        // System.out.println(DIVIDER);

        if (tasks.totalNumberOfTasks() == 0) {
            // System.out.println("No tasks matched with '" + keyword + "' :(");
            output += SKIPLINE + "No tasks matched with '" + keyword + "' :(";
        }

        if (tasks.totalNumberOfTasks() > 0) {
            // System.out.println("Here are the tasks that matched with '" + keyword + "':");
            // System.out.println(tasks);
            output += SKIPLINE + "Here are the tasks that matched with '" + keyword + "':" + SKIPLINE
                    + tasks.toString();
        }
        output += USER;
        return output;
        // System.out.println(USER);
    }

    /**
     * Displays the saved task list when the program starts up.
     *
     * @param tasks Task list saved in the local storage
     */
    public String loadTaskList(TaskList tasks) {
        String output = "Here is your current tasklist:";
        // System.out.println("Here is your current tasklist:");
        if (tasks.totalNumberOfTasks() == 0) {
            output += SKIPLINE + "List is empty :(" + SKIPLINE;
            // System.out.print("List is empty :(");
            // System.out.println(SKIPLINE);
        }

        if (tasks.totalNumberOfTasks() > 0) {
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
        String output = DIVIDER + SKIPLINE;
        // System.out.println(DIVIDER);
        // Bob's response
        output += "Noted! I have added the following task to your list:" + SKIPLINE;
        output += newTask.toString() + SKIPLINE;
        output += "You now have " + tasks.totalNumberOfTasks() + " task(s) in your list";
        // System.out.println("Noted! I have added the following task to your list:");
        // System.out.println(newTask);
        // System.out.println("You now have " + tasks.totalNumberOfTasks() + " task(s) in your list");
        output += showSuccessfullySavedMessage();
        output += USER;
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
        String output = DIVIDER + SKIPLINE;
        // System.out.println(DIVIDER);

        // Bob's response
        output += "Noted! I have deleted this task from your list:" + SKIPLINE;
        output += deletedTask.toString() + SKIPLINE;
        output += "You now have " + tasks.totalNumberOfTasks() + " task(s) in your list";
        output += USER;
        // System.out.println("Noted! I have deleted this task from your list:");
        // System.out.println(deletedTask);
        // System.out.println("You now have " + tasks.totalNumberOfTasks() + " task(s) in your list");
        // System.out.println(USER);
        return output;
    }

    /**
     * Informs user that indicated task has been successfully marked as done.
     *
     * @param doneTask Task to be marked done.
     */
    public String showMarkDoneMessage(Task doneTask) {
        String output = DIVIDER + SKIPLINE;
        // System.out.println(DIVIDER);

        // Bob's response
        output += "Good job completing this task! I've marked this task as done:" + SKIPLINE;
        output += doneTask.toString() + SKIPLINE;
        output += "Keep up the good work :)" + USER;
        // System.out.println("Good job completing this task! I've marked this task as done:");
        // System.out.println(doneTask);
        // System.out.println("Keep up the good work :)");
        // System.out.println(USER);
        return output;
    }

    /**
     * Informs user that indicated task is already marked as done.
     *
     * @param doneTask Task that is already marked done.
     */
    public String showAlreadyMarkDoneMessage(Task doneTask) {
        String output = DIVIDER + SKIPLINE;
        // System.out.println(DIVIDER);

        // Bob's response
        output += "OOPS. It seems like this task has already been completed:" + SKIPLINE;
        output += doneTask.toString() + SKIPLINE;
        output += USER;
        return output;
        // System.out.println("OOPS. It seems like this task has already been completed:");
        // System.out.println(doneTask);
        // System.out.println(USER);
    }

    /**
     * Displays the appropriate error message depending on the type of Exception is passed into the
     * parameters.
     *
     * @param e Exception caught.
     */
    public String showErrorMessage(Exception e) {
        String output = DIVIDER + SKIPLINE;
        output += e.getMessage();
        output += USER;
        return output;
        // System.out.println(DIVIDER);
        // System.out.println(e.getMessage());
        // System.out.println(USER);
    }

    /**
     * Informs user that the updated task list has been successfully saved to the file.
     */
    public String showSuccessfullySavedMessage() {
        // System.out.println(SKIPLINE + "Successfully saved tasklist to file :)");
        return SKIPLINE + "Successfully saved tasklist to file :)";
    }

    /**
     * Determines whether the user has exited the program.
     *
     * @return True if user exits the program, otherwise false.
     */
    public static boolean hasExited() {
        return hasExit;
    }
}
