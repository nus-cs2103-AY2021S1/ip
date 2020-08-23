import java.time.LocalDate;

public class DateListCommand extends Command {
	LocalDate localDate;

	public DateListCommand(LocalDate localDate) {
		this.localDate = localDate;
	}

	@Override
	public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
		StringBuilder stringBuilder = new StringBuilder();
		int numberOfTasksFound = 0;
		stringBuilder.append("Here are the tasks with the date: " + localDate.toString() + "\n");

		for (int i = 0; i < taskList.numberOfTasks(); i++) {
			Task currentTask = taskList.getTask(i);
			if (currentTask instanceof Deadline) {
				Deadline deadline = (Deadline) currentTask;
				if (deadline.getLocalDate().equals(localDate)) {
					stringBuilder.append((i + 1) + ". " + deadline.toString() + "\n");
					numberOfTasksFound++;
				}
			} else if (currentTask instanceof Event) {
				Event event = (Event) currentTask;
				if (event.getLocalDate().equals(localDate)) {
					stringBuilder.append((i + 1) + ". " + event.toString() + "\n");
					numberOfTasksFound++;
				}
			}
		}
		if (numberOfTasksFound > 0) {
			ui.printMessage(stringBuilder.toString());
		} else {
			ui.printMessage("There are no tasks with the date: " + localDate.toString());
		}
	}

}
