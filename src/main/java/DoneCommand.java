public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList list) throws InvalidTaskException, StorageException {
        list.completeTask(this.taskIndex);
    }
}
