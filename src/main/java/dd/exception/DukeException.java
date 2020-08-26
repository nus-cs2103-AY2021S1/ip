package dd.exception;

public class DukeException extends Exception {

    private String message;

    public DukeException(String errorMessage) {
        message = errorMessage;
    }

    public DukeException() {

    }

    public String getMessage() {
        return this.message;
    }

    public DukeException emptyCheckDate(String date) {
        String msg = "No tasks found on " + date + "!";

        return new DukeException(msg);
    }

    public DukeException emptyCheckDesc(String desc) {
        String msg = "No tasks related to " + desc + "!";

        return new DukeException(msg);
    }

    public DukeException invalidCheckDate() {
        String msg = "I don't understand :( Please input date as DD-MM-YYYY\n"
                + "Example: 31-12-2020";

        return new DukeException(msg);
    }

    public DukeException invalidDate() {
        String msg = "I don't understand :( Please input date as DD-MM-YYYY or DD-MM-YYYY HHmm\n"
                + "Example: 31-12-2020 or 31-12-2020 2359";

        return new DukeException(msg);
    }

    public DukeException invalidDeadline() {
        String msg = "Due date not detected, try again!\n"
                + "Please input deadline as 'deadline (title) /by (date)'\n"
                + "Example: deadline return book /by 31-12-2020";

        return new DukeException(msg);
    }

    public DukeException invalidEvent() {
        String msg = "Event date not detected, try again!\n"
                + "Please input event as 'event (title) /at (date)'\n"
                + "Example: event group meeting /at 31-12-2020";

        return new DukeException(msg);
    }

    public DukeException invalidTaskNumber() {
        String msg = "hmm.. I don't think thats a valid task, try again?";

        return new DukeException(msg);
    }

    public DukeException noData() {
        String msg = "No data written to file.";

        return new DukeException(msg);
    }
}
