public class ByeCommand extends Command{
    public ByeCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException {
        ui.printMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
