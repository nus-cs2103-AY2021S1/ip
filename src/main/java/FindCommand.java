public class FindCommand extends Command {

    private String search;

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.findTasks(search);
        ui.printOutput(output, false);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
