package duke.command;

import java.util.StringJoiner;

import duke.DukeException;
import duke.TaskList;

/**
 * Command to find all the tasks which matches the keywords provided.
 */
public class FindCommand implements Command {

    private final String fullCommand;
    private final TaskList taskList;

    /**
     * Initialise find command with the keyword(s) to search
     * and the task list to search.
     * @param keywords The phrase or word to search.
     * @param taskList The list of the user's tasks.
     */
    public FindCommand(String keywords, TaskList taskList) {
        this.fullCommand = keywords;
        this.taskList = taskList;
    }

    @Override
    public String executeWithResponse() throws DukeException {
        if (fullCommand.substring(4).length() <= 1) {
            throw new DukeException("Invalid keyword(s) specified for find command.");
        } else {
            StringJoiner responseBuilder = new StringJoiner("\n");
            String description = fullCommand.substring(5);
            String matchingTasks = taskList.getMatchingTasks(description);
            if (matchingTasks.length() > 0) {
                responseBuilder.add("Here are the matching tasks in your list:");
                responseBuilder.add(matchingTasks);
            } else {
                responseBuilder.add("No tasks matching your description was found.");
            }
            return responseBuilder.toString();
        }
    }

    @Override
    public boolean continueDuke() {
        return true;
    }
}
