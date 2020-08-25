package Duke.commands;

import java.util.List;
import java.util.Optional;

import Duke.data.task.Task;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /**
     * The feedback message to be shown to the user. Contains a description of the
     * execution result
     */
    public final String feedbackToUser;

    /** The list of tasks that was produced by the command */
    private final List<Task> relevantTasks;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantTasks = null;
    }

    public CommandResult(String feedbackToUser, List<Task> relevantTasks) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTasks = relevantTasks;
    }

    /**
     * Returns a list of tasks relevant to the command command result, if any.
     */
    public Optional<List<Task>> getRelevantTasks() {
        return Optional.ofNullable(relevantTasks);
    }

}
