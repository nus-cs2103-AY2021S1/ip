package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeConverter {

    /** DateTimeFormatter object for formatting purposes */
    protected DateTimeFormatter dtf;


    /**
     * Constructor of DateTimeConverter class.
     *
     * @param dateStyle  FormatStyle for LocalDate.
     * @param timeStyle  FormatStyle for LocalTime.
     */
    public DateTimeConverter(FormatStyle dateStyle, FormatStyle timeStyle) {
        dtf = DateTimeFormatter.ofLocalizedDateTime(dateStyle, timeStyle);
    }


    /**
     * Returns Processed date and time from user input.
     *
     * @param taskType  User input for task to add.
     * @param dateTime  User input for date and time of events.
     * @return Formatted date and time.
     */
    public String processTime(String taskType, String dateTime) {
        if (dateTime.equals("undecided")) {
            return dateTime;
        }
        String[] dateTimeArr = splitDateTime(taskType, dateTime);
        String[] dateTimeForConversion = cleanDateTime(dateTimeArr);
        LocalDateTime ldt = generateLocalDateTime(dateTimeForConversion);

        return ldt.format(dtf);

    }


    /**
     * Returns cleaned up date and time that are ready for formatting.
     *
     * @param dateTimeArr  Array containing date and time Strings processed from user input.
     * @return date and time ready for conversion to LocalDateTime.
     */
    public String[] cleanDateTime(String[] dateTimeArr) {
        String date = dateTimeArr[0];
        String time = dateTimeArr[1];

        date = date.replaceAll("\\D", "-");
        String[] dateSeg = date.split("-", 3);
        if (dateSeg[0].length() != 4) {
            String temp = dateSeg[0];
            dateSeg[0] = dateSeg[2];
            dateSeg[2] = temp;
        }

        String cleanDate = dateSeg[0] + "-" + dateSeg[1] + "-" + dateSeg[2];
        String cleanTimeHour = time.substring(0, 2);
        String cleanTimeMinute = time.substring(2);
        return new String[] {cleanDate, cleanTimeHour, cleanTimeMinute};

    }


    /**
     * Returns formatted LocalDateTime object.
     *
     * @param dateTimeForConversion  array containing cleaned date and time Strings.
     * @return formatted LocalDateTime object.
     */
    public LocalDateTime generateLocalDateTime(String[] dateTimeForConversion) {
        String cleanDate = dateTimeForConversion[0];
        Integer cleanHour = Integer.parseInt(dateTimeForConversion[1]);
        Integer cleanMinute = Integer.parseInt(dateTimeForConversion[2]);

        LocalDate ld = LocalDate.parse(cleanDate);
        LocalTime lt = LocalTime.of(cleanHour, cleanMinute);

        return LocalDateTime.of(ld, lt);
    }


    /**
     * Returns raw date and time information processed from user input Strings.
     *
     * @param taskType  type of task to be created.
     * @param dateTime  raw user input of datetime of the task.
     * @return raw date and time information.
     */
    public String[] splitDateTime(String taskType, String dateTime) {
        String date;
        String time;
        dateTime = dateTime.trim();
        if (dateTime.contains(" ")) {
            String[] parts = dateTime.split(" ", 2);
            boolean firstPartIsTime = parts[0].length() <= 4;
            if (firstPartIsTime) {
                time = parts[0];
                date = parts[1];
            } else {
                time = parts[1];
                date = parts[0];
            }
            // If time input contains only a single digit for hour
            if (time.length() == 1) {
                time = "0" + time;
            }
            // If time input contains only two digits for hour
            if (time.length() == 2) {
                time += "00";
            }
        } else {
            // If no input for time, set a default time for the task based on its type
            date = dateTime;
            time = taskType.equals("event")
                    ? "0000"
                    : "2359";
        }
        return new String[] {date, time};
    }
}