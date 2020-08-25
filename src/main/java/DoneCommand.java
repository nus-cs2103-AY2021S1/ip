public class DoneCommand extends Command{
    final static String COMMAND = "done";
    
    private int index;

    DoneCommand(String string) throws InvalidIndexException {
        try {
            index = Integer.parseInt(string) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        try {
            tasks.markAsDone(index);
            ui.showMarkAsDoneTask(tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }
}
