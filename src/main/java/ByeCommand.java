public class ByeCommand extends Command {
    
    ByeCommand(String[] inputArr) {
        super(inputArr);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
        storage.record(tasks);
        setExitStatus(true);
    }
}
