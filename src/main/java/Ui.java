import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    // Messages used in the package classes
    static final String MESSAGE_EXIT = "Hey! Come back here! You big-a monkey!";
    static final String MESSAGE_INVALID_ID =
            "That task wasn't even on the list! You can save the princess if you're that free...";
    static final String MESSAGE_TASK_ID_MISSING = "You didn't give me the task number to work with...";
    static final String MESSAGE_WRONG_FORMAT = "The date format should be in DD-MM-YY and the time in HH:MM";
    static final String MESSAGE_ALR_DONE = "Do you happen to have short term memory?";
    static final String MESSAGE_ERROR_IO = "An error occurred while loading/saving to disk";

    // General style properties of the chatbot.
    private static final String LEFT_MARGIN = "    ";
    private static final String BORDER = LEFT_MARGIN
            + "______________________________________________________________________________\n";
    private static final String LOGO = "\n" + LEFT_MARGIN
            + "888b     d888             d8888      8888888b.       8888888       .d88888b.  \n" + LEFT_MARGIN
            + "8888b   d8888            d88888      888   Y88b        888        d88P\" \"Y88b \n" + LEFT_MARGIN
            + "88888b.d88888           d88P888      888    888        888        888     888 \n" + LEFT_MARGIN
            + "888Y88888P888          d88P 888      888   d88P        888        888     888 \n" + LEFT_MARGIN
            + "888 Y888P 888         d88P  888      8888888P\"         888        888     888 \n" + LEFT_MARGIN
            + "888  Y8P  888        d88P   888      888 T88b          888        888     888 \n" + LEFT_MARGIN
            + "888   \"   888       d8888888888      888  T88b         888        Y88b. .d88P \n" + LEFT_MARGIN
            + "888       888      d88P     888      888   T88b      8888888       \"Y88888P\"  \n";
    private static final String LEFT_MARGIN_DOUBLE = LEFT_MARGIN + LEFT_MARGIN;

    // Other messages
    private static final String MESSAGE_WELCOME = "It's a-me, Mario! how can I help you today?";
    private static final String MESSAGE_EMPTY = "What do you expect me to say when you didn't save any tasks?";
    private static final String MESSAGE_TASKS = "As you wish, here's what you gotta do:";
    private static final String MESSAGE_DONE = "You did it! Good job little guy!";
    private static final String MESSAGE_ADD = "Got it! I've added this task:";
    private static final String MESSAGE_COUNT = "Now you have %s tasks in the list.";
    private static final String MESSAGE_DELETE = "Got it. I removed this task:";
    private static final String MESSAGE_NO_MATCHES = "Since when did you save a task like that?";
    private static final String MESSAGE_MATCHES = "These are the matching tasks in your list:";

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    // processes the input and generates the output in the correct format.
    public void displayOutput(String input) {
        System.out.print(BORDER + LEFT_MARGIN + input + "\n" + BORDER);
    }

    // displays the task list in the correct format
    public String displayList(ArrayList<Task> tasks, boolean isFiltered) {
        if (tasks.size() == 0) {
            return isFiltered ? MESSAGE_NO_MATCHES : MESSAGE_EMPTY;
        } else {
            StringBuilder out = new StringBuilder(isFiltered ? MESSAGE_MATCHES : MESSAGE_TASKS).append("\n");
            for (short i = 0; i < tasks.size(); i++) {
                out.append(LEFT_MARGIN).append(i + 1)
                        .append(".").append(tasks.get(i)).append("\n");
            }
            return out.toString();
        }
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public String showAdd(short count, Task task) {
        return MESSAGE_ADD + "\n" + LEFT_MARGIN + task + "\n" + String.format(MESSAGE_COUNT, count);
    }

    public String showDelete(short listSize, Task task) {
        return MESSAGE_DELETE + "\n" + LEFT_MARGIN + task + "\n"+ String.format(MESSAGE_COUNT, listSize);
    }

    public String showDone(Task task) {
        assert task.isDone() : "task should be marked as done";
        return MESSAGE_DONE + "\n" + LEFT_MARGIN + task;
    }

    public static String showWelcome() {
        return MESSAGE_WELCOME;
    }
}
