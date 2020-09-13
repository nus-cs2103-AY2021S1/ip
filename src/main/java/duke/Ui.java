package duke;

import java.util.ArrayList;

/**
 * Deals with user interaction.
 */
class Ui {

    /**
     * Constructs User interface .
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
     * Displays welcome message.
     *
     * @return Welcome message.
     */
    String showWelcome() {
        String welcomeMessage = "Hello! I'm Piplup \uD83C\uDFB5\n"
                + "What should we explore today?";
        return welcomeMessage;
    }

    /**
     * Displays exit message.
     *
     * @return Exit message.
     */
    String showExit() {
        String exitMessage = "Bye. Hope to see you again soon! \uD83D\uDC27\u2665";
        return exitMessage;
    }

    /**
     * Displays add task message.
     *
     * @param taskString Task in string format.
     * @param lenString Length of task list in string format.
     * @return Add task message.
     */
    String showAddTaskMessage(String taskString, String lenString) {
        String addedTaskOutputMessage = "Got it. I've added this mission:\n"
                + taskString + "\nNow you have " + lenString
                + " missions in the list.";
        return addedTaskOutputMessage;
    }

    /**
     * Displays list task message.
     *
     * @param taskListString Task list in string format.
     * @return List task message.
     */
    String showListTaskMessage(ArrayList<String> taskListString) {
        StringBuilder listTaskMessage = new StringBuilder("Here are the missions in your list:\n");
        int index = 1;
        for (String taskString : taskListString) {
            listTaskMessage.append(index).append(".").append(taskString).append("\n");
            index++;
        }
        return listTaskMessage.toString();
    }

    /**
     * Displays do task message.
     *
     * @param taskString Task in string format.
     * @return Do task message.
     */
    String showDoTaskMessage(String taskString) {
        String doneOutputMessage = "Nice! I've marked this mission as done: \n"
                + taskString;
        return doneOutputMessage;
    }

    /**
     * Displays delete task message.
     *
     * @param taskString Deleted task in string format.
     * @param lenString Length of task list in string format.
     * @return Delete task message.
     */
    String showDeleteTaskMessage(String taskString, String lenString) {
        String deletionOutputMessage = "Noted. I've removed this mission:\n"
                + taskString + "\nNow you have " + lenString
                + " missions in the list.";
        return deletionOutputMessage;
    }

    /**
     * Displays find task message.
     *
     * @param foundTaskListString Task list found, in string format.
     * @return Find task message.
     */
    String showFindTaskMessage(ArrayList<String> foundTaskListString) {
        StringBuilder findTaskMessage = new StringBuilder("Here are the matching missions in your list:\n");
        int index = 1;
        for (String taskString : foundTaskListString) {
            findTaskMessage.append(index).append(".").append(taskString).append("\n");
            index++;
        }
        return findTaskMessage.toString();
    }

}
