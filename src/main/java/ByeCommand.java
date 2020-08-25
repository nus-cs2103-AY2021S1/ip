public class ByeCommand extends Command {

    ByeCommand(String fullCommand) {
       super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws InvalidByeCommandException {
        String[] tempArray = fullCommand.trim().split(" ");
        if (tempArray.length != 1) {
            throw new InvalidByeCommandException();
        }
        ui.byeMessage();
    }
}
