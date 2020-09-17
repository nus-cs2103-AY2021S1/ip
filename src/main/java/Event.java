/**
 * The Event class acts as a task that occurs on a specific day.
 * It extends the parent class Task and has a LocalDate at to represent the specified date
 */
public class Event extends Task {
    private String time;
    Event(String name, String time) {
        this(name, time, false);
    }


    Event(String name, String time, boolean isCompleted) {
        super(name, isCompleted);
        this.time = time;
    }

    /**
     * Creates an event from the user command
     * @param description
     * @return a event with details from the user input
     * @throws DukeException
     */
    public static Event create(String description) throws DukeException {
        String[] keywords = description.split(" /at ", 2);
        if (keywords.length < 2) {
            throw new DukeException("Add a time using \" /at <time>\".");
        }
        assert keywords.length == 2: "description length should be 2";
        return new Event(keywords[0], keywords[1]);
    }
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + time;
    }
}
