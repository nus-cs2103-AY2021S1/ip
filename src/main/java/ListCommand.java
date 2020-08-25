public class ListCommand extends Command {

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        ui.displayTasks(manager.getTasks());
    }
    
}