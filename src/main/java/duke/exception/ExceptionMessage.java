package duke.exception;

import duke.command.CommandFormat;

public class ExceptionMessage {

    public static final String DEADLINE_INCORRECT_FORMAT_MESSAGE =
            "\nPlease follow the format of " + CommandFormat.DEADLINE_CMD_FORMAT + "\n";

    public static final String EVENT_INCORRECT_FORMAT_MESSAGE =
            "\nPlease follow the format of " + CommandFormat.EVENT_CMD_FORMAT + "\n";

    public static final String TODO_NO_DESCRIPTION_MESSAGE =
            "\nOOPS!!! The description of a todo cannot be empty.\n";

    public static final String UNKNOWN_COMMAND_MESSAGE =
            "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n";

    public static String getInvalidIndexMessage(String wrongIndex) {
        return "\nSorry " + wrongIndex + " is not a valid index\n";
    }

    public static String getUnknownTaskMessage(String unknownTask) {
        return unknownTask + "is not a task type";
    }
}
