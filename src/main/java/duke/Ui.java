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
     * Sends a greeting to user.
     */
    public void greeting() {
        String logo =
                "8888888 888b    888 88888888888 8888888b.  888     888 888888b.    .d88888b. 88888888888 \n" +
                        "  888   8888b   888     888     888   Y88b 888     888 888  \"88b  d88P\" \"Y88b    888     \n" +
                        "  888   88888b  888     888     888    888 888     888 888  .88P  888     888    888     \n" +
                        "  888   888Y88b 888     888     888   d88P 888     888 8888888K.  888     888    888     \n" +
                        "  888   888 Y88b888     888     8888888P\"  888     888 888  \"Y88b 888     888    888     \n" +
                        "  888   888  Y88888     888     888 T88b   888     888 888    888 888     888    888     \n" +
                        "  888   888   Y8888     888     888  T88b  Y88b. .d88P 888   d88P Y88b. .d88P    888     \n" +
                        "8888888 888    Y888     888     888   T88b  \"Y88888P\"  8888888P\"   \"Y88888P\"     888";
        printReply(replyFormatter("ITS ME: \n" + logo + "\nI want to know EVERYTHING ABOUT YOU"));
    }

    /**
     * Sends Goodbye message to user.
     */
    public void sayGoodbye() {
        printReply("SAYONARA!");
    }

    /**
     * Wraps message in partitioning for clearer readability.
     * 
     * @param reply to be wrapped in partition.
     * @return String representation of reply.
     */
    public static String replyFormatter(String reply) {
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
        String formattedListString = "";
        for (int i = 0; i < taskItems.size(); i ++) {
            formattedListString+= String.format("%d. %s\n", i + 1, taskItems.get(i));
        }
        return formattedListString;
    }

    /**
     * Shows Task error message with a wrapper.
     * 
     * @param errorMessage custom error message.
     */
    public void showError(String errorMessage) {
        System.out.println(replyFormatter(String.format("Something is amiss `(OCO)/ !!: %s", errorMessage)));
    }

    /**
     * Shows Task done message.
     * 
     * @param task which is mark complete.
     */
    public void doneTaskReply(Task task) {
        printReply(replyFormatter("Nice! I've marked this task as done:\n" + task.toString()));
    }

    /**
     * Shows Task added message.
     *
     * @param task which is added.
     */
    public void addTaskReply(Task task, TaskList taskItems) {
        printReply(replyFormatter(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list"
                , task.toString(), taskItems.getSize())));
    }

    /**
     * Shows Task deleted message.
     *
     * @param task which is deleted.
     */
    public void deleteTaskReply(Task task, TaskList taskItems) {
        printReply(replyFormatter(String.format("HAI. I've deleted this task:\n    %s\nNow you have %d tasks in the list"
                , task.toString(), taskItems.getSize())));
    }

    
    public void findTaskReply(List<Task> matchingTasks) { 
        printReply(replyFormatter(String.format("HAI. Here are matching tasks:\n%s",listFormatter(matchingTasks))));
    }
    
    /**
     * Prints String to console. 
     *
     * @param reply String to be printed.
     */
    public void printReply(String reply) {
        System.out.println(reply);
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
