/**
 * find task matching to a keyword
 */
public class FindCommand extends Command{
    public String keyword;

    /**
     * FindCommand constructor
     * @param keyword the keyword to be matched
     */
    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    /**
     * Execute the command
     * @param inputTasks the list of tasks used
     * @param storage the storage used
     * @throws DukeException throws exceptions that fail to fulfil command requirements
     */
    public void execute(TaskList inputTasks, Storage storage, Ui ui) throws DukeException{
        inputTasks.findKeyword(keyword, ui);
    }
}
