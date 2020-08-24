public abstract class AbstractModifyTaskCommand extends Command {
    protected final Task task;

    // Contains DONE and DELETE commands since they modify tasks in list
    public AbstractModifyTaskCommand(Task task) {
        this.task = task;
    }
}
