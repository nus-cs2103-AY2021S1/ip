public class DeleteCommand extends Command {
    private final String fullCommand;

    public DeleteCommand (String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        try {
            String taskNumberToDelete = this.fullCommand.substring(7);
            int index = Integer.parseInt(taskNumberToDelete) - 1;
            Task deleteTask = tasklist.getItem(index);
            tasklist.deleteItem(index);
            ui.deleteMessage(deleteTask, tasklist.getTasksLeft());

            // change data file
            storage.deleteTask(index);

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a task number within the range of tasks");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number for me to delete");
        }
    }
}
