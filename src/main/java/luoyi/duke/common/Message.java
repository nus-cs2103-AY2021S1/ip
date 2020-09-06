package luoyi.duke.common;

import java.util.HashMap;

/**
 * Encapsulate all the common messages used in the application.
 */
public enum Message {
    WELCOME ("Hi I'm Cat Bot.\nWhat can I do for you?\n"),
    FAREWELL ("Bye! Hope to see you again! (App closing in 5s)\n"),
    LIST ("Here are the tasks in your list:\n"),
    MARKED_DONE ("Naisu! I've marked this task done!\n"),
    REMOVED_TASK ("Hmmm~! I've removed this task:\n"),
    ERR_NO_TASK_IN_LIST ("Oops! Looks like there's no task in the list!\n"),
    ERR_NO_MATCHING_TASK ("Oops! Looks like there's no matching task!\n"),
    ERR_WRONG_LIST_CMD ("Wrong list command!\n" + Usage.get("list") + "\n"),
    ERR_WRONG_DONE_CMD ("Wrong done command!\n" + Usage.get("done") + "\n"),
    ERR_WRONG_TODO_CMD ("Wrong todo command!\n" + Usage.get("todo") + "\n"),
    ERR_WRONG_DEADLINE_CMD ("Wrong deadline command!\n" + Usage.get("deadline") + "\n"),
    ERR_WRONG_EVENT_CMD ("Wrong event command!\n" + Usage.get("event") + "\n"),
    ERR_WRONG_DELETE_CMD ("Wrong delete command!\n" + Usage.get("delete") + "\n"),
    ERR_WRONG_FIND_CMD ("Wrong find command!\n" + Usage.get("find") + "\n"),
    ERR_WRONG_CMD ("Unrecognizable command!\n"),
    ERR_DUKE_NOT_INIT("Duke not initialised yet in execute command"),
    CAT_DOUBT ("Meow? Sorry I don't know what you are talking about...\n"),
    CAT_CRY ("Meow!!! Something terrible happened!\n");

    /**
     * Class to encapsulate the usage help strings.
     */
    private static class Usage {
        private static final HashMap<String, String> usages = new HashMap<>();

        static {
            usages.put("list", "List the tasks stored. Optionally, list tasks on a specific date."
                    + "\nUsage: list [date]");
            usages.put("done", "Mark a task as done.\nUsage: done [index]");
            usages.put("todo", "Create a new todo task.\nUsage: todo [description]");
            usages.put("deadline", "Create a new deadline task.\nUsage: deadline [description] /by [time]");
            usages.put("event", "Create a new event task.\nUsage: event [description] /at [time]");
            usages.put("delete", "Delete a task.\nUsage: delete [index]");
            usages.put("find", "Find a task with description.\nUsage: find [description]");
            usages.put("bye", "Quit application.\nUsage: bye");
            usages.put("help", "Get help. Can specify command.\nUsage: help [keyword]");
        }

        private static String get(String key) {
            return usages.get(key);
        }
    }

    private final String message;
    Message(String message) {
        this.message = message;
    }

    /**
     * Returns help message.
     *
     * @param keyword Keyword used to find help.
     * @return Help message.
     */
    public static String getHelpMessage(String keyword) {
        if (keyword != null) {
            String helpMessage = Usage.get(keyword);
            return helpMessage == null
                    ? "Cannot find help for " + keyword
                    : keyword + ": " + helpMessage;
        }

        // If no keyword, return help for all commands
        StringBuilder sb = new StringBuilder();
        Usage.usages.forEach((key, description) ->
                sb.append(key).append(": ").append(description).append("\n\n"));
        return sb.toString();
    }

    @Override
    public String toString() {
        return this.message;
    }
}
