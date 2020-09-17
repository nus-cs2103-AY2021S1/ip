package duke.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import duke.data.task.Task;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " :\nFinds all tasks whose description contain any of "
            + "the specified keywords (case-sensitive) and \n"
            + "displays them as a list with index numbers.\n"
            + "  Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "  Example: " + COMMAND_WORD + " book return meeting";

    public static final String MESSAGE_SUCCESS = "We have found %1$d task(s) related to your keyword(s).";

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns a copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<Task> tasksFound = getTasksWithNameContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForFindingTaskSummary(tasksFound), tasksFound);
    }

    public static String getMessageForFindingTaskSummary(List<Task> taskList) {
        return String.format(MESSAGE_SUCCESS, taskList.size());
    }

    /**
     * Retrieves all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<Task> getTasksWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<Task> matchedTasks = new ArrayList<>();
        for (Task task : super.taskList.getList()) {
            final Set<String> wordsInName = new HashSet<>(task.getWordsInDescription());
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

}
