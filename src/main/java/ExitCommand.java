public class ExitCommand extends Command {
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ExitCommand) {
            return true;
        } else {
            return false;
        }
    }
}
