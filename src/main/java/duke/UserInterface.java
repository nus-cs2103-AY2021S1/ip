package duke;

/**
 * Class to represent the User Interface of which a user interacts with.
 *
 * @author Kor Ming Soon
 */
public class UserInterface {

    private static final String BORDER = "      ";

    private static final String ERROR = "ERROR: ";

    private static final String NEWLINE = "\n";

    /**
     * To print out the task that is just added to the list.
     *
     * @param task Task of which to be added.
     * @param totalNumber The prevailing number after a task is added.
     * @return string to print whenever a task is added to the list.
     */
    public String printAddTask(String task, int totalNumber) {
        String response = BORDER + "Steady! I add... wait ah.." + NEWLINE;
        response += BORDER + BORDER + "ADDED: " + task + NEWLINE;
        response += BORDER + "Now you got " + totalNumber + " tasks" + NEWLINE;
        return response;
    }

    /**
     * Preamble while the chat bot retrieves list of the tasks.
     *
     * @return response from when user prompts List Command
     */
    public String listTask() {
        return BORDER + "Retrieving your list, patient ah!\n";
    }

    /**
     * To print out the list of tasks in an ordered fashion.
     *
     * @param listNumber Index of the tasking in the list.
     * @param task The details of the task itself.
     * @return response from Duke to list Command
     */
    public String printTask(int listNumber, String task) {
        String response = String.format("%2d. %s\n", listNumber, task);
        return BORDER + response;
    }

    /**
     * To print the preamble as well as the details of the task marked as done.
     *
     * @param task Task of which to be marked as done.
     * @return response from Duke to print Done command
     */
    public String printDone(String task) {
        String response = BORDER + "Swee la, task done liao:" + NEWLINE;
        response += BORDER + BORDER + task + NEWLINE;
        return response;
    }

    /**
     * Preamble as well as printing of the sequence of deletion.
     *
     * @param task Task to be deleted.
     * @param remainingNumber Remaining number of tasks after deletion.
     */
    public String printDelete(String task, int remainingNumber) {
        String response = BORDER + "Delete liao boss:" + NEWLINE;
        response += BORDER + "Remaining Tasks: " + remainingNumber + NEWLINE;
        return response;
    }

    /**
     * Standard error message to be printed.
     *
     * @param errorMessage The details of the error message itself.
     */
    public String printError(String errorMessage) {
        return ERROR + errorMessage + NEWLINE;
    }

    /**
     * Preamble for the initialisation of the Pikachu Chatbot.
     *
     * @return response for initialisation and instruction for User.
     */
    public String sendWelcomeMessage() {

        String startingMessage1 =
                "Pika pika pika!!! Pika Pika CHU!\n"
                + "\u2022 'list', to list all your task!\n"
                + "\u2022 'event <task> /at <duration>'\n"
                + "\u2022 'deadline <task> /by <deadline>' \n"
                + "\u2022 'delete x' delete a task!\n"
                + "\u2022 'done x' complete a task to tick it\n"
                + "\u2022 'find <key word search>'\n"
                + "\u2022 'help' to bring this page up!\n";
        ;

        String startingMessage2 = "Hello! I am PikaDuke, I can help you keep track of your tasks!\n";

        return startingMessage1;
    }

    /**
     * Exit message when the chat bot terminates.
     *
     * @return response when the user ends the interaction with the bot.
     */
    public String exitMessage() {
        String exitMessage = "Pikachu: Pika byebye! THUNDERBOLT!\n";
        return exitMessage;
    }

}
