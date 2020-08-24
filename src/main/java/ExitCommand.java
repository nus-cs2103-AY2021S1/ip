public class ExitCommand extends Command {

    ExitCommand(String[] stringArray) {
        super(stringArray);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //Empty as program will exit.
    }

    @Override
    public boolean isExit() {
        return true;
    };
}
