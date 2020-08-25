public class DeleteCommand extends Command {

    public DeleteCommand(String description) throws IllegalArgumentException {
        super(description); //the task number to delete
    }

    public void execute(TaskList taskList) {
        int taskNumber;
        taskNumber = Integer.parseInt(this.getTaskName());
        Task deletedTask = taskList.getTask(taskNumber);
        taskList.removeTask(deletedTask);
        System.out.println(deletedTask + "\n" + TextUi.DIVIDER);
    }
}
