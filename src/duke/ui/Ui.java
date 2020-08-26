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
    
    public static boolean exit = false;
    
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
    public void showGreetings() {
        System.out.println(CHATBOT + "Hey there! I'm Bob" + SKIPLINE + "What can I do for you today?");
        System.out.println(USER);
    }

    /**
     * Displays the standard goodbye message "Bob" gives to users when users exit the program.
     */
    public void showGoodbyeMessage() {
        System.out.println(DIVIDER + "Goodbye! Have a nice day :D");
        this.scan.close();
        exit = true;

    }

    /**
     * Displays the task list saved in the local storage. 
     *
     * @param tasks Task list saved in the local storage.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(DIVIDER);

        // If list is empty
        if (tasks.totalNumberOfTasks() == 0) {
            System.out.print("List is empty :(");
        }

        if (tasks.totalNumberOfTasks() > 0) {
            System.out.println("Your current tasklist is as follows:");
            System.out.println(tasks);
        }

        System.out.println(USER);
    }

    public void showFilteredByKeywordTaskList(TaskList tasks, String keyword) {
        System.out.println(DIVIDER);

        if (tasks.totalNumberOfTasks() == 0) {
            System.out.println("No tasks matched with '" + keyword + "' :(");
        }

        if (tasks.totalNumberOfTasks() > 0) {
            System.out.println("Here are the tasks that matched with '" + keyword + "':");
            System.out.println(tasks);
        }

        System.out.println(USER);
    }

    /**
     * Displays the saved task list when the program starts up.
     *
     * @param tasks Task list saved in the local storage
     */
    public void loadTaskList(TaskList tasks) {
        System.out.println("Here is your current tasklist:");
        if (tasks.totalNumberOfTasks() == 0) {
            System.out.print("List is empty :(");
            System.out.println(SKIPLINE);
        }

        if (tasks.totalNumberOfTasks() > 0) {
            System.out.println(tasks);
        }
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
    public void showAddedNewTaskMessage(Task newTask, TaskList tasks) {
        System.out.println(DIVIDER);

        // Bob's response
        System.out.println("Noted! I have added the following task to your list:");
        System.out.println(newTask);
        System.out.println("You now have " + tasks.totalNumberOfTasks() + " task(s) in your list");
        showSuccessfullySavedMessage();
        System.out.println(USER);

    }

    /**
     * Informs user that indicated task has been deleted from the task list and has successfully 
     * been deleted from the task list.
     *
     * @param deletedTask Task to be deleted from the task list.
     * @param tasks Task list saved in the local storage.
     */
    public void showDeleteTaskMessage(Task deletedTask, TaskList tasks) {
        System.out.println(DIVIDER);

        // Bob's response
        System.out.println("Noted! I have deleted this task from your list:");
        System.out.println(deletedTask);
        System.out.println("You now have " + tasks.totalNumberOfTasks() + " task(s) in your list");
        System.out.println(USER);

    }

    /**
     * Informs user that indicated task has been successfully marked as done. 
     *
     * @param doneTask Task to be marked done.
     */
    public void showMarkDoneMessage(Task doneTask) {
        System.out.println(DIVIDER);

        // Bob's response
        System.out.println("Good job completing this task! I've marked this task as done:");
        System.out.println(doneTask);
        System.out.println("Keep up the good work :)");
        System.out.println(USER);

    }

    /**
     * Informs user that indicated task is already marked as done.
     *
     * @param doneTask Task that is already marked done.
     */
    public void showAlreadyMarkDoneMessage(Task doneTask) {
        System.out.println(DIVIDER);

        // Bob's response
        System.out.println("OOPS. It seems like this task has already been completed:");
        System.out.println(doneTask);
        System.out.println(USER);
    }

    /**
     * Displays the appropriate error message depending on the type of Exception is passed into the
     * parameters.
     *
     * @param e Exception caught.
     */
    public void showErrorMessage(Exception e) {
        System.out.println(DIVIDER);
        System.out.println(e.getMessage());
        System.out.println(USER);
    }

    /**
     * Informs user that the updated task list has been successfully saved to the file.
     */
    public void showSuccessfullySavedMessage() {
        System.out.println(SKIPLINE + "Successfully saved tasklist to file :)");
    }
}
