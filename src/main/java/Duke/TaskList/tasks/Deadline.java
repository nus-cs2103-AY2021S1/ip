package Duke.TaskList.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Deadline extends Task {
    private LocalDateTime due;
    private DateTimeFormatter inFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private DateTimeFormatter outFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    public Deadline(String string) throws DateTimeParseException {
        super(string.substring(0, string.indexOf("/") - 1), string, string.indexOf("/"));
        this.due = LocalDateTime.parse(string.substring(string.indexOf("/") + 4), inFormat);
    }
    public String toString() {
        return "[D] " + super.toString() + " (by: " + due.getDayOfWeek() + " " + due.format(outFormat) + ")";
    }

    @Override
    public void update(String newTask) {
        task = newTask;
        fullText = newTask + fullText.substring(commandIndex);
    }
}
