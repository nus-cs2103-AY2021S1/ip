package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    /**
     * Star Bot's logo shown upon start up
     */
    private static final String LOGO = "     _______.___________.    ___     " +
            " .______      \n" +
            "    /       |           |   /   \\     |   _  \\     \n" +
            "   |   (----`---|  |----`  /  ^  \\    |  |_)  |    \n" +
            "    \\   \\       |  |      /  /_\\  \\   |      /     \n" +
            ".----)   |      |  |     /  _____  \\  |  |\\  \\----.\n" +
            "|_______/       |__|    /__/     \\__\\ | _| `._____|\n" +
            "                                                   \n" +
            "         .______     ______   .___________.        \n" +
            "         |   _  \\   /  __  \\  |           |        \n" +
            "         |  |_)  | |  |  |  | `---|  |----`        \n" +
            "         |   _  <  |  |  |  |     |  |             \n" +
            "         |  |_)  | |  `--'  |     |  |             \n" +
            "         |______/   \\______/      |__|             \n" +
            "                                                   ";

    /**
     * Divider that delineates Star Bot's replies
     */
    private static final String DIVIDER =
            "------------------------------------------------------\n";

    private static final Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(LOGO + "\nHello, I'm Star Bot! What can I do for " +
                "you?\nSay \"bye\" to exit.\n");
    }

    public void showGoodbye() {
        showReply("Goodbye, see you again soon! :)");
    }

    public void showError(Exception e) {
        showReply(e.getMessage());
    }

    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Standardises the look of Star Bot's replies by wrapping it in
     * dividers
     */
    public void showReply(String reply) {
        System.out.println(DIVIDER + reply + "\n" + DIVIDER);
    }

    public void showReplyForAddTask(Task newTask, TaskList tasks) {
        showReply("Got it. I've added this task:\n" + newTask + "\nNow " +
                "you have " + tasks.getNumberOfTasks() + " tasks in the list.");
    }

    public void showReplyForDoneTask(Task doneTask) {
        showReply("Nice! I've marked this task as done:\n" + doneTask);
    }

    public void showReplyForDeleteTask(Task removedTask, TaskList tasks) {
        showReply("Noted. I've removed this task:\n" + removedTask +
                "\nNow you have " + tasks.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Formats the task list to be shown to the user.
     */
    public String showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Your list is empty! Let's add some tasks!";
        }
        return "Here are the tasks in your list:" + tasks.toString();
    }
}
