package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents an event item in the taskList.
 */
public class Event extends Task{
    LocalDate time;
    public Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);
    }

    public Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = LocalDate.parse(time);
    }
    /**
     * formats the Date time.
     * @return Formatted date time.
     */
    String printTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Shows the status and type of task.
     * @return [E] and tick or cross depending in the status.
     */
    @Override
    public String getStatusIcon() {
        return String.format("[E]%s", super.getStatusIcon(), printTime());
    }

    /**
     * Returns a string that can be stored in data.txt.
     * @return String that has the details of this object.
     */
    @Override
    public String writeToFile() {
        int done = isDone ? 1 : 0;
        return String.format("E//%d//%s//%s\n", done, this.description, this.time );
    }

    /**
     * Creates the string that is shown to the user.
     * @return String.
     */
    @Override
    public String getOutput() {
        return String.format("%s %s(At: %s)", getStatusIcon(), this.description, printTime());
    }
}
