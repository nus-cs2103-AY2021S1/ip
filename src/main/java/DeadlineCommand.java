import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String deadlineName, LocalDateTime deadlineDateTime) throws IllegalArgumentException {
        super(deadlineName, deadlineDateTime);
    }

    public void execute(TaskList taskList) {
            Deadline newDeadline = new Deadline(this.getTaskName(), this.getTaskDateTime());
                taskList.addTask(newDeadline);
                System.out.println(newDeadline +
            String.format("\nNow you have %d tasks in the list.\n", taskList.getTaskLength())
            + TextUi.DIVIDER);
    }
}
