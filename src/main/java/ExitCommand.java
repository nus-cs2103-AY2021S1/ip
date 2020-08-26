public class ExitCommand extends Command {
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
        storage.save(tasks);
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
