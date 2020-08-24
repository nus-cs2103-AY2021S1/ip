package duke;

public class DoneCommand extends Command{
    int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.doneTask(taskIndex);

        ui.printDivider();
        ui.printMsg("Mr Camel will mark this task as done:\n");
        ui.printMsg("\t" + t);
        ui.printDivider();

        super.execute(tasks, ui, storage);
    }
}
