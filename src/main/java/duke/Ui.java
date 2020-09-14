package duke;

import duke.util.TaskList;

/**
 * Handles interactions with the user.
 */
public class Ui {

    /** Checks if there is a GUI. */
    private final boolean hasGui;

    /**
     * Creates a UI and checks the presence of GUI.
     *
     * @param hasGui If there is a GUI or not.
     */
    public Ui(boolean hasGui) {
        this.hasGui = hasGui;
    }

    /**
     * Displays Duke's introduction message.
     *
     * @return Introduction to be displayed if GUI is used.
     */
    public String displayIntroduction() {
        String divider = "____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String welcome = "Hello! I'm Duke.\n" + "How can I help you?\n";
        if (hasGui) {
            return welcome;
        } else {
            System.out.println(divider + logo + welcome + divider);
            return "";
        }
    }

    /**
     * Displays a message.
     *
     * @param message Message.
     * @return Message to be displayed if GUI is used.
     */
    public String displayMessage(String message) {
        if (hasGui) {
            return message;
        } else {
            System.out.println(message);
            return "";
        }
    }

    /**
     * Displays a warning message.
     *
     * @param message Warning message.
     * @return Warning message to be displayed if GUI is used.
     */
    public String displayWarning(String message) {
        String warningMessage = "WARNING: " + message;
        if (hasGui) {
            return warningMessage;
        } else {
            System.out.println(warningMessage);
            return "";
        }
    }

    /**
     * Displays an error message.
     *
     * @param message Error message.
     * @return Error message to be displayed if GUI is used.
     */
    public String displayError(String message) {
        String errorMessage = "ERROR: " + message;
        if (hasGui) {
            return errorMessage;
        } else {
            System.out.println(errorMessage);
            return "";
        }
    }

    /**
     * Displays the entire list of tasks.
     *
     * @param taskList Task list.
     * @return Task list to be displayed if GUI is used.
     */
    public String displayTaskList(TaskList taskList) {
        int numOfTasks = taskList.getNumOfTasks();
        StringBuilder messageBuilder = new StringBuilder();
        if (numOfTasks == 0) {
            messageBuilder.append("There are no tasks in your list.\n");
        } else {
            if (numOfTasks == 1) {
                messageBuilder.append("This is the only task in your list:\n");
            } else {
                messageBuilder.append("Here are the ").append(numOfTasks).append(" tasks in your list:\n");
            }

            for (int i = 0; i < numOfTasks; i++) {
                int number = i + 1;
                messageBuilder.append(number).append(". ").append(taskList.getTask(i).toString());
                messageBuilder.append("\n");
            }
        }

        String message = messageBuilder.toString();
        if (hasGui) {
            return message;
        } else {
            System.out.println(message);
            return "";
        }
    }
}
