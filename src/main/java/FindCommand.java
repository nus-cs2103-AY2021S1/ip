public class FindCommand extends Command {

    // Attributes
    private final String substring;

    // Constructor
    public FindCommand(String substring) {
        this.substring = substring;
    }


    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.findTasks(this.substring);
        ui.showMessage("Here are your tasks matching " + this.substring);
        ui.showMessage(filteredTasks.toString());
    }

    @Override
    boolean isExit() {
        return false;
    }
}
