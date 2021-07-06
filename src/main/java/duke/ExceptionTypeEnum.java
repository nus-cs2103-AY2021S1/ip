package duke;

public enum ExceptionTypeEnum {
    // thrown in executing commands
    INVALID_ITEM_NUMBER ("That is not a valid item number."),
    INVALID_DONE_COMMAND("Sorry - only todos and deadlines can be marked as done!"),
    ITEM_ALREADY_DONE ("This task has already been marked as done."),

    // thrown in parser
    INCORRECT_LIST ("Did you mean to say 'list'?"),
    MISSING_FIND_KEYWORD ("Please provide keyword(s) to look for"),
    MISSING_SCHEDULE_DATE ("Please provide a date to look for"),
    INCORRECT_SCHEDULE_DATE ("A date must follow ISO format: yyyy-mm-dd"),
    MISSING_TODO_DESCRIPTION ("The description for a todo cannot be empty."),
    MISSING_DEADLINE_DESCRIPTION ("The description for a deadline cannot be empty."),
    MISSING_DEADLINE_DATE ("The date for a deadline cannot be empty."),
    INCORRECT_DEADLINE_DATE ("The date for a deadline must follow ISO format: yyyy-mm-dd"),
    MISSING_EVENT_DESCRIPTION ("The description for a task cannot be empty."),
    MISSING_EVENT_DATE ("The date for an event cannot be empty."),
    INCORRECT_EVENT_DATE ("The date for an event must follow ISO format: yyyy-mm-dd"),
    MISSING_NOTE_NAME ("The name for a note cannot be empty."),
    MISSING_NOTE_DESCRIPTION ("The description for a note cannot be empty."),
    MISSING_DONE_ITEM ("Please specify an item number."),
    MISSING_DELETE_ITEM ("Please specify an item number."),
    INCORRECT_BYE ("Did you mean to say 'bye'?"),
    UNKNOWN_COMMAND ("OOPS!!! I'm sorry, but I don't know what that means :-(");


    private final String message;

    ExceptionTypeEnum(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
