public class DeleteCommand extends Command {
    final static String COMMAND = "delete";
    private int index;
    
    DeleteCommand(String string) throws InvalidIndexException {
        try {
            index = Integer.parseInt(string) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        try {
            ui.showDeleteTask(tasks.remove(index), tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }
}
