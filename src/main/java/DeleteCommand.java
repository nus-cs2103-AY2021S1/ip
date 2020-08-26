public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList list) throws InvalidTaskException, StorageException {
        list.deleteTask(index);
    }
}
