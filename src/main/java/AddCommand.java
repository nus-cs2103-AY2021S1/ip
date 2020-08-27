public abstract class AddCommand extends Command {
    protected String taskDescription;
    
    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
