package clippy.command;

public abstract class UpdateCommand extends Command {
    protected final int indexOfTaskToUpdate;
    
    public UpdateCommand(int indexOfTaskToUpdate) {
        this.indexOfTaskToUpdate = indexOfTaskToUpdate;
    }

}
