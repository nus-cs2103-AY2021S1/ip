package duke;

public class ExitCommand extends Command {
    
    @Override
    public boolean isExit() {
        return true;
    }
    
    @Override
    public String execute(TaskList taskList, Ui ui, Storage store) {
        return ui.exit();
    }
}
