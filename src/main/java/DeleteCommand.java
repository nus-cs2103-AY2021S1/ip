/**
 * handles the "delete" commands
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand
     *
     * @param taskNumber
     * @throws IllegalArgumentException
     */
    public DeleteCommand(String taskNumber) throws IllegalArgumentException {
        super(taskNumber);
    }

    /**
     * delete the tasks
     *
     * @param taskList
     */
    public String execute(TaskList taskList) {
        int taskNumber;
        taskNumber = Integer.parseInt(this.getTaskName());
        assert taskNumber != 0;
        Task deletedTask = taskList.getTask(taskNumber);
        taskList.removeTask(deletedTask);
        return TextUi.printMessage("Noted. I've removed this task:\n" + deletedTask);
    }
}
