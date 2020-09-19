package juke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskDate {

    private static final DateTimeFormatter BACKWARDS = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter NORMAL = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter PRINT_STYLE = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private LocalDate date;

    private TaskDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Creates a TaskDate with given input text.
     * @param dateToParse Input text to be parsed.
     * @return Parsed Task Date.
     */
    public static TaskDate parse(String dateToParse) {
        try {
            return parseBackwardDate(dateToParse);
        } catch (DateTimeParseException exception) {
            return parseNormalDate(dateToParse);
        }
    }

    private static TaskDate parseBackwardDate(String dateToParse) throws DateTimeParseException {
        LocalDate parsedDate = LocalDate.parse(dateToParse, BACKWARDS);
        return new TaskDate(parsedDate);
    }

    private static TaskDate parseNormalDate(String dateToParse) throws DateTimeParseException {
        LocalDate parsedDate = LocalDate.parse(dateToParse, NORMAL);
        return new TaskDate(parsedDate);
    }

    public String saveDateToDisk() {
        return this.date.format(BACKWARDS);
    }

    @Override
    public String toString() {
        return this.date.format(PRINT_STYLE);
    }
}
