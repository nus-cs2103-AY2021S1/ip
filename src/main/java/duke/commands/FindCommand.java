package duke.commands;

import java.util.List;

import duke.task.Task;
import duke.task.TaskManager;

import duke.utils.Messages;

/**
 * Represents a command which will find {@code Task} that match a keyword the user has inputted.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor to create a new {@code FindCommand} object with its keyword.
     *
     * @param keyword the keyword that is being searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns a {@code CommandOutput} object containing the output from finding the tasks that match the keyword.
     *
     * @param taskManager the {@code TaskManager} object that contains the list of {@code Task}s.
     * @return the output from executing the command.
     */
    @Override
    public CommandOutput executeCommand(TaskManager taskManager) {
        List<Task> filteredTasks = taskManager.findTasksByKeyword(keyword);
        String filteredTasksOutput = outputResult(filteredTasks);
        return new CommandOutput(filteredTasksOutput, false);
    }

    private String outputResult(List<Task> filteredTasks) {
        StringBuilder filteredTasksOutput = new StringBuilder();
        boolean hasFilteredTasks = filteredTasks.size() > 0;
        if (hasFilteredTasks) {
            filteredTasksOutput.append(Messages.FILTERED_TASKS_STARTING_MESSAGE);
            filteredTasks.stream()
                    .map(Task::toString)
                    .map(filteredTaskDescription -> filteredTaskDescription + "\n")
                    .forEach(filteredTasksOutput::append);
        } else {
            filteredTasksOutput.append(Messages.NO_TASKS_UNDER_KEYWORD_MESSAGE);
        }
        return filteredTasksOutput.toString();
    }
}
