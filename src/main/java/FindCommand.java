import java.io.IOException;

class FindCommand extends Command {
    private String key;

    FindCommand(String key) {
        this.key = key;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return ui.printf("Here are the matching tasks in your list:\n" + tasks.find(this.key));
    }
}
