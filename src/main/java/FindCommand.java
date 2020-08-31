public class FindCommand extends Command {

    private String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printResultTaskList(taskList.generateResultTaskList(description));
    }

    @Override
    public boolean isInProgram() {
        return true;
    }
}
