package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Deadline extends Task implements Serializable {
    private LocalDate end;

    public Deadline(String s, LocalDate e) {
        super(s);
        end = e;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate e) {
        end = e;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + (super.isDone() ? "O" : "X") + "] " + super.getName() + " (by: "
                + end.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")) + ")";
    }
}
