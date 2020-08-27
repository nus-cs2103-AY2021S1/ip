public abstract class AddCommand extends Command {
    String taskDescription;
    
    AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    
    @Override
    boolean isExit() {
        return false;
    }
}
