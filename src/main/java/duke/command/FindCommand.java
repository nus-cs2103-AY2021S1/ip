package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.NoKeywordException;

/** A command to find tasks with a given keyword. */
public class FindCommand extends Command {

    /** The keyword to find in tasks. */
    private String keyword;

    /**
     * Constructs a FindCommand.
     *
     * @param fullCommand The input given by the user.
     * @throws NoKeywordException If the fullCommand has no keyword following find command.
     */
    public FindCommand(String fullCommand) throws NoKeywordException {
        try {
            this.keyword = fullCommand.substring("find".length() + 1).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoKeywordException();
        }
    }

    /**
     * Executes the command by finding tasks with the given keyword.
     *
     * @param taskList The task list that adds or deletes task in the task list.
     * @param ui       The UI for the bot.
     * @param storage  The storage for saving tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.findTasks(keyword);
    }
}
