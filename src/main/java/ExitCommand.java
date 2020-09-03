public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        ui.showExit();
        storage.storeTaskList(taskList);
    }

    @Override
    public Boolean isExit() {
        return true;
    }
}
