import tasks.Deadline;
import tasks.Event;
import tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DateTimeHandler {

    public boolean checkInput(String input) {
        if (input.length() == 10) {
            try {
                convertDate(input);
                return true;
            }
            catch (DateTimeParseException e) {
                return false;
            }
        }
        else if (input.length() == 15) {
            try {
                convertDateTime(input);
                return true;
            }
            catch (DateTimeParseException e) {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public String categorizeInput(String input) {
        if (input.length() == 10) {
            return convertDate(input);
        }
        else {
            return convertDateTime(input);
        }
    }

    public String convertDate(String input) {
        DateTimeFormatter inputFormat = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy")
                .toFormatter();

        LocalDate parsedDate = LocalDate.parse(input, inputFormat);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");

        return parsedDate.format(outputFormat);
    }

    public String convertDateTime(String input) {
        DateTimeFormatter inputFormat = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy HHmm")
                .toFormatter();

        LocalDateTime parsedDate = LocalDateTime.parse(input, inputFormat);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");

        return parsedDate.format(outputFormat);
    }

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
                    tasksOnDate.add(t);
                }
            }
            else if (t instanceof Event) {
                LocalDate eventDate = LocalDate.parse(((Event) t).getDate(), taskFormat);
                if (eventDate.isEqual(queryDate)) {
                    tasksOnDate.add(t);
                }
            }
        }

        return tasksOnDate;
    }
}
