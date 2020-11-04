package dd.datetimehandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import dd.tasks.Deadline;
import dd.tasks.Event;
import dd.tasks.Task;

/**
 * A date time handler checks if a date input given is valid and
 * parses them according to the given format.
 */
public class DateTimeHandler {

    /**
     * Returns whether input is a valid date or date and time input.
     *
     * @param input String containing input date or date and time.
     * @return true if input is valid, false if not.
     */
    public boolean isValidInput(String input) {
        int dateLength = 10;
        int dateTimeLength = 15;

        if (input.length() == dateLength) {
            try {
                convertDate(input);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        } else if (input.length() == dateTimeLength) {
            try {
                convertDateTime(input);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Returns the appropriate method based on whether input consists of only date
     * or both date and time.
     *
     * @param input String containing input date or date and time.
     * @return convertDate() method if date input given,
     * convertDateTime() method if date and time input is given.
     */
    public String categorizeInput(String input) {
        int dateLength = 10;
        int dateTimeLength = 15;

        if (input.length() == dateLength) {
            return convertDate(input);
        } else if (input.length() == dateTimeLength) {
            return convertDateTime(input);
        } else {
            assert false : "invalid input to categorize";
            return input;
        }
    }

    private String convertDate(String input) {
        // define the input and output date formats
        DateTimeFormatter inputFormat = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy")
                .toFormatter();
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");

        // parse the input according to the defined input format
        LocalDate parsedDate = LocalDate.parse(input, inputFormat);

        String outputDate = parsedDate.format(outputFormat);
        assert outputDate.length() == 11;

        return outputDate;
    }

    private String convertDateTime(String input) {
        // define the input and output date and time formats
        DateTimeFormatter inputFormat = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy HHmm")
                .toFormatter();
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");

        // parse the input according to the defined input format
        LocalDateTime parsedDate = LocalDateTime.parse(input, inputFormat);

        String outputDateTime = parsedDate.format(outputFormat);
        assert outputDateTime.length() == 20;

        return outputDateTime;
    }

    /**
     * Filters an ArrayList of tasks given according to date given.
     *
     * @param date String containing input date.
     * @param tasks ArrayList of tasks to filter from.
     * @return ArrayList of tasks in given tasks that fall on the date given.
     */
    public ArrayList<Task> filterDate(String date, ArrayList<Task> tasks) {
        DateTimeFormatter inputFormat = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy")
                .toFormatter();
        DateTimeFormatter taskFormat = new DateTimeFormatterBuilder().appendPattern("dd MMM yyyy")
                .toFormatter();

        ArrayList<Task> tasksOnDate = new ArrayList<>();
        LocalDate queryDate = LocalDate.parse(date, inputFormat);

        for (Task t : tasks) {
            if (t instanceof Deadline) {
                LocalDate deadlineDate = LocalDate.parse(((Deadline) t).getDate(), taskFormat);

                if (deadlineDate.isEqual(queryDate)) {
                    int initialNum = tasksOnDate.size();
                    tasksOnDate.add(t);

                    assert tasksOnDate.size() - initialNum == 1 : "matched task was not added into list";
                }
            } else if (t instanceof Event) {
                LocalDate eventDate = LocalDate.parse(((Event) t).getDate(), taskFormat);

                if (eventDate.isEqual(queryDate)) {
                    int initialNum = tasksOnDate.size();
                    tasksOnDate.add(t);

                    assert tasksOnDate.size() - initialNum == 1 : "matched task was not added into list";
                }
            }
        }

        return tasksOnDate;
    }
}
