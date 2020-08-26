public class DeleteCommand extends Command {
    public int index;
    
    public DeleteCommand(int index) {
        this.index = index;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.taskListLength()) {
            throw new DukeException("ERROR: Task does not exist");
        }
        Task deletedTask = tasks.deleteTask(index);
        ui.showDeletedTask(deletedTask, tasks.taskListLength());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
