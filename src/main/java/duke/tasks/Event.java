package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent a event task.
 */
public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at, Boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Mark the event as done.
     * @return
     */
    @Override
    public Event markDone() {
        super.markDone();
        return this;
    }

    /**
     * Add the task into the list and print the added message.
     * Write the changes into the file.
     * @param tasklist
     * @param ui
     * @param storage
     */
    @Override
    public void excute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.add(this);
        ui.showAddedMessage(tasklist, tasklist.getNumOfTasks() - 1);
        storage.writeData(tasklist.taskList);
    }

    /**
     * String representation of the deadline task.
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))  + ")";
    }

    /**
     * Formatted String representation of the task.
     * @return
     */
    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "E | " + doneOrNot + " | " + this.name + " | " +
                at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

}
