package duke.command;

import static duke.util.Keyword.KEYWORD_MATCHING;

import duke.exception.EmptyFindException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Executes the finding of matching word in the task descriptions.
 */
public class FindCommand extends Command {

    private final String input;

    /**
     * Initializes the FindCommand class to look for the matching word.
     *
     * @param input Input string.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds all matching tasks based on the given user input.
     *
     * @param taskList TaskList object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Response message to user.
     * @throws EmptyFindException If input string succeeding find is empty.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyFindException {
        if (input.isEmpty()) {
            throw new EmptyFindException();
        }
        TaskList outputList = taskList.matchAll(input);
        if (outputList.isEmpty()) {
            return ui.emptyFind(input);
        } else {
            return ui.showTaskList(outputList, KEYWORD_MATCHING);
        }
    }
}
