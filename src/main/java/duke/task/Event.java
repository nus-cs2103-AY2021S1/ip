package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidEventException;
import duke.util.DukeDateTime;

public class Event extends Task {
    private static final String EVENT_SYMBOL = "[E]";
    private static final String EVENT_NAME = "event ";
    private static final String EVENT_KEYWORD = "/at ";

    private final String time12h;
    private final LocalDate date;

    private Event(String description, String time12h, LocalDate date) {
        super(description);
        this.time12h = time12h;
        this.date = date;
    }

    /**
     * Factory method for creating an event task.
     *
     * @param details String details of the task.
     * @return Event the event task.
     * @throws InvalidEventException If the format of the details is invalid.
     */
    protected static Event createEvent(String details) throws InvalidEventException {
        boolean hasKeyword = details.contains(EVENT_KEYWORD);

        if (!hasKeyword) {
            throw new InvalidEventException();
        }

        String[] info = details.split(EVENT_KEYWORD);
        String desc = info[0];
        String[] dateTime = info[1].trim().split(" ");
        try {
            LocalDate date = DukeDateTime.parseDate(dateTime[0]);
            String time12h = DukeDateTime.to12HTimeFormat(dateTime[1]);
            return new Event(desc, time12h, date);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new InvalidEventException();
        }
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        return this.date.equals(date);
    }

    @Override
    public String toSaveString() {
        String date = DukeDateTime.localDateToString(this.date);
        String time = DukeDateTime.to24HTimeFormat(time12h);
        String eventCommand = (isDone ? 1 : 0) + EVENT_NAME + description + EVENT_KEYWORD + date + " " + time;
        return eventCommand + " " + getTagsSaveString();
    }

    @Override
    public boolean isDueInNDays(int n) {
        assert n >= 0 : "isDueInNDays should receive a non-negative input";

        return DukeDateTime.isWithinNDays(date, n);
    }

    @Override
    public String toString() {
        String formattedDate = DukeDateTime.localDateToFormattedString(date);
        String tags = stringifyTags();
        String displayTags = tags.equals("")
                ? ""
                : " Tags: " + tags;
        return EVENT_SYMBOL + super.toString() + "(at: " + formattedDate + " " + time12h + ")" + displayTags;
    }
}
