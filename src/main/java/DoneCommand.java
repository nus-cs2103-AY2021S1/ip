/**
 * handles "done" Commands
 */
public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand
     *
     * @param taskNumber
     * @throws IllegalArgumentException
     */
    public DoneCommand(String taskNumber) throws IllegalArgumentException {
        super(taskNumber);
    }

    /**
     * marks the tasks as done and shows the done tasks
     *
     * @param taskList
     */
    public String execute(TaskList taskList) {
        int taskNumber;
        taskNumber = Integer.parseInt(this.getTaskName());
        assert taskNumber != 0;
        Task doneTask = taskList.getTask(taskNumber);
        doneTask.markAsDone();
        return TextUi.printMessage("Nice! I've marked this task as done:\n" + doneTask);
    }
}

