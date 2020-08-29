package duke.commands;

import static duke.utils.Messages.MESSAGE_FIND;
import static duke.utils.Messages.MESSAGE_FIND_NO_MATCH;

import java.util.ArrayList;

import duke.tasklist.TaskList;
import duke.tasks.Task;

/** Represents the command that displays all tasks that match the user's search words
 * when executed.
 */
public class FindCommand extends Command {

    private String[] searchWords;

    /** Constructs a FindCommand with the specified search words.
     *
     * @param searchWords The search words that the user input.
     */
    public FindCommand(String ... searchWords) {
        this.searchWords = searchWords;
    }

    /** Return CommandResult containing all tasks whose description contains the user's search words.
     *
     * @param taskList The taskList involved.
     * @return The result of the command.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        ArrayList<Task> allTasks = taskList.getTasks();
        for (Task task : allTasks) {
            if (matchesAllWords(task)) {
                matchedTasks.add(task);
            }
        }
        String response;
        if (matchedTasks.size() == 0) {
            response = MESSAGE_FIND_NO_MATCH;
        } else {
            response = ListCommand.tasksToString(matchedTasks, MESSAGE_FIND);
        }
        return new CommandResult(response, false);
    }

    private boolean matchesAllWords(Task task) {
        for (String word : searchWords) {
            if (!(task.getDescription().contains(word))) {
                return false;
            }
        }
        return true;
    }
}
