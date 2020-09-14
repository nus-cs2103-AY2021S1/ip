package duke.ui;

/**
 * Encapsulates a Messenger class to manage important notifications in the program.
 */
public class Messenger {
    /**
     * Project logo.
     */
    public static final String LOGO = " ____   ___      _            __         _\n"
                                    + "|  _   \\/ _     \\ | |         /    \\      | |____\n"
                                    + "| | |  | | |  | | |        /   _  \\     |  __   |\n"
                                    + "| |_| | |_|  | | |___ /  /__\\ \\   |  |  |  |\n"
                                    + "|____/\\____/ |_____|_/    \\__\\ |_|  |_|\n";

    /**
     * Messages for loading and saving files.
     */
    public static final String FILE_LOADING = "Hold a while, trying to retrieve where you were last time...";
    public static final String FILE_LOADED = "Great! We have successfully loaded the data. Enjoy~";
    public static final String CLOSE_MESSAGE = "Content Saved! Hope to see you again soon!";

    /**
     * Error handling messages.
      */
    public static final char FROWN = '\u2639';
    public static final String INDEX_FORMAT_ERROR = FROWN + " OOPS!!! Seems you have provided an invalid index :-(";
    public static final String COMMAND_UNRECOGNIZABLE_ERROR = FROWN + " OOPS!!! I'm sorry, "
            + "but I don't know what that means :-(";
    public static final String EMPTY_COMMAND_ERROR = FROWN + " OOPS!!! The command cannot be empty!";
    public static final String SPELL_ERROR = FROWN + " OOPS!!! Check if you have spelled correctly!";
    public static final String EMPTY_CONTENT_ERROR = FROWN + " OOPS!!! Seems you forgot to supply the main content!";
    public static final String EMPTY_TIME_ERROR = FROWN + " OOPS!!! Seems you forgot to supply the time!\n"
            + "Simply add '/by <time>' for deadline OR '/at <time>' for event behind your command";
    public static final String INDEX_OUT_OF_BOUND_ERROR = FROWN + " OOPS!!! Seems the "
            + "index you provided is not in the list.";
    public static final String FILE_NOT_FOUND = "Couldn't find a save file. You may start adding things from start!";
    public static final String DIRECTORY_NOT_FOUND = "Couldn't find the data directory. Creating one for you...";
    public static final String DATE_FORMAT_ERROR = FROWN + " OOPS!!! The format for your date is incorrect. "
            + "Use yyyy-MM-dd format instead";
    public static String emptyDescriptionError(String command) {
        return String.format(FROWN + " OOPS!!! The description of a %s cannot be empty.", command);
    }
}
