package commands;

import data.task.Task;

import java.util.List;
import java.util.Optional;

// Represents the result of a command execution.
public class CommandResult {

    public final String feedbackToUser;
    public final List<? extends Task> taskList;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.taskList = null;
    }

    public CommandResult(String feedbackToUser, List<? extends Task> taskList) {
        this.feedbackToUser = feedbackToUser;
        this.taskList = taskList;
    }

    public Optional<List<? extends Task>> getRelevantTasks() {
        return Optional.ofNullable(taskList);
    }

}
