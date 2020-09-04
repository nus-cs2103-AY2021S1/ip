import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    private static final String INDENTATION = "\t";
    private static final String HORIZONTAL_LINE = "-------------------------" +
            "------------------------------";
    private static final String CURRENT_TASKS = "Now you have %d task(s) in the list.";
    private static final String ADD_TASK = "Got it. I've added this task:";
    private static final String DONE_TASK = "Nice! I've marked this as done:";
    private static final String DELETED_TASK = "Noted. I've removed this task:";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    private static void showFormatted(String... lines) {
        System.out.println(INDENTATION + HORIZONTAL_LINE);
        for (String s : lines) {
            System.out.println(INDENTATION + s);
        }
        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    private static void showNumbered(boolean hasHeader, boolean hasFooter, String... lines) {
        int start = 0;
        int end = lines.length;
        int number = 1;
        System.out.println(INDENTATION + HORIZONTAL_LINE);

        if (hasHeader) {
            System.out.println(INDENTATION + lines[0]);
            start++;
        }

        if (hasFooter) {
            end--;
        }

        for (int i = start; i < end; i++) {
            System.out.println(INDENTATION + String.format("%d. %s", number++, lines[i]));
        }

        if (hasFooter) {
            System.out.println(INDENTATION + lines[lines.length - 1]);
        }

        System.out.println(INDENTATION + HORIZONTAL_LINE);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }
    public void showWelcome() {
        showFormatted("Hello! I'm Duke", "What can I do for you?");
    }

    public void showGoodbye() {
        showFormatted("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        showFormatted("\u2639 OOPS!!! " + errorMessage);
    }

    public void showTasks(String... tasks) {
        String[] lines = new String[tasks.length + 1];
        lines[0] = "Here are the tasks in your list:";
        System.arraycopy(tasks, 0, lines, 1, tasks.length);
        showNumbered(true, false, lines);
    }

    public void showLoadingError(String message) {
        showFormatted("Oh dear, an error occurred while trying to load the file:", "\t" + message);
    }

    public void showAddedTask(String task, int numTasks) {
        showFormatted(ADD_TASK, "  " + task, String.format(CURRENT_TASKS, numTasks));
    }

    public void showDoneTask(String task) {
        showFormatted(DONE_TASK, "  " + task);
    }

    public void showDeletedTask(String task, int numTasks) {
        showFormatted(DELETED_TASK, "  " + task, String.format(CURRENT_TASKS, numTasks));
    }
}
