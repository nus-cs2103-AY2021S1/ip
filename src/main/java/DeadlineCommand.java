public class DeadlineCommand extends Command {
    private String taskName;
    private String deadlineString;

    public DeadlineCommand(String taskName, String deadlineString) {
        this.taskName = taskName;
        this.deadlineString = deadlineString;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (taskName.isBlank()) {
            throw DukeException.badDeadlineTask();
        } else if (deadlineString.isBlank()) {
            throw DukeException.badDeadlineDate();
        }

        Task task = new Deadline(taskName, deadlineString);
        tasks.addTask(task);
        ui.displayAddTask(task, tasks.numTasks());
    }
}
