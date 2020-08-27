public class DoneCommand extends Command {

    public int index;

    public DoneCommand(int i) {
        index = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlisonException {
        try {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            ui.markDoneMsg(task);
            storage.update(tasks);
        } catch (Exception e) {
            throw AlisonException.invalidIndexException();
        }
    }

}
