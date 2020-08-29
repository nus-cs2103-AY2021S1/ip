public class ExitCommand extends Command {
    @Override
    void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.exit();
    }
    
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
