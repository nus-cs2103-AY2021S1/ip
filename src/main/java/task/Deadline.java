package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.DukeException;
import magic.Format;
import magic.MyString;

public class Deadline extends Task {
    private static final DateTimeFormatter MY_DATE_FORMATTER =
            DateTimeFormatter.ofPattern(Format.LOCAL_DATE);
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
            throw new DukeException(MyString.ERROR_INVALID_DATE);
        }

    }

    @Override
    public String getParsedData() {
        String[] args = new String[]{MyString.DATA_DEADLINE_SYMBOL, String.valueOf(super.isDone),
            super.tag, super.name, this.by};
        return String.join(MyString.DATA_TASK_SEP, args);
    }

    @Override
    public String toString() {
        String tag = "";
        if (!super.tag.isEmpty()) {
            tag = Task.TAG_ICON + super.tag;
        }

        return String.format(Format.DISPLAY_DEADLINE, MyString.DATA_DEADLINE_SYMBOL,
                super.toString(), this.byFormat, tag);
    }
}
