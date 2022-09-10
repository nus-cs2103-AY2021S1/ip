package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {

    public Deadline(String description, LocalDate deadline) {
        super(description, deadline);
    }

    @Override
    public String createSaveDataLine() {
        return "D:" + getStatusLetter() + ":" + description + ":" + getSaveDate() + ":" + "tags " + listTags();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDate() + ") " + listTags();
    }

}
