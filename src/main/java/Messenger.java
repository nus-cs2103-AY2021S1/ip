public class Messenger {
    public static final String INDEX_FORMAT_ERROR = "☹ OOPS!!! Seems you have provided an invalid index :-(";
    public static final String COMMAND_UNRECOGNIZABLE_ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String EMPTY_COMMAND_ERROR = "☹ OOPS!!! The command cannot be empty!";
    public static final String SPELL_ERROR = "☹ OOPS!!! Check if you have spelled correctly!";
    public static final String EMPTY_CONTENT_ERROR = "☹ OOPS!!! Seems you forgot to supply the main content!";
    public static final String EMPTY_TIME_ERROR = "☹ OOPS!!! Seems you forgot to supply the time!\n"
            + "Simply add '/by <time>' for deadline OR '/at <time>' for event behind your command";
    public static final String INDEX_OUT_OF_BOUND_ERROR = "☹ OOPS!!! Seems the index you provided is not in the list.";

    public static String EMPTY_DESCRIPTION_ERROR(String command) {
        return String.format("☹ OOPS!!! The description of a %s cannot be empty.", command);
    }
}
