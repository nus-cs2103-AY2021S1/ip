public class AddCommand extends Command {
	Task taskToAdd;

	public AddCommand(Task taskToAdd) {
		this.taskToAdd = taskToAdd;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		tasks.addTask(taskToAdd);
		String stringToWriteToStorage = convertTaskListToSaveFormat(tasks);
		storage.updateStorage(stringToWriteToStorage);
		ui.printMessage("Got it. I've added this task:\n" +
				taskToAdd.toString() + "\n" +
				"Now you have " + tasks.numberOfTasks() + " tasks in the list.");
	}
}
