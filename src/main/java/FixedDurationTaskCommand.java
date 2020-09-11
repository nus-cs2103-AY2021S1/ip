public class FixedDurationTaskCommand extends Command {
    public FixedDurationTaskCommand(String taskName, String duration) throws IllegalArgumentException {
        super(taskName, duration);
    }

    public String execute(TaskList taskList) {
        FixedDurationTask newTask = new FixedDurationTask(this.getTaskName(), this.getDuration());
        taskList.addTask(newTask);
        return TextUi.printNewTasks(newTask.toString()) + "\n" + TextUi.printTaskSummary(taskList.getTaskLength());
    }
}
