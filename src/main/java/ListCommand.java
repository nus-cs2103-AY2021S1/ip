public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException{
        ui.getList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
