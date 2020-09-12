import java.io.IOException;

class FindCommand extends Command {
    private String key;

    FindCommand(String key, TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
        this.key = key;
    }

    @Override
    public String execute() throws IOException {
        return ui.printf("Here are the matching tasks in your list:\n" + tasks.find(this.key));
    }
}
