package duke;

/**
 * This is the enum class to store the types of
 * commands a user can input to Duke.
 */
public enum CommandType {
    HELP,
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    LIST_ALL,
    LIST_ALL_DONE,
    LIST_ALL_NOT_DONE,
    LIST_TODOS,
    LIST_TODOS_DONE,
    LIST_TODOS_NOT_DONE,
    LIST_DEADLINES,
    LIST_DEADLINES_DONE,
    LIST_DEADLINES_NOT_DONE,
    LIST_EVENTS,
    LIST_EVENTS_DONE,
    LIST_EVENTS_NOT_DONE,
    LIST_BY_KEYWORD,
    DONE,
    DELETE,
    BYE,
    INVALID_IS_EMPTY,
    INVALID_COMMAND,
    INVALID_EMPTY_DESCRIPTION,
    INVALID_DEADLINE_NO_BY,
    INVALID_EVENT_NO_START_END
}
