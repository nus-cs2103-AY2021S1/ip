package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDeadlineException;
import duke.util.DukeDateTime;

public class Deadline extends Task {
    private static final String DEADLINE_SYMBOL = "[D]";
    private static final String DEADLINE_NAME = "deadline ";
    private static final String DEADLINE_KEYWORD = "/by ";

    private final String time12h;
    private final LocalDate date;

    private Deadline(String description, String time12h, LocalDate date) {
        super(description);
        this.time12h = time12h;
        this.date = date;
    }

    /**
     * Factory method for creating a deadline task.
     *
     * @param details String details of the task.
     * @return Deadline the deadline task.
     * @throws InvalidDeadlineException If the format of the details is invalid.
     */
    protected static Deadline createDeadline(String details) throws InvalidDeadlineException {
        boolean hasKeyword = details.contains(DEADLINE_KEYWORD);

        if (!hasKeyword) {
            throw new InvalidDeadlineException();
        }
        String[] info = details.split(DEADLINE_KEYWORD);
        String desc = info[0];
        String[] dateTime = info[1].trim().split(" ");
        if (dateTime.length < 2) {
            throw new InvalidDeadlineException();
        }
        try {
            LocalDate date = DukeDateTime.parseDate(dateTime[0]);
            String time12h = DukeDateTime.to12HTimeFormat(dateTime[1]);
            return new Deadline(desc, time12h, date);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new InvalidDeadlineException();
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
        String deadlineCommand = (isDone ? 1 : 0) + DEADLINE_NAME + description + DEADLINE_KEYWORD + date + " " + time;
        return deadlineCommand + " " + getTagsSaveString();
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
        return DEADLINE_SYMBOL + super.toString() + "(by: " + formattedDate + " " + time12h + ")" + displayTags;
    }
}
