package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Represents Ui Components for user interaction and displaying messages.
 */
public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Sends greeting message to user.
     *
     * @return String representation of greeting message.
     */
    public String greetUser() {
        String greetingLogo = "8888888 888b    888 88888888888 8888888b.  888     888 888888b.    .d88888b. 88888888888 \n"
                + "  888   8888b   888     888     888   Y88b 888     888 888  \"88b  d88P\" \"Y88b    888     \n"
                + "  888   88888b  888     888     888    888 888     888 888  .88P  888     888    888     \n"
                + "  888   888Y88b 888     888     888   d88P 888     888 8888888K.  888     888    888     \n"
                + "  888   888 Y88b888     888     8888888P\"  888     888 888  \"Y88b 888     888    888     \n"
                + "  888   888  Y88888     888     888 T88b   888     888 888    888 888     888    888     \n"
                + "  888   888   Y8888     888     888  T88b  Y88b. .d88P 888   d88P Y88b. .d88P    888     \n"
                + "8888888 888    Y888     888     888   T88b  \"Y88888P\"  8888888P\"   \"Y88888P\"     888";
        return "ITS ME: \n" + greetingLogo + "\nI want to know EVERYTHING ABOUT YOU";
    }

    /**
     * Sends good bye message to user. 
     *
     * @return String with good bye message.
     */
    public String sayGoodbye() {
        return "SAYONARA!";
    }

    /**
     * Wraps message in partitioning for clearer readability.
     *
     * @param reply to be wrapped in partition.
     * @return String representation of reply.
     */

    public static String formatReply(String reply) {
        String partition = "__________________________";
        return String.format(partition + "\n%s\n" + partition, reply);
    }

    /**
     * Formats list object to be String representation.
     *
     * @param taskItems taskItems to be converted to appropriate String representation.
     * @return String representation of taskItems.
     */
    public static String listFormatter(List<Task> taskItems) {
        StringBuilder formattedListString = new StringBuilder();
        for (int i = 0; i < taskItems.size(); i ++) {
            formattedListString.append(String.format("%d. %s\n", i + 1, taskItems.get(i)));
        }
        return formattedListString.toString();
    }

    /**
     * Shows Task error message with a wrapper.
     *
     * @param errorMessage custom error message.
     */
    public static String showError(String errorMessage) {
        return String.format("Something is amiss `(OCO)/ !!: %s", errorMessage);
    }

    /**
     * Shows Task done message.
     *
     * @param task which is mark complete.
     * @return String representation of task done message.
     */
    public String doneTaskReply(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Shows Task added message.
     *
     * @param task which is added.
     */
    public String addTaskReply(Task task, TaskList taskItems) {
        return String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list"
                , task.toString(), taskItems.getSize());
    }

    /**
     * Shows Task deleted message.
     *
     * @param task which is deleted.
     * @return String representation of delete message
     */
    public String deleteTaskReply(Task task, TaskList taskItems) {
        return String.format("HAI. I've deleted this task:\n    %s\nNow you have %d tasks in the list"
                , task.toString(), taskItems.getSize());
    }


    /**
     * Shows Task found message.
     *
     * @param matchingTasks list of matching tasks.
     * @return String representation of delete message
     */
    public String findTaskReply(List<Task> matchingTasks) {
        return String.format("HAI. Here are matching tasks:\n%s",listFormatter(matchingTasks));
    }

    /**
     * Reads user input in the command line.
     *
     * @return String representation of user input.
     */
    public String readCommand() {
        return SCANNER.nextLine();
    }
}
