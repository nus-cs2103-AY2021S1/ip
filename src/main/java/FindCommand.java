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
     */
<<<<<<< HEAD
    public void execute(TaskList inputTasks, Storage storage, Ui ui) throws DukeException{
        inputTasks.findKeyword(keyword, ui);
=======
    public void execute(TaskList inputTasks, Storage storage) throws DukeException {
        inputTasks.findKeyword(keyword);
>>>>>>> master
    }
}
