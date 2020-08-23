import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ComplexTaskManager {

    private final String taskDetails;
    private final TaskType complexTask;

    protected ComplexTaskManager(String taskDetails, TaskType complexTask) {
        this.taskDetails = taskDetails;
        this.complexTask = complexTask;
    }

    protected Task getTask() throws DukeException {
        if (complexTask == TaskType.DEADLINE) {
            String[] inputArr = taskDetails.split(" /by", 2);
            if (inputArr.length == 1) {
                throw new InvalidDeadlineException();
            } else {
                checkIfEmpty(inputArr);
                String date = inputArr[1].trim();
                if (isDateAndTimeFormat(date)) {
                    return new Deadline(inputArr[0], LocalDateTime.parse(date));
                } else if (isDateFormat(date)) {
                    return new Deadline(inputArr[0], LocalDate.parse(date));
                } else if (isTimeFormat(date)) {
                    return new Deadline(inputArr[0], LocalTime.parse(date));
                } else {
                    return new Deadline(inputArr[0], date);
                }
            }
        } else { // EVENT type
            String[] inputArr = taskDetails.split(" /at", 2);
            if (inputArr.length == 1) {
                throw new InvalidEventException();
            } else {
                checkIfEmpty(inputArr);
                String date = inputArr[1].trim();
                if (isDateAndTimeFormat(date)) {
                    return new Event(inputArr[0], LocalDateTime.parse(date));
                } else if (isDateFormat(date)) {
                    return new Event(inputArr[0], LocalDate.parse(date));
                } else if (isTimeFormat(date)) {
                    return new Event(inputArr[0], LocalTime.parse(date));
                } else {
                    return new Event(inputArr[0], date);
                }
            }
        }
    }

    private boolean isDateFormat(String input) {
        try {
            LocalDate.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    private boolean isDateAndTimeFormat(String input) {
        try {
            LocalDateTime.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    private boolean isTimeFormat(String input) {
        try {
            LocalTime.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void checkIfEmpty(String[] inputArr) throws DukeException {
        String description = inputArr[0];
        String by = inputArr[1];
        if (description.isEmpty()) {
            throw new EmptyTaskException(complexTask);
        } else if (by.isBlank()) {
            throw new EmptyByException(complexTask);
        }
    }
}
