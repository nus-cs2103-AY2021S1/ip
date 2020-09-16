package duke;

/**
 * Finds tasks with the given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * FindCommand constructor.
     *
     * @param keyword keyword to be found.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        taskList.find(keyword, ui);
    }
}
