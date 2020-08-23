public class DoneCommand extends Command {
	int indexOfTask;

	public DoneCommand(int indexOfTask) {
		this.indexOfTask = indexOfTask;
	}
	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
		Task taskToSetDone = taskList.getTask(indexOfTask);
		taskList.setTaskDone(indexOfTask);
		storage.updateStorage(convertTaskListToSaveFormat(taskList));
		ui.printMessage("Nice! I've marked this task as done:\n" +
				taskToSetDone.toString());
	}
}
