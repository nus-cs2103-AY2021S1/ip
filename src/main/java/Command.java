import java.time.LocalDateTime;

public class Command {
    private String taskName;
    private LocalDateTime taskDateTime;

    public Command(String taskName) {
        this.taskName = taskName;
    }

    public Command(String taskName, LocalDateTime taskDateTime) {
        this.taskName = taskName;
        this.taskDateTime = taskDateTime;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public LocalDateTime getTaskDateTime() {
        return this.taskDateTime;
    }

    public void execute(TaskList taskList) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
