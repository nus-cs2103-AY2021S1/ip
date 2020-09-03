package duke.commands;

import static duke.utils.Messages.MESSAGE_FIND;
import static duke.utils.Messages.MESSAGE_FIND_NO_MATCH;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.tasklist.TaskList;
import duke.tasks.Task;

/**
 * Represents the command that displays all tasks that match the user's search words
 * when executed.
 */
public class FindCommand extends Command {

    private String[] searchWords;

    /**
     * Constructs a FindCommand with the specified search words.
     *
     * @param searchWords The search words that the user input.
     */
    public FindCommand(String ... searchWords) {
        this.searchWords = searchWords;
    }

    /**
     * Return CommandResult containing all tasks whose description contains the user's search words.
     *
     * @param taskList The taskList involved.
     * @return The result of the command.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        List<Task> matchedTasks = getMatchedTasks(taskList);
        String response = getResponse(matchedTasks);
        return new CommandResult(response, false);
    }

    private String getResponse(List<Task> matchedTasks) {
        if (matchedTasks.isEmpty()) {
            return MESSAGE_FIND_NO_MATCH;
        } else {
            return ListCommand.tasksToString(matchedTasks, MESSAGE_FIND);
        }
    }

    private List<Task> getMatchedTasks(TaskList taskList) {
        return taskList.getTasks()
                .stream()
                .filter(this::matchesAllWords)
                .collect(Collectors.toList());
    }

    private boolean matchesAllWords(Task task) {
        return Arrays.stream(searchWords)
                .allMatch(word -> task.getDescription().contains(word));
    }
}
