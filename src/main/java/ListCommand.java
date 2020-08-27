public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.listTasks();
        ui.printOutput(output, false);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
