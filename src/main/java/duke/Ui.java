package duke;

import duke.tasks.Task;

/**
 * <code>Ui</code> contains all the methods and information needed to diplay
 * the user interface on the screen.
 */
public class Ui {
    final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Shows the interface to ask for the name of a todo.
     * @return the question
     */
    public String askTodo() {
        return "What is the name of your Todo?";
    }

    /**
     * Shows the interface to ask for the name of a deadline.
     * @return the question
     */
    public String askDeadlineName() {
        return "What is the name of your Deadline?";
    }

    /**
     * Shows the interface to ask for the due date of the deadline.
     * @return the question
     */
    public String askDeadlineDate() {
        return "When is the deadline? (Give in this format: day month year)";
    }

    /**
     * Shows the interface to ask for the name of the event.
     * @return the question
     */
    public String askEventName() {
        return "What is the name of your Event?";
    }

    /**
     * Shows the interface to ask for the start time of the event.
     * @return the question
     */
    public String askEventStartTime() {
        return "When is the start of your event? (Give in this format: day month year hour:min)";
    }

    /**
     * Shows the interface to to ask for the end time of the event.
     * @return the question
     */
    public String askEventEndTime() {
        return "When is the end of your event? (Give in this format: day month year hour:min)";
    }

    /**
     * Shows the interface to ask for the number of the
     * task to be marked as completed.
     * @return the question
     */
    public String askTaskNumToComplete() {
        return "What is the number of the task you wish to mark as complete?";
    }

    /**
     * Displays a message to indicating a task has been marked as completed.
     * @param task the task that has completed
     * @return the message indicated the task has been marked as completed
     */
    public String taskCompleted(Task task) {
        return "Nice, I've marked this task as done!\n" + task.toString();
    }

    /**
     * Shows the interface to ask for the number of the
     * task to be deleted.
     * @return the question
     */
    public String askTaskNumToDelete() {
        return "What is the number of the task you wish to delete?";
    }

    /**
     * Show interface to ask for a keyword.
     * @return the question
     */
    public String askForKeyword() {
        return "Give me a keyword to search for";
    }
}