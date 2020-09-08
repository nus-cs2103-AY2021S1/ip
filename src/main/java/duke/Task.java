package duke;

import java.time.LocalDateTime;
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
    Task(String name, String startDateTime, String endDateTime) {
        isDone = false;
        this.name = name;
        this.startDateTime = parseDateTime(startDateTime);
        this.endDateTime = parseDateTime(endDateTime);
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
     * @param dateTimeString date in string format
     * @return returns the LocalDate
     */
    public LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        if (dateTimeString == "") {
            return null;
        } else {
            return LocalDateTime.parse(dateTimeString, format);
        }
    }


    /**
     * printDateTime method which takes in the date and time and converts it to String
     * @return returns String of date and time
     */
    public String printDateTime() {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMM yyyy");
        if (endDateTime == null){
            return formatDateTime.format(startDateTime);
        } else {
            if (startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())){
                return formatDate.format(startDateTime) + " " + startDateTime.toLocalTime() + "-"
                        + endDateTime.toLocalTime();
            } else {
                return formatDateTime.format(startDateTime) + " to " + formatDateTime.format(endDateTime);
            }
        }
    }

    public String getStartDateTime(){
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return formatDateTime.format(startDateTime);
    }

    public String getEndDateTime(){
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return formatDateTime.format(endDateTime);
    }

    public Task rescheduleTask(int num){
        startDateTime = startDateTime.plusHours(num);
        if (endDateTime != null){
            endDateTime = endDateTime.plusHours(num);
        }
        return this;
    }

}
