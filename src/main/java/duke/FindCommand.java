package duke;

/**
 * Find tasks with the given keyword.
 */
public class FindCommand extends Command {
    String keyword;

    /**
     * FindCommand constructor.
     *
     * @param keyword keyword to be found.
     */
    public FindCommand(String keyword){
        this.keyword =keyword;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.find(keyword);
    }
}
