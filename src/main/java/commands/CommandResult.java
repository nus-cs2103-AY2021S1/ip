package commands;

import data.task.Task;

import java.util.List;
import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /** The feedback message to be shown to the user. Contains a description of the execution result. */
    public final String feedbackToUser;

    /** The resulted list of tasks that was produced by the command. */
    public final List<? extends Task> taskList;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.taskList = null;
    }

    public CommandResult(String feedbackToUser, List<? extends Task> taskList) {
        this.feedbackToUser = feedbackToUser;
        this.taskList = taskList;
    }

    /**
     * Returns a list of tasks relevant to the command's command result, if any.
     */
    public Optional<List<? extends Task>> getRelevantTasks() {
        return Optional.ofNullable(taskList);
    }

}
