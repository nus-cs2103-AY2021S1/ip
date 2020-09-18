package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command.
 *
 * @author Tee Kok Siang
 */
public abstract class Command {
    /** Indicates a ByeCommand for exiting application*/
    public static final String BYE_COMMAND = "bye";
    /** Indicates a AddCommand for adding a Deadline task */
    public static final String DEADLINE_COMMAND = "deadline";
    /** Indicates a DeleteCommand for deleting a Task */
    public static final String DELETE_COMMAND = "delete";
    /** Indicates a DoneCommand for completing a Task */
    public static final String DONE_COMMAND = "done";
    /** Indicates a AddCommand for adding a Event task */
    public static final String EVENT_COMMAND = "event";
    /** Indicates a FindCommand for search a Task */
    public static final String FIND_COMMAND = "find";
    /** Indicates a AddCommand for listing a TaskList */
    public static final String LIST_COMMAND = "list";
    /** Indicates a AddCommand for adding a Todo task */
    public static final String TODO_COMMAND = "todo";
    /** Indicates a PriorityCommand for prioritising a task */
    public static final String PRIORITY_COMMAND = "priority";
    /** Space character for separating a Command */
    public static final String SPLIT_DELIMITER = " ";
    /** Minimum number of word count for a Command that contains task number */
    public static final int TASK_NUMBER_COMMAND_WORD_COUNT = 2;
    /** Indicates the position of task number in the command */
    public static final int TASK_NUMBER_POSITION = 1;

    /** Indicates if this is an exit command */
    protected boolean isExit;
    /** Task for AddCommand */
    protected Task task;

    /**
     * Constructs a Command object.
     */
    protected Command() {
        this.isExit = false;
    }

    /**
     * Constructs a Command object.
     *
     * @param isExit Indicates if this is an exit command.
     */
    protected Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns isExit.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns task.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Executes command.
     * An abstract method to be overridden by the subclasses.
     *
     * @param taskList List of tasks.
     * @param ui UI to handle user interaction.
     * @param storage Storage to save the task list in the hard disk.
     * @return Formatted response message.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
