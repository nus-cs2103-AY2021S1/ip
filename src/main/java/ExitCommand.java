public class ExitCommand extends Command{
    final static String COMMAND = "bye";
    
    ExitCommand() {
        super();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.write(tasks);
        ui.showGoodbye();
    }
}
