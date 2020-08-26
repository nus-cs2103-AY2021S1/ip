import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String name, String at, boolean done) throws DukeException {
        super(name, TaskType.EVENT, done);
        try {
            this.at = LocalDateTime.parse(at.substring(1), Duke.dateTimeFormat);
        } catch (Exception e) {
            throw new DukeWrongFormattingException();
        }

    }

    public static void newEvent(String inputSuffix, TaskList taskList, boolean done, boolean announce)
            throws DukeException {
        String[] eventParts = inputSuffix.split("/at",2);
        String eventName = eventParts[0];
        if (eventParts.length == 1) {
            throw new DukeEmptyDescException(TaskType.EVENT);
        } else {
            String at = eventParts[1];
            if (Ui.isBlankString(at)) {
                throw new DukeEmptyDescException(TaskType.EVENT);
            } else {
                taskList.addTask(new Event(eventName, at, done), announce);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s(at: %s)", super.toString(), at.format(Duke.dateTimeFormat));
    }

    public String toData() {
        return String.format("[E]%s/at %s", super.toString(), at.format(Duke.dateTimeFormat));
    }

}