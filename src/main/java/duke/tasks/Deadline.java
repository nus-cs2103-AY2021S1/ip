package duke.tasks;

import duke.Tag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import java.util.List;

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
        List<Tag> tags = super.getTags();
        String s = "";

        if (!tags.isEmpty()) {
            for (int i = 0; i < tags.size(); i++) {
                s += " #" + tags.get(i);
            }
        }
        return "[D]" + "[" + (super.isDone() ? "O" : "X") + "] " + super.getName() + " (by: "
                + end.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")) + ")" + s;
    }
}
