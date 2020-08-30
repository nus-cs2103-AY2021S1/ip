package duke;

/**
 * Class to initiate the Ui object. Contains various methods to handle different duke.commands.
 */
public class Ui {

    /**
     * Formats the message.
     *
     * @param words Words to be concatenated.
     * @return String formatted message of the given words.
     */
    private String messageFormatter(String... words) {
        StringBuilder string = new StringBuilder();
        for (String message: words) {
            string.append(message);
        }
        string.append("\n");
        return string.toString();
    }

    /**
     * Prints the welcome message.
     *
     * @param taskList Prints out the current tasks in list.
     */
    public String welcomeMessage(String taskList) {
        return messageFormatter("Hello! I'm Duke! Welcome back!\n",
                "Here are the tasks in your list:\n",
                taskList);
    }

    public String byeMessage() {
        return messageFormatter("Bye. Hope to see you again soon!");
    }

    public String listMessage(String taskList) {
        return messageFormatter("Here are the tasks in your list:\n", taskList);
    }

    public String errorMessage(String error) {
        return messageFormatter(error);
    }

    /**
     * Prints the done message.
     *
     * @param message Prints out the current task that is completed.
     */
    public String markAsDoneMessage(String message) {
        return messageFormatter("Nice! I've marked this task as done:\n",
                message);
    }

    /**
     * Prints the delete message.
     *
     * @param task Prints out the current deleted task.
     * @param size Number of tasks in the list.
     */
    public String deleteMessage(String task, int size) {
        return messageFormatter("Noted. I've removed this task:\n",
                task, "\nNow you have ", String.valueOf(size), " tasks in the list.");
    }

    /**
     * Prints the the added task message.
     *
     * @param task Prints out the added task in list.
     * @param size Number of tasks in the list.
     */
    public String taskMessage(String task, int size) {
        return messageFormatter("Got it. I've added this task:\n", task,
                "\nNow you have ", String.valueOf(size), " tasks in the list.");
    }

    /**
     * Prints the task that suits the input keyword message.
     *
     * @param taskList Prints out the task that suits the input keyword.
     * @param size Number of tasks in the list.
     */
    public String findTaskMessage(String taskList, int size) {
        if (size == 0) {
            return messageFormatter("There are not matching task in your list!");
        } else {
            return messageFormatter("There are ", String.valueOf(size),
                    " matching tasks in your list:\n", taskList);
        }
    }

}
