/**
 * handles the "delete" commands
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand
     *
     * @param description
     * @throws IllegalArgumentException
     */
    public DeleteCommand(String description) throws IllegalArgumentException {
        super(description); //the task number to delete
    }

    /**
     * delete the tasks
     *
     * @param taskList
     */
    public void execute(TaskList taskList) {
        int taskNumber;
        taskNumber = Integer.parseInt(this.getTaskName());
        Task deletedTask = taskList.getTask(taskNumber);
        taskList.removeTask(deletedTask);
        System.out.println(deletedTask + "\n" + TextUi.divider);
    }
}
