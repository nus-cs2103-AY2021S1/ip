public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task removedTask = tasks.get(taskNumber);
            tasks.deleteTask(taskNumber);
            storage.save(tasks);
            ui.display("Noted. I've removed this task:\n" + "  "
                    + removedTask.toDisplayString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("Please enter a valid task number!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
