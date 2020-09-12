package duke;

import java.util.ArrayList;

/**
 * Deals with user interaction.
 */
class Ui {

    /**
     * Constructor used to create UI .
     */
    Ui() {
    }

    /**
     * Displays error message.
     *
     * @param e Duke Exception.
     */
    String showError(DukeException e) {
        return e.getMessage();
    }

    /**
     *
     * @return
     */
    String showWelcome() {
        String welcomeMessage = "Hello! I'm Shinomiya \u2665\n"
                + "What can I do for you?";
        return welcomeMessage;
    }

    /**
     *
     * @return
     */
    String showExit() {
        String exitMessage = "Bye. Hope to see you again soon! \u2665";
        return exitMessage;
    }

    /**
     *
     * @param taskString
     * @param lenString
     * @return
     */
    String showAddTaskMessage(String taskString, String lenString) {
        String addedTaskOutputMessage = "Got it. I've added this task:\n"
                + taskString + "\nNow you have " + lenString
                + " tasks in the list.";
        return addedTaskOutputMessage;
    }

    /**
     *
     * @param taskListString
     * @return
     */
    String showListTaskMessage(ArrayList<String> taskListString) {
        StringBuilder listTaskMessage = new StringBuilder("Here are the tasks in your list:\n");
        int index = 1;
        for (String taskString : taskListString) {
            listTaskMessage.append(index).append(".").append(taskString).append("\n");
            index++;
        }
        return listTaskMessage.toString();
    }

    /**
     *
     * @param taskString
     * @return
     */
    String showDoTaskMessage(String taskString) {
        String doneOutputMessage = "Nice! I've marked this task as done: \n"
                + taskString;
        return doneOutputMessage;
    }

    /**
     *
     * @param taskString
     * @param lenString
     * @return
     */
    String showDeleteTaskMessage(String taskString, String lenString) {
        String deletionOutputMessage = "Noted. I've removed this task:\n"
                + taskString + "\nNow you have " + lenString
                + " tasks in the list.";
        return deletionOutputMessage;
    }

    /**
     *
     * @param foundTaskListString
     * @return
     */
    String showFindTaskMessage(ArrayList<String> foundTaskListString) {
        StringBuilder findTaskMessage = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 1;
        for (String taskString : foundTaskListString) {
            findTaskMessage.append(index).append(".").append(taskString).append("\n");
            index++;
        }
        return findTaskMessage.toString();
    }

}
