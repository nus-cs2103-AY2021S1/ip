package duke.ui;

import duke.task.Task;

public class Ui {

    public Ui() { }

    /**
     * Prints a welcome message.
     */
    public static String showWelcome() {
        return "Hello, I'm Pico, no time to waste. "
                + "\nLet's get started!"
                + "\nWhat shall we do now?";
    }

    /**
     * Generates a success message after task is added successfully.
     *
     * @param taskAdded task added to TaskList
     * @return Add Success message
     */
    public static String getAddSuccessMessage(Task taskAdded) {
        return "Got it. I've added this task:"
                + "\n\t" + taskAdded;
    }

    /**
     * Generates a done message after task is marked as done.
     *
     * @param taskDone task that was marked as done
     * @return Done message
     */
    public static String getDoneMessage(Task taskDone) {
        return "Nice! I've marked this task as done:"
                + "\n\t" + taskDone;
    }

    /**
     * Generates a done message after task is deleted from TaskList.
     *
     * @param taskDeleted task that was deleted
     * @return Delete message
     */
    public static String getDeleteMessage(Task taskDeleted) {
        return "Noted. I've deleted this task:"
                + "\n\t" + taskDeleted;
    }


}
