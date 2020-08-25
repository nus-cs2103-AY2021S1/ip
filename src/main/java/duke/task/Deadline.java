package duke.task;

import duke.util.DateFormatter;

import java.util.Date;

public class Deadline extends Task {
    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }
    
    @Override
    public String toSaveFormat() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, 
                this.description, DateFormatter.formatSave(this.by)
        );
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DateFormatter.formatDisplay(this.by));
    }
}
