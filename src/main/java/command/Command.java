package command;

import java.io.IOException;

import exception.AnonymousException;
import exception.DescriptionException;
import exception.DukeDateTimeParserException;
import exception.DukeInvalidIndexException;
import exception.DukeKeywordException;
import exception.NoIndexException;
import storage.Storage;
import task.TaskList;
import ui.Ui;



/**
 * The Command class implements methods that will be used
 * by its child class to create various Command type.
 * It also contains strings of commands that specify
 * the command type.
 */
public abstract class Command {
    public static final String DONE_COMMAND = "done";
    public static final String DELETE_COMMAND = "delete";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String EXIT_COMMAND = "bye";
    public static final String FIND_COMMAND = "find";
    public static final String HELP_COMMAND = "--help";
    public static final String LIST_COMMAND = "list";
    public static final String TODO_COMMAND = "todo";
    public static final String SHOW_AFTER_COMMAND = "show after";
    public static final String SHOW_BEFORE_COMMAND = "show before";
    public static final String DELETE_ALL_COMMAND = "delete all";
    public static final String DONE_ALL_COMMAND = "done all";

    private boolean isExit;

    /**
     * An empty constructor to initialize the Command object,
     * and initialize isExit fields to false.
     * isExit would be used to terminate the program when it is false.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * A method template for child's classes to implement. It would execute any
     * action needed based on the type of the command.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     * @throws IOException Thrown when the system failed to detect the external file.
     * @throws AnonymousException Thrown whenever Parser failed to detect
     * valid command from user.
     * @throws DescriptionException Thrown whenever Parsed failed to detect
     * valid description passed in the command.
     * @throws DukeDateTimeParserException Thrown whenever when Parser to detect
     * valid date-time details on the command
     * @throws NoIndexException Thrown whenever Parser failed to detect valid index
     * that should be specified in the command.
     * @throws DukeKeywordException Thrown when user failed to specify the keyword in the command.
     */
    public abstract String execute (TaskList tasks, Ui ui, Storage storage) throws IOException, AnonymousException,
            DescriptionException, DukeDateTimeParserException, NoIndexException, DukeKeywordException,
            DukeInvalidIndexException;

    /**
     * A getter method that returns the state whether a program is ready to exit or not.
     *
     * @return boolean Returns true if the type of command is ExitCommand, and false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * A setter method to set the state to true when the program is ready to be terminated.
     *
     * @param exit boolean true if the program is set to be terminated, and false otherwise.
     */
    public void setExit(boolean exit) {
        isExit = exit;
    }
}
