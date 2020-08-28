/**
 * handles "done" Commands
 */
public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand
     *
     * @param description
     * @throws IllegalArgumentException
     */
    public DoneCommand(String description) throws IllegalArgumentException {
        super(description); //the task number to mark it as done
    }

    /**
     * marks the tasks as done and shows the done tasks
     *
     * @param taskList
     */
    public void execute(TaskList taskList) {
        int taskNumber;
        taskNumber = Integer.parseInt(this.getTaskName());
        Task doneTask = taskList.getTask(taskNumber);
        doneTask.markAsDone();
        TextUi.printMessage("Nice! I've marked this task as done: " + doneTask);
    }
}

