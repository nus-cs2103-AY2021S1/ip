package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Sends a greeting to user and indicates the INTRUBOT is active.
     * Loads data from harddisk
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

    public void sayGoodbye() {
        printReply("SAYONARA!");
    }
    
    // Formatting and UI
    public static String replyFormatter(String reply) {
        String partition = "__________________________";
        return String.format(partition + "\n%s\n" + partition, reply);
    }

    public void showError(String errorMessage) {
        System.out.println(replyFormatter(String.format("Something is amiss `(OCO)/ !!: %s", errorMessage)));
    }
    
    public void doneTaskReply(Task task) {
        printReply(replyFormatter("Nice! I've marked this task as done:\n" + task.toString()));
    }

    public void addTaskReply(Task task, TaskList taskItems) {
        printReply(replyFormatter(String.format("Got it. I've added this task:\n    %s\nNow you have %d tasks in the list"
                , task.toString(), taskItems.getSize())));
    }

    public void deleteTaskReply(Task task, TaskList taskItems) {
        printReply(replyFormatter(String.format("HAI. I've deleted this task:\n    %s\nNow you have %d tasks in the list"
                , task.toString(), taskItems.getSize())));
    }


    public void printReply(String reply) {
        System.out.println(reply);
    }
    
    public String readCommand() {
        return  SCANNER.nextLine();
    }
}
