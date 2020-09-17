import java.time.LocalDateTime;
import java.util.Objects;

public class Event extends Task {

    protected LocalDateTime at;

    private Event(String name, String at, boolean isDone) throws DukeException {
        super(name, TaskType.EVENT, isDone);
        try {
            this.at = LocalDateTime.parse(at.substring(1), Ui.dateTimeFormat);
        } catch (Exception e) {
            throw new DukeWrongFormattingException();
        }

    }

    /**
     * Adds a new event to the task list.
     * @param inputSuffix String containing the description and dateTime of the new event.
     * @param taskList The taskList to have the new event added to.
     * @param isDone Boolean representing if the task has been done.
     * @param shouldAnnounce A boolean representing if the creation of the event should be displayed in the GUI.
     */
    public static void newEvent(String inputSuffix, TaskList taskList, boolean isDone, boolean shouldAnnounce)
            throws DukeException {
        String[] eventParts = inputSuffix.split("/at", 2);
        String eventName = eventParts[0];
        if (eventParts.length == 1) {
            throw new DukeEmptyDescException(TaskType.EVENT);
        } else {
            String at = eventParts[1];
            System.out.println(at);
            if (Ui.isBlankString(at)) {
                throw new DukeEmptyDescException(TaskType.EVENT);
            } else {
                taskList.addTask(new Event(eventName, at, isDone), shouldAnnounce);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s(at: %s)", super.toString(), at.format(Ui.dateTimeFormat));
    }

    @Override
    public String toData() {
        return String.format("[E]%s/at %s", super.toString(), at.format(Ui.dateTimeFormat));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else if (!super.equals(o)) {
            return false;
        } else {
            Event event = (Event) o;
            return at.equals(event.at);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), at);
    }
}
