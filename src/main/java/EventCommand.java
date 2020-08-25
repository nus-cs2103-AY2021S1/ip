import org.w3c.dom.Text;

import java.time.LocalDateTime;

public class EventCommand extends Command {

    public EventCommand(String deadlineName, LocalDateTime deadlineDateTime) throws IllegalArgumentException {
        super(deadlineName, deadlineDateTime);
    }

    public void execute(TaskList taskList) {
        Event newEvent = new Event(this.getTaskName(), this.getTaskDateTime());
        taskList.addTask(newEvent);
        System.out.println(newEvent +
                String.format("\nNow you have %d tasks in the list.\n", taskList.getTaskLength())
                + TextUi.DIVIDER);
    }
}
