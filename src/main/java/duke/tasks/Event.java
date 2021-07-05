package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a event task.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructs a Event.
     *
     * @param description
     * @param at
     * @param isDone
     */
    public Event(String description, LocalDateTime at, Boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Marks the event as done.
     *
     * @return
     */
    @Override
    public Event markDone() {
        super.markDone();
        return this;
    }

    /**
     * Gets the type of the task.
     *
     * @return String representation of the type.
     */
    @Override
    public String getType() {
        return "event";
    }

    /**
     * Checks whether the tasks is on today.
     *
     * @return is today or not.
     */
    @Override
    public boolean isOnTheDate(LocalDate date) {
        return this.at.toLocalDate().isEqual(date);
    }

    /**
     * Adds the task into the list and print the added message.
     * Writes the changes into the file.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this);
        storage.writeData(taskList.getTaskList());
        return ui.showAddedMessage(taskList, taskList.getNumOfTasks() - 1);
    }

    /**
     * Shows the string representation of the deadline task.
     *
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Shows the formatted String representation of the task.
     *
     * @return
     */
    @Override
    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "E | " + doneOrNot + " | " + this.name + " | "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

}
