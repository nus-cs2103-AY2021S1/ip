package duke;

public class DeleteCommand extends Command {
    int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.deleteTask(taskIndex);

        ui.printDivider();
        ui.printMsg("Mr Camel will delete this task:\n");
        ui.printMsg("\t" + t);
        ui.printMsg("Number of tasks: " + tasks.getTasklist().size());
        ui.printDivider();

        super.execute(tasks, ui, storage);
    }
}
