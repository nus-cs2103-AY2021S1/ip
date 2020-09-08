package duke;

public class Ui {

    private static final String MESSAGE_CURRENT_TASKS = "Now you have %d task(s) in the list.";
    private static final String MESSAGE_ADD_TASK = "Got it. I've added this task:";
    private static final String MESSAGE_DONE_TASK = "Nice! I've marked this as done:";
    private static final String MESSAGE_DELETED_TASK = "Noted. I've removed this task:";
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke, your task-list manager!\n"
            + "Before we get started, let me know if you would like to:\n"
            + "\ti)  LOAD <filepath>  : fetch a task-list you have made before, or\n"
            + "\tii) CREATE <filepath>: create a new task-list from scratch.";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";

    public Ui() {}

    private static String showLines(String... lines) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;
        for (String s : lines) {
            if (!isFirst) {
                stringBuilder.append('\n');
            } else {
                isFirst = false;
            }
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    private static String showNumbered(boolean hasHeader, boolean hasFooter, String... lines) {
        int start = 0;
        int end = lines.length;
        int number = 1;
        StringBuilder stringBuilder = new StringBuilder();

        if (hasHeader) {
            assert(lines.length > 0);
            stringBuilder.append(lines[0]);
            stringBuilder.append('\n');
            start++;
        }

        if (hasFooter) {
            end--;
        }

        for (int i = start; i < end; i++) {
            stringBuilder.append(String.format("%d. %s\n", number++, lines[i]));
        }

        if (hasFooter) {
            stringBuilder.append(lines[lines.length - 1]);
        }
        return stringBuilder.toString();
    }

    public static String showWelcomeMessage() {
        return MESSAGE_WELCOME;
    }

    public String showGoodbyeMessage() {
        return MESSAGE_GOODBYE;
    }

    public String showErrorMessage(String errorMessage) {
        return "\u2639 OOPS!!! " + errorMessage;
    }

    public String showTasks(String... tasks) {
        String message = "Here are the tasks in your list:";
        if (tasks.length == 0) {
            return showLines(message, "\tThere are no tasks in your list.");
        } else {
            String[] lines = new String[tasks.length + 1];
            lines[0] = message;
            System.arraycopy(tasks, 0, lines, 1, tasks.length);
            return showNumbered(true, false, lines);
        }
    }

    public String showFoundTasks(String... finds) {
        String message = "Here are the matching tasks in your list:";
        if (finds.length == 0) {
            return showLines(message, "\tThere are no matching tasks in your list.");
        } else {
            String[] lines = new String[finds.length + 1];
            lines[0] = message;
            System.arraycopy(finds, 0, lines, 1, finds.length);
            return showNumbered(true, false, lines);
        }
    }

    public String showNoSuchTasks(String searchQuery) {
        return "Hmm...I did not manage to find any tasks containing " + searchQuery;
    }

    public String showLoadingSuccess(String filepath) {
        return showLines("Got it. I have successfully loaded your task-list from " + filepath,
                "What can I do for you next?");
    }

    public String showMakeFileSuccess(String filepath) {
        return showLines("Congratulations! You now have a new task-list at " + filepath,
                "What can I do for you next?");
    }

    public String showAddedTask(String task, int numTasks) {
        return showLines(MESSAGE_ADD_TASK, " " + task, String.format(MESSAGE_CURRENT_TASKS, numTasks));
    }

    public String showDoneTask(String task) {
        return showLines(MESSAGE_DONE_TASK, "  " + task);
    }

    public String showDeletedTask(String task, int numTasks) {
        return showLines(MESSAGE_DELETED_TASK, "  " + task, String.format(MESSAGE_CURRENT_TASKS, numTasks));
    }

}
