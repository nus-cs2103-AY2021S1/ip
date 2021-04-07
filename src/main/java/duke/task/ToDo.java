package duke.task;

import java.time.LocalDate;

/**
 * Represents a To-Do Item in the TaskList.
 */
public class ToDo extends Task {

    /**
     * Creates a new To-Do Item.
     * @param description of To-Do item.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a {@code String} to be written into saved data.
     */
    @Override
    public String storageForm() {
        return "T" + ", "  + (isDone ? "1" : "0") + ", " + description;
    }

    @Override
    public String getType() { return "Todo"; }

    @Override
    public LocalDate getDate() { return LocalDate.MIN; }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
