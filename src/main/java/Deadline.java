import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate taskBy;

    Deadline(String taskName, LocalDate taskBy) {
        super(taskName);
        this.taskBy = taskBy;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.taskBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }

    @Override
    public String toFileFormat() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.taskName + "|" + this.taskBy;
    }

    /**
     * Loads the file format String representation of Deadline task.
     *
     * @return Deadline loaded from file format representation
     */
    static Deadline fromFileFormat(String fileFormatString) {
        String[] tokens = fileFormatString.split("\\|");
        Deadline loaded = new Deadline(tokens[2], LocalDate.parse(tokens[3]));
        if (tokens[1].equals("1")) {
            loaded.setDone();
        }
        return loaded;
    }
}
