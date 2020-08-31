package duke;

public class FindCommand extends Command {
    private final String input;
    public FindCommand(String input) {
        super(false);
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.search(tasks, input);
    }
}
