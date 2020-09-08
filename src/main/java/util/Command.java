package util;

/**
 * Represents the type of commands Duke can do.
 *
 * Commands:
 * INVALID - Unrecognized commands.
 * EXIT - 'bye' that ends Duke's session.
 * LIST - 'list' which lists Duke's recorded tasks.
 * DONE - Helps mark task as done.
 * DELETE - Indicates task to be removed.
 * FIND - Identifies tasks containing given keyword.
 * UPDATE - Updates task description with given string.
 * Types of task that can be made:
 * DEADLINE, EVENT, TO DO
 * */
public enum Command {
    INVALID, EXIT, LIST,
    DONE, DELETE, FIND, UPDATE,
    TODO, DEADLINE, EVENT
}
