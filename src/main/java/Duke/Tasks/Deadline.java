package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public Deadline markDone() {
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
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "D | " + doneOrNot + " | " + this.name + " | " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

}
