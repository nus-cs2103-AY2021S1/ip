/**
 * Represents a DukeExceptionType, which is an enum to keep track of different DukeExceptions.
 */
public enum DukeExceptionType {
    EMPTY_TASK_TODO("☹ OOPS!!! The description of a todo cannot be empty."),
    EMPTY_TASK_EVENT_DEADLINE("☹ OOPS!!! The description of a event/deadline cannot be empty."),
    INVALID_INPUT("☹ OOPS!!! I'm sorry, but I don't know what that means :-("),
    EMPTY_DATE("☹ OOPS!!! The date of a event/deadline cannot be empty."),
    TASK_NOT_FOUND("☹ OOPS!!! The task you are trying to delete/mark as done is not found.");

    private String error;
    DukeExceptionType(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
