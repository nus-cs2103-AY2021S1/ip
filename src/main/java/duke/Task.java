package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Task class to store task information
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Task {
    private boolean isDone;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Task constructor to initialize a task object with the name
     * @param name name of task
     */
    Task(String name) {
        isDone = false;
        this.name = name;
        this.startDateTime = null;
        this.endDateTime = null;
    }

    /**
     * Task constructor to initialize a task object with the name and time
     * @param name name of task
     */
    Task(String name, String startDateTime, String endDateTime) throws DukeException {
        isDone = false;
        this.name = name;
        String[] startArray = startDateTime.split(" ", 2);
        String[] endArray = endDateTime.split(" ", 2);
        if (endDateTime != "") {
            if (startArray.length == 2 || endArray.length == 2) {
                this.startDateTime = LocalDateTime.of(parseDate(startArray[0]), parseTime(startArray[1]));
                this.endDateTime = LocalDateTime.of(parseDate(endArray[0]), parseTime(endArray[1]));
            } else {
                throw new DukeException("Please key in a valid date and time");
            }
        } else {
            if (startArray.length == 2) {
                this.startDateTime = LocalDateTime.of(parseDate(startArray[0]), parseTime(startArray[1]));
                this.endDateTime = null;
            } else {
                throw new DukeException("Please key in a valid date and time");
            }
        }
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return isDone;
    }

    /**
     * completedTask method which marks the task as completed
     * @return the completed task
     */
    public Task completeTask() {
        isDone = true;
        return this;
    }

    /**
     * parseDate method which takes in a date in string form and converts it to a LocalDate object
     * @param dateString date in string format
     * @return returns the LocalDate
     * @throws DukeException when input cannot be processed
     */
    public LocalDate parseDate(String dateString) throws DukeException {
        dateString = dateString.replaceAll("\\s+", "");
        String[] dateArray = dateString.split("[/]|[.]|[-]", 3);
        if (dateArray.length != 3) {
            throw new DukeException("Please key in a valid date!");
        }
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        if (year < 100) {
            throw new DukeException("Please key in a valid 4 digit year!");
        }
        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new DukeException("Please key in a valid date!");
        }
    }

    /**
     * parseTime method which takes in a time in string form and converts it to a LocalTime object
     * @param timeString time in string format
     * @return returns the LocalTime
     * @throws DukeException when input cannot be processed
     */
    public LocalTime parseTime (String timeString) throws DukeException {
        try {
            timeString = timeString.replaceAll("\\s+", "");
            int hour = 0;
            int minute = 0;
            if (timeString.contains("PM") || timeString.contains("pm")) {
                String timeNum = timeString.substring(0, timeString.length() - 2);
                String[] timeArray = timeNum.split("[:]|[.]|[-]", 2);
                if (timeArray.length == 2) {
                    hour = Integer.parseInt(timeArray[0]) + 12;
                    minute = Integer.parseInt(timeArray[1]);
                } else {
                    hour = Integer.parseInt(timeArray[0]) + 12;
                }
            } else if (timeString.contains("AM") || timeString.contains("am")) {
                String timeNum = timeString.substring(0, timeString.length() - 2);
                String[] timeArray = timeNum.split("[:]|[.]", 2);
                if (timeArray.length == 2) {
                    hour = Integer.parseInt(timeArray[0]);
                    minute = Integer.parseInt(timeArray[1]);
                } else {
                    hour = Integer.parseInt(timeArray[0]);
                }
            } else if (timeString.length() < 4) {
                throw new DukeException("Please key in a valid time!");
            } else {
                String[] timeArray = timeString.split("[:]|[.]|[-]", 2);
                if (timeArray.length == 1) {
                    hour = Integer.parseInt(timeString.substring(0, 2));
                    minute = Integer.parseInt(timeString.substring(2));
                } else {
                    hour = Integer.parseInt(timeArray[0]);
                    minute = Integer.parseInt(timeArray[1]);
                }
            }
            if (hour == 24) {
                hour = 12;
            }
            return LocalTime.of(hour, minute);
        } catch (DateTimeException | NumberFormatException e) {
            throw new DukeException("Please key in a valid time!");
        }

    }

    /**
     * printDateTime method which takes in the date and time and converts it to String
     * @return returns String of date and time
     */
    public String printDateTime() {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMM yyyy");
        if (endDateTime == null) {
            return formatDateTime.format(startDateTime);
        } else {
            if (startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())) {
                return formatDate.format(startDateTime) + " " + startDateTime.toLocalTime() + "-"
                        + endDateTime.toLocalTime();
            } else {
                return formatDateTime.format(startDateTime) + " to " + formatDateTime.format(endDateTime);
            }
        }
    }

    public String getStartDateTime() {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return formatDateTime.format(startDateTime);
    }

    public String getEndDateTime() {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return formatDateTime.format(endDateTime);
    }

    /**
     * snoozeTask method which takes a number of hours and returns a new task that's snoozed by that number of hours
     * @return Task with new time
     * @throws DukeException when user tries to snooze a todo
     */
    public Task snoozeTask(int num) throws DukeException {
        if (startDateTime == null) {
            throw new DukeException("Todo does not have any date time information!");
        } else if (endDateTime == null) {
            startDateTime = startDateTime.plusHours(num);
        } else {
            startDateTime = startDateTime.plusHours(num);
            endDateTime = endDateTime.plusHours(num);
        }
        return this;
    }

    /**
     * rescheduleTask method which takes a new Date and Time
     * @return Task with new date and time
     * @throws DukeException when user tries to reschedule a todo or when input is incorrect
     */
    public Task rescheduleTask(String newDateTime) throws DukeException {
        String name = this.name;
        if (startDateTime == null) {
            throw new DukeException("Todo does not have any date time information!");
        } else if (endDateTime == null) {
            return new Deadlines(name, newDateTime);
        } else {
            String[] dateTimeArray = newDateTime.split(" ", 2);
            if (dateTimeArray.length < 2) {
                throw new DukeException("Please specify a date and time range! \nEg. 25/12/2020 10pm - 11pm");
            }
            String[] timeArray = dateTimeArray[1].split("-", 2);
            if (timeArray.length < 2) {
                throw new DukeException("Please use - to specify the time range! \nEg. 10pm - 11pm");
            }
            String date = dateTimeArray[0];
            String startTime = timeArray[0];
            String endTime = timeArray[1];
            String startDateTime = date + " " + startTime;
            String endDateTime = date + " " + endTime;
            if (dateTimeArray.length <= 1) {
                throw new DukeException("You need to specify a time!");
            }
            return new Events(name, startDateTime, endDateTime);
        }
    }

}
