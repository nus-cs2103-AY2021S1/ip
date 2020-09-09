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
        String repr = "[D]"
                + super.toString()
                + " (by: "
                + this.taskBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
        for (String tag : tags) {
            repr += " #" + tag;
        }
        return repr;
    }

    public String toFileFormat() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.taskName + "|" + this.taskBy + "|" + tagsFileFormat();
    }

    /**
     * Loads the file format String representation of Deadline task.
     *
     * @return Deadline loaded from file format representation
     */
    static Deadline fromFileFormat(String fileFormatString) {
        String[] tokens = fileFormatString.split("\\|");
        Deadline loaded = new Deadline(tokens[2], LocalDate.parse(tokens[3]));
        if (tokens.length >= 5) {
            String[] tags = tokens[4].split("#");
            for (String tag : tags) {
                loaded.addTag(tag);
            }
        }

        if (tokens[1].equals("1")) {
            loaded.setDone();
        }
        return loaded;
    }
}
