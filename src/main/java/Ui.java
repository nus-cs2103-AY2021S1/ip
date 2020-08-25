import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    // General style properties of the chatbot.
    private static final String LEFT_MARGIN = "    ";
    private static final String BORDER = LEFT_MARGIN + "_______________________________________________________\n";
    private static final String LOGO = "\n" + LEFT_MARGIN
            + "███╗   ███╗     █████╗     ██████╗     ██╗     ██████╗ \n" + LEFT_MARGIN
            + "████╗ ████║    ██╔══██╗    ██╔══██╗    ██║    ██╔═══██╗\n" + LEFT_MARGIN
            + "██╔████╔██║    ███████║    ██████╔╝    ██║    ██║   ██║\n" + LEFT_MARGIN
            + "██║╚██╔╝██║    ██╔══██║    ██╔══██╗    ██║    ██║   ██║\n" + LEFT_MARGIN
            + "██║ ╚═╝ ██║    ██║  ██║    ██║  ██║    ██║    ╚██████╔╝\n" + LEFT_MARGIN
            + "╚═╝     ╚═╝    ╚═╝  ╚═╝    ╚═╝  ╚═╝    ╚═╝     ╚═════╝ \n";
    private static final String LEFT_MARGIN_DOUBLE = LEFT_MARGIN + LEFT_MARGIN;

    // Messages
    private static final String MESSAGE_WELCOME = "It's a-me, Mario! how can I help you today?";
    private static final String MESSAGE_EMPTY = "What do you expect me to say when you didn't save any tasks?";
    private static final String MESSAGE_TASKS = "As you wish, here's what you gotta do:";
    private static final String MESSAGE_DONE = "You did it! Good job little guy!";
    private static final String MESSAGE_ADD = "Got it! I've added this task:";
    private static final String MESSAGE_COUNT = "Now you have %s tasks in the list.";
    private static final String MESSAGE_DELETE = "Got it. I removed this task:";
    static final String MESSAGE_EXIT = "Hey! Come back here! You big-a monkey!";
    static final String MESSAGE_INVALID_ID =
            "That task wasn't even on the list! You can save the princess if you're that free...";
    static final String MESSAGE_TASK_ID_MISSING = "You didn't give me the task number to work with...";
    static final String MESSAGE_WRONG_FORMAT = "The date format should be in DD-MM-YY and the time in HH:MM";
    static final String MESSAGE_ALR_DONE = "Do you happen to have short term memory?";
    static final String MESSAGE_ERROR_IO = "An error occurred while loading/saving to disk";

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    // processes the input and generates the output in the correct format.
    public void displayOutput(String input) {
        System.out.print(BORDER + LEFT_MARGIN + input + "\n" + BORDER);
    }

    // displays the task list in the correct format
    public void displayList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            displayOutput(MESSAGE_EMPTY);
        } else {
            StringBuilder out = new StringBuilder(MESSAGE_TASKS).append("\n");
            for (short i = 0; i < tasks.size(); i++) {
                out.append(LEFT_MARGIN_DOUBLE).append(i + 1)
                        .append(".").append(tasks.get(i)).append("\n");
            }
            displayOutput(out.substring(0, out.length() - 1));
        }
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showAdd(short id, Task task) {
        displayOutput(MESSAGE_ADD + "\n" + LEFT_MARGIN_DOUBLE + task + "\n"
                + LEFT_MARGIN + String.format(MESSAGE_COUNT, id));
    }

    public void showDelete(short listSize, Task task) {
        displayOutput(MESSAGE_DELETE + "\n" + LEFT_MARGIN_DOUBLE + task + "\n"
                + LEFT_MARGIN + String.format(MESSAGE_COUNT, listSize));
    }

    public void showDone(Task task) {
        displayOutput(MESSAGE_DONE + "\n" + LEFT_MARGIN_DOUBLE + task);
    }

    public void showWelcome() {
        System.out.print(BORDER + LOGO);
        displayOutput(MESSAGE_WELCOME);
    }
}
