package luoyi.duke.common;

/**
 * Encapsulate all the common messages used in the application.
 */
public enum Message {
    WELCOME ("Hi I'm Cat Bot.\nWhat can I do for you?\n"),
    FAREWELL ("Bye! Hope to see you again!\n"),
    LIST ("Here are the tasks in your list:\n"),
    MARKED_DONE ("Naisu! I've marked this task done!\n"),
    REMOVED_TASK ("Hmmm~! I've removed this task:\n"),
    ERR_NO_TASK_IN_LIST ("Oops! Looks like there's no task in the list!\n"),
    ERR_NO_MATCHING_TASK ("Oops! Looks like there's no matching task!\n"),
    ERR_WRONG_LIST_CMD ("Wrong list command!\nFormat: list Optional:<date>\n"),
    ERR_WRONG_DONE_CMD ("Wrong done command!\nFormat: done <taskId>\n"),
    ERR_WRONG_TODO_CMD ("Wrong todo command!\nFormat: todo <taskName>\n"),
    ERR_WRONG_DEADLINE_CMD ("Wrong deadline command!\nFormat: deadline <taskName> /by <time>\n"),
    ERR_WRONG_EVENT_CMD ("Wrong event command!\nFormat: event <taskName> /at <time>\n"),
    ERR_WRONG_DELETE_CMD ("Wrong delete command!\nFormat: delete <taskId>\n"),
    ERR_WRONG_FIND_CMD ("Wrong find command!\nFormat: find <description>\n"),
    ERR_WRONG_CMD ("Unrecognizable command!\n"),
    ERR_DUKE_NOT_INIT("Duke not initialised yet in execute command"),
    CAT_DOUBT ("Meow? Sorry I don't know what you are talking about...\n"),
    CAT_CRY ("Meow!!! Something terrible happened!\n");

    private final String message;
    Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
