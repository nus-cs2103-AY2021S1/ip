public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String task = tasks.delete(index);
        ui.say("Deleted this task:\n" + task);
    }
}
