import java.time.LocalDateTime;

/**
 * handles the "deadline" commands
 */
public class DeadlineCommand extends Command {

    /**
     * Constructor for DeadlineCommand
     *
     * @param deadlineName
     * @param deadlineDateTime
     * @throws IllegalArgumentException
     */
    public DeadlineCommand(String deadlineName, LocalDateTime deadlineDateTime) throws IllegalArgumentException {
        super(deadlineName, deadlineDateTime);
    }

    /**
     * shows the deadline task added, updates the total number of tasks
     *
     * @param taskList
     */
    public void execute(TaskList taskList) {
        Deadline newDeadline = new Deadline(this.getTaskName(), this.getTaskDateTime());
        taskList.addTask(newDeadline);
        String deadlineTask = newDeadline.toString();
        TextUi.printNewTasks(deadlineTask);
        TextUi.printTaskSummary(taskList.getTaskLength());
    }
}
