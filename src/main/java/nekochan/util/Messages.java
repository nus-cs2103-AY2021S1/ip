package nekochan.util;

public class Messages {

    public static final String ERROR_MESSAGE_HEADER = "Gomen nasai~ %s\n";

    public static final String INCOMPLETE_ADD_COMMAND = "Add command was not completed.\n";
    public static final String INCOMPLETE_LIST_COMMAND = "List command was not completed.\n";
    public static final String INCOMPLETE_SEARCH_COMMAND = "Search command was not completed.\n";
    public static final String INCOMPLETE_DELETE_COMMAND = "Delete command was not completed.\n";
    public static final String INCOMPLETE_COMPLETE_COMMAND = "Complete command was not completed.\n";
    public static final String INCOMPLETE_DELETE_ALL_COMMAND = "Delete all command was not completed.\n";
    public static final String INCOMPLETE_EXIT_COMMAND = "Exit command was not completed.\n";
    public static final String INCOMPLETE_REDO_COMMAND = "Redo command was not completed.\n";
    public static final String INCOMPLETE_UNDO_COMMAND = "Undo command was not completed.\n";

    public static final String PARSE_COMMAND_COMPLETE_MISSING_ARGUMENT = "What did you complete exactly?\n";
    public static final String PARSE_COMMAND_DELETE_MISSING_ARGUMENT = "What do you want to remove exactly?\n";
    public static final String PARSE_COMMAND_DEADLINE_MISSING_ARGUMENT = "Are you hiding something from me?\n";
    public static final String PARSE_COMMAND_TODO_MISSING_ARGUMENT = "I know your life is empty "
            + "but your todo can't be empty.\n";
    public static final String PARSE_COMMAND_EVENT_MISSING_ARGUMENT = "Are you going somewhere without me?\n";
    public static final String PARSE_COMMAND_SEARCH_MISSING_ARGUMENT = "Neko-chan doesn't have the "
            + "answer to everything.\n";
    public static final String PARSE_COMMAND_UNKNOWN = "Wakarimasen~\n";

    public static final String PARSE_DATETIME_ERROR = "Neko-chan can't understand what you're saying...\n";

    public static final String PARSE_TASK_DESCRIPTION_ERROR = "That's really descriptive...\n";
    public static final String PARSE_DEADLINE_DUE_DATE_ERROR = "So you never did plan on doing it huh...\n";
    public static final String PARSE_EVENT_DATETIME_ERROR = "Does this thing ever end???\n";
    public static final String PARSE_EVENT_MISSING_END_DATETIME_ERROR = "Something's missing, "
            + "oh right I lost track of time.\n";

    public static final String INVALID_TASK_TYPE_ERROR = "Neko-chan doesn't understand.\n";

    public static final String DECODE_UNEXPECTED_TYPE_ERROR = "Something doesn't seem right...\n";

    public static final String MISSING_TASK_ERROR = "Neko-chan doesn't know that much yet.\n";
    public static final String DUPLICATE_TASK_ERROR = "Neko-chan already know this!\n";
    public static final String SIMILAR_TASK_ERROR = "But Neko-chan found something similar:\n";

    public static final String STORAGE_ERROR_UNABLE_TO_WRITE = "Neko-chan doesn't know how to write.\n";
    public static final String STORAGE_ERROR_FOLDER_ERROR = "Neko-chan got lost somewhere in your folders.\n";
    public static final String STORAGE_ERROR_MISSING_SAVE = "Neko-chan thinks she lost her memory..."
            + " Let me start afresh.\n";
    public static final String STORAGE_ERROR_CORRUPT = "There's something wrong with my memory...\n";

    public static final String HISTORY_REDO_LIMIT_EXCEEDED = "Neko-chan can't tell the future!";
    public static final String HISTORY_UNDO_LIMIT_EXCEEDED = "Neko-chan has told you all she knows!";

    public static final String MESSAGE_WELCOME = "Konnichinyaa!\nWhat can Neko-chan do for you?\n";
    public static final String MESSAGE_ADD = "Hai hai~ Neko-chan has added this task:\n";
    public static final String MESSAGE_EMPTY_LIST = "Sugoi! You don't have any tasks left to do.\n";
    public static final String MESSAGE_LIST = "Here's everything Neko-chan knows:\n";
    public static final String MESSAGE_EXIT = "Ja ne!\n";
    public static final String MESSAGE_DELETE = "Hai! Neko-chan has removed this task:\n";
    public static final String MESSAGE_DELETE_ALL = "Neko-chan has cleared all your tasks.\nYou sure are efficient!\n";
    public static final String MESSAGE_COMPLETE = "Yay! Neko-chan has marked this task as complete:\n";
    public static final String MESSAGE_SEARCH = "Here's everything Neko-chan found:\n";
    public static final String MESSAGE_EMPTY_SEARCH = "Neko-chan couldn't find anything for you.\n";
    public static final String MESSAGE_UNDO = "You're lucky Neko-chan can reverse the time.\n";
    public static final String MESSAGE_REDO = "So this is how the future looks like.\n";
    private static final String MESSAGE_TASK_COUNT = "Neko-chan now knows %d %s.\n";

    public static String getTotalTaskMessage(int count) {
        return String.format(MESSAGE_TASK_COUNT, count, count > 1 ? "things" : "thing");
    }
}
