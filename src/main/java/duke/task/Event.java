package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    public static Event load(String loadTask) {
        String[] splitTask = loadTask.split(" \\| ", 4);
        Event event = new Event(splitTask[2], splitTask[3]);
        if (splitTask[1].equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String save(int isFinished) {
        return "E | " + super.save(isFinished) + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
