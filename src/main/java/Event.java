import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at, Boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public Event markDone() {
        super.markDone();
        return this;
    }

    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.add(this);
        ui.showAddedMessage(tasklist, tasklist.getNumOfTasks() - 1);
        storage.writeData(tasklist.taskList);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))  + ")";
    }

    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "E | " + doneOrNot + " | " + this.name + " | " +
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

}
