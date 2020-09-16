package duke.commands;

import java.util.List;
import java.util.Optional;

import duke.data.task.Task;

/**
 * Represents the result of a command execution.
 */
@SuppressWarnings("ALL")
public class CommandResult {

    /** The feedback message to be shown to the user. Contains a description of the execution result. */
    public final String feedbackToUser;

    /** The resulted list of tasks that was produced by the command. */
    public final List<? extends Task> taskList;

    private boolean exit = false;

    /**
     * Constructs a {@code CommandResult}.
     * @param feedbackToUser The message to be shown to the user.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.taskList = null;
    }

    /**
     * Constructs a {@code CommandResult}.
     * @param feedbackToUser The message to be shown to the user.
     * @param isExit True if the command was an exit command, false otherwise.
     */
    public CommandResult(String feedbackToUser, boolean exit) {
        this.feedbackToUser = feedbackToUser;
        this.taskList = null;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult}.
     * @param feedbackToUser The message to be shown to the user.
     * @param taskList The updated task list after a command is executed.
     */
    public CommandResult(String feedbackToUser, List<? extends Task> taskList) {
        this.feedbackToUser = feedbackToUser;
        this.taskList = taskList;
    }

    /**
     * Returns Duke's response from the command executed.
     */
    public String getDialog() {
        return feedbackToUser;
    }

    /**
     * Returns {@code True} if the command is an exit command.
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Returns a list of tasks relevant to the command's command result, if any.
     */
    public Optional<List<? extends Task>> getRelevantTasks() {
        return Optional.ofNullable(taskList);
    }

}
