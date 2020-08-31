package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.ui.Ui;
import duke.tool.Storage;
import duke.tool.TaskList;

/**
 * Represent a event task.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructs a Event.
     * @param description
     * @param at
     * @param isDone
     */
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
     * @return
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.add(this);
        storage.writeData(tasklist.getTaskList());
        return ui.showAddedMessage(tasklist, tasklist.getNumOfTasks() - 1);
    }

    /**
     * String representation of the deadline task.
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))  + ")";
    }

    /**
     * Formatted String representation of the task.
     * @return
     */
    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "E | " + doneOrNot + " | " + this.name + " | "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

}
