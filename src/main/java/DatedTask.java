// DatedTask.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

import java.util.List;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DatedTask {

	public static List<String> parse(String kind, String input, String dateSpec, String usage)
        throws InvalidInputException {

        var slash = input.indexOf('/');

        if (slash == 0 || input.isEmpty()) {
            throw new InvalidInputException("task description cannot be empty", usage);

        } else if (slash == -1) {
            throw new InvalidInputException(String.format("%s requires a date", kind), usage);
        }

        var item = input.substring(0, slash).strip();
        var when = input.substring(slash + 1).strip();

        assert !item.isEmpty();

        if (!when.startsWith(dateSpec + " ")) {
            throw new InvalidInputException("incorrect date specification", usage);
        }

        when = when.substring(3).strip();
        return Arrays.asList(item, when);
	}

    public static LocalDate parseDate(String date) throws InvalidInputException {
        // TODO: handle more formats, eg dd/mm/yy, dd/mm/yyyy, dd/mm
        // TODO: handle offsets from now, eg. +7d or something like that

        try {
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(String.format("invalid date format: %s", e.getMessage()),
                "yyyy-mm-dd");
        }
    }
}
