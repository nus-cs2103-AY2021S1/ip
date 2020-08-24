package duke;

public class EventCommand extends ToDoCommand {
    EventCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        super.execute(taskItems, ui, storage);
    }
}
