package duke;

import duke.tasks.Task;

/**
 * <code>duke.Ui</code> contains all the methods and information needed to diplay
 * the user interface on the screen.
 */
public class Ui {
    final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    final String LINE = "------------------------";

    /**
     * Shows the start screen.
     */
    public void showStartScreen() {
        System.out.println(LINE);
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println(LINE + "\n");
    }

    /**
     * Prints out a string with a <code>LINE</code> above and below it.
     * @param s the string to be printed out
     */
    public void print(String s) {
        System.out.println(LINE);
        System.out.println(s);  
        System.out.println(LINE);
    }

    /**
     * Shows the interface to ask for a command.
     */
    public void askForCommand() {
        System.out.println(LINE);
        System.out.println("What is your command?"); 
        System.out.println(LINE);
        System.out.print("> ");
    }

    /**
     * Shows the interface to display the error.
     * @param errorMessage the error to be displayed
     */
    public void showErrorScreen(String errorMessage) {
        System.out.println(LINE);
        System.out.println("Oh no, something went wrong!");
        System.out.println(errorMessage);
        System.out.println(LINE);
    }

    /**
     * Shows the exit screen.
     */
    public void showExitScreen() {
        System.out.println(LINE);
        System.out.println("GOODBYE!");
        System.out.println(LINE);
    }

    /**
     * Shows the interface to ask for the name of a todo.
     */
    public String askTodo() {
        return "What is the name of your Todo?";
    }

    /**
     * Shows the interface to ask for the name of a deadline.
     * @return
     */
    public String askDeadlineName() {
        return "What is the name of your Deadline?";
    }

    /**
     * Shows the interface to ask for the due date of the deadline.
     */
    public String askDeadlineDate() {
        return "When is the deadline? (Give in this format: day month year)";
    }

    /**
     * Shows the interface to ask for the name of the event.
     */
    public String askEventName() {
        return "What is the name of your Event?";
    }

    /**
     * Shows the interface to ask for the start time of the event.
     */
    public String askEventStartTime() {
        return "When is the start of your event? (Give in this format: day month year hour:min)";
    }

    /**
     * Shows the interface to to ask for the end time of the event.
     */
    public String askEventEndTime() {
        return "When is the end of your event? (Give in this format: day month year hour:min)";
    }

    /**
     * Shows the interface to ask for the number of the
     * task to be marked as completed.
     */
    public String askTaskNumToComplete() {
        return "What is the number of the task you wish to mark as complete?";
    }

    /**
     * Displays a message to indicating a task has been marked as completed.
     * @param task the task that has completed
     */
    public String taskCompleted(Task task) {
        return "Nice, I've marked this task as done!" + task.toString();
    }

    /**
     * Shows the interface to ask for the number of the
     * task to be deleted.
     */
    public String askTaskNumToDelete() {
        System.out.println(LINE);
        System.out.println("What is the number of the task you wish to delete?"); 
        System.out.println(LINE);
        System.out.print("> ");
        return "What is the number of the task you wish to delete?";
    }

    /**
     * Show interface to ask for a keyword.
     */
    public String askForKeyword() {
        return "Give me a keyword to search for";
    }
}