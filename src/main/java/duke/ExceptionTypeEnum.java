package duke;

public enum ExceptionTypeEnum {
    UNKNOWN_COMMAND ("OOPS!!! I'm sorry, but I don't know what that means :-("),
    INCORRECT_BYE ("Did you mean to say 'bye'?"),
    MISSING_DELETE_ITEM ("Please specify an item number."),
    INVALID_ITEM_NUMBER ("That is not a valid item number."),
    ITEM_ALREADY_DONE ("This task has already been marked as done."),
    MISSING_DONE_ITEM ("Please specify an item number."),
    MISSING_EVENT_DESCRIPTION ("The description for a task cannot be empty."),
    MISSING_EVENT_DATE ("The date for an event cannot be empty."),
    MISSING_DEADLINE_DESCRIPTION ("The description for a deadline cannot be empty."),
    MISSING_DEADLINE_DATE ("The date for a deadline cannot be empty."),
    MISSING_TODO_DESCRIPTION ("The description for a todo cannot be empty."),
    INCORRECT_LIST ("Did you mean to say 'list'?");


    private final String message;

    ExceptionTypeEnum(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
