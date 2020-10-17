import java.io.IOException;

class FindCommand extends Command {
    private final String key;

    /**
     * Constructor for FindCommand
     * @param key Search term to be found
     * @param tasks Existing list of tasks
     * @param ui User interface to be updated
     * @param storage Storage to be updated
     */
    FindCommand(String key, TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
        this.key = key;
    }

    /**
     * Executes the FindCommand
     * @return Returns String of lists of tasks matching search term
     * @throws IOException Exception thrown when updating storage file
     */
    @Override
    public String execute() throws IOException {
        return ui.printf("Here are the matching tasks in your list:\n" + tasks.find(this.key));
    }
}
