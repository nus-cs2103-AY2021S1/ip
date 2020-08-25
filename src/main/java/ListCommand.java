public class ListCommand extends Command{
    ListCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws InvalidListCommandException {
        String[] tempArray = fullCommand.trim().split(" ");
        if (tempArray.length != 1) {
            throw new InvalidListCommandException();
        }
        ui.listMessage(taskList.toString());
    }
}
