public class DeleteCommand extends Command {

    DeleteCommand(String[] stringArray) {
        super(stringArray);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (super.isFirstIndexEmpty()) {
            throw new DukeException("Oops, please enter a task number after your command!");
        }
        try {
            int taskNumber = Integer.parseInt(getFirstIndex());
            if (taskNumber <= 0 || !tasks.isTaskPresent(taskNumber - 1)) {
                throw new DukeException("Oops, enter a task number that already exists in the list. "
                        + "(starting from 1 to " + tasks.totalTask() + ").");
            }
            Task taskToDelete = tasks.getTask(taskNumber - 1);
            tasks.deleteTask(taskNumber - 1);
            ui.showDelete(tasks, taskToDelete);
        } catch (NumberFormatException e) {
            throw new DukeException("Task Number to delete must be an Integer!");
        }
        storage.write(tasks);
    }
}
