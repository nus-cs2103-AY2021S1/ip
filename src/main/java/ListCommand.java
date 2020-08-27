public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (Task.totalTasks == 0) {
            throw new DukeException("You don't have any tasks yet!");
        }

        ui.showList();
        tasks.printList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
