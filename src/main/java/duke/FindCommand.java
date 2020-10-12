package duke;

/**
 * Command to find tasks by keywords
 */
public class FindCommand extends Command {
    private final String input;

    /**
     * FindCommand constructor
     *
     * @param input
     */
    public FindCommand(String input) {
        super(false);
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.search(tasks, input);
    }
}
