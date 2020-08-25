public class ExitCommand extends Command{

    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print("     Bye. Hope to see you again!");
        ui.showLine();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
