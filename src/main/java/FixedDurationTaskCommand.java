public class FixedDurationTaskCommand extends Command {
    public FixedDurationTaskCommand(String taskName, String duration) throws IllegalArgumentException {
        super(taskName, duration);
    }
    /**
     * shows the fixed duration task added, updates the total number of tasks
     *
     * @param taskList
     */
    public String execute(TaskList taskList) {
        FixedDurationTask newTask = new FixedDurationTask(this.getTaskName(), this.getDuration());
        taskList.addTask(newTask);
        return TextUi.printNewTasks(newTask.toString()) + "\n" + TextUi.printTaskSummary(taskList.getTaskLength());
    }
}
