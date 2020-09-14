import java.util.ArrayList;

public class Ui {

    // Messages used in the package classes
    static final String MESSAGE_NOT_A_NUMBER = "The index has to be a number of course!";
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
    private static final String MESSAGE_BINDING = "%s is now bound to %s!";

    public Ui() {
    }

    public static String showWelcome() {
        return MESSAGE_WELCOME;
    }

    // processes the input and generates the output in the correct format.
    public void displayOutput(String input) {
        System.out.print(BORDER + LEFT_MARGIN + input + "\n" + BORDER);
    }

    // displays the task list in the correct format
    public String displayList(ArrayList<Task> tasks, boolean isFiltered) {
        if (tasks.size() == 0) {
            return isFiltered ? MESSAGE_NO_MATCHES : MESSAGE_EMPTY;
        }

        StringBuilder out = new StringBuilder(isFiltered ? MESSAGE_MATCHES : MESSAGE_TASKS).append("\n");
        for (short i = 0; i < tasks.size(); i++) {
            out.append(LEFT_MARGIN).append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return out.substring(0, out.length() - 1);
    }

    public String showAdd(short count, Task task) {
        return MESSAGE_ADD + "\n" + LEFT_MARGIN + task + "\n" + String.format(MESSAGE_COUNT, count);
    }

    public String showDelete(short listSize, Task task) {
        return MESSAGE_DELETE + "\n" + LEFT_MARGIN + task + "\n" + String.format(MESSAGE_COUNT, listSize);
    }

    public String showDone(Task task) {
        assert task.isDone() : "task should be marked as done";
        return MESSAGE_DONE + "\n" + LEFT_MARGIN + task;
    }

    public String showMapping(String originalKey, String newKey) {
        return String.format(MESSAGE_BINDING, originalKey, newKey);
    }
}
