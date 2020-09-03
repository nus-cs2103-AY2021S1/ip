package duke;

public class FindCommand extends Command {

    private String search;

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.findTasks(search);
        return ui.printOutput(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
