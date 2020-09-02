package src.main.java.duke.commands;

import java.util.List;
import java.util.Optional;

import src.main.java.duke.data.task.Task;

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

    /**
     * Constructor for command result with feedback to user
     * @param feedbackToUser message to the user
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantTasks = null;
    }

    /**
     * Constructor for command result with feedback to user and list of relevant tasks
     * @param feedbackToUser message to the user
     * @param relevantTasks relevant tasks list
     */
    public CommandResult(String feedbackToUser, List<Task> relevantTasks) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTasks = relevantTasks;
    }

    /**
     * Returns a list of tasks relevant to the command command result, if any.
     * @return return a list of tasks which may be empty
     */
    public Optional<List<Task>> getRelevantTasks() {
        return Optional.ofNullable(relevantTasks);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }
}
