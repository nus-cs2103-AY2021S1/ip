import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ComplexTaskManager {

    private final String taskDetails;
    private final TaskType taskType;

    protected ComplexTaskManager(String taskDetails, TaskType taskType) {
        this.taskDetails = taskDetails;
        this.taskType = taskType;
    }

    private String identifier() {
        if (taskType == TaskType.DEADLINE) {
            return " /by";
        } else {
            return " /at";
        }
    }

    protected ComplexTask getTask() throws DukeException {
        String[] inputArr = taskDetails.split(identifier(), 2);
        if (inputArr.length == 1) {
            if (taskType == TaskType.DEADLINE) {
                throw new InvalidDeadlineException();
            } else {
                throw new InvalidEventException();
            }
        }
        checkIfEmpty(inputArr);
        String date = inputArr[1].trim();
        if (isDateAndTimeFormat(date.replace(" ", "T"))) {
            return new ComplexTask(inputArr[0], dateAndTimeToString(date), taskType);
        } else if (isDateFormat(date)) {
            return new ComplexTask(inputArr[0], dateToString(date), taskType);
        } else if (isTimeFormat(date)) {
            return new ComplexTask(inputArr[0], timeToString(date), taskType);
        } else {
            return new ComplexTask(inputArr[0], date, taskType);
        }
    }

    private void checkIfEmpty(String[] inputArr) throws DukeException {
        String description = inputArr[0];
        String time = inputArr[1];
        if (description.isEmpty()) {
            throw new EmptyTaskException(taskType);
        } else if (time.isBlank()) {
            throw new EmptyByException(taskType);
        }
    }

    private String dateToString(String date) {
        return LocalDate.parse(date).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String dateAndTimeToString(String dateAndTime) {
        return LocalDateTime
                .parse(dateAndTime.replace(" ", "T"))
                .format(DateTimeFormatter.ofPattern("MMM d yyyy / h.mm a"));
    }

    private String timeToString(String time) {
        return LocalTime.parse(time).format(DateTimeFormatter.ofPattern("h.mm a"));
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
}
