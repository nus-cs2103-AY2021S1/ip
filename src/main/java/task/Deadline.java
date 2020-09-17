package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.DukeException;

public class Deadline extends Task {
    public static final String COMMAND = "deadline";
    public static final String DELIMITER = "/by";
    private static final DateTimeFormatter MY_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("LLL dd uuuu");
    private static final String DISPLAY_SYMBOL = "[D]";
    private static final String PARSED_SYMBOL = "D";
    private final String by;
    private final String byFormat;
    private final LocalDate date;


    /**
     * Create Deadline task based on the name and due date.
     *
     * @param name Name of Deadline Task
     * @param by   Date of deadline in the format "LLL dd uuuu"
     * @throws DukeException when error in creating a date.
     */
    public Deadline(String name, String by) throws DukeException {
        super(name);
        this.by = by;
        try {
            this.date = LocalDate.parse(by);
            this.byFormat = this.date.format(MY_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date Format.\nPlease input in the following format:\n"
                    + "YYYY-mm-dd");
        }

    }

    @Override
    public String getParsedData() {
        String[] args = new String[]{Deadline.PARSED_SYMBOL, String.valueOf(super.isDone),
            super.tag, super.name, this.by};
        return String.join(Task.DELIMITER, args);
    }

    @Override
    public String toString() {
        String tag = "";
        if (!super.tag.equals("")) {
            tag = Task.TAG_ICON + super.tag;
        }

        return String.format("%s%s (by: %s) %s", Deadline.DISPLAY_SYMBOL,
                super.toString(), this.byFormat, tag);
    }
}
