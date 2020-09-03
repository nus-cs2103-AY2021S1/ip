public class ExitCommand extends Command {
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = ui.showExit();
        
        return output;
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
