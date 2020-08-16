public class DukeException extends Exception {
    private DukeException(String msg) {
        super(msg);
    }

    public static DukeException emptyList() {
        return new DukeException("Your list is empty.");
    }

    public static DukeException doneTypeMismatch() {
        return new DukeException("Error! Integer should follow 'done' command.");
    }

    public static DukeException doneOutOfBounds() {
        return new DukeException("Error! Enter a valid task number.");
    }

    public static DukeException emptyDesc(String taskType) {
        return new DukeException("Error! '" + taskType + "' description cannot be empty.");
    }

    public static DukeException unknownCommand() {
        return new DukeException("Sorry, I don't understand what you just said.");
    }

    public static DukeException missingTask() {
        return new DukeException("Error! No task description provided.");
    }

    public static DukeException missingTime(String byOrAt) {
        return new DukeException("Error! '/" + byOrAt + "' date not found.");
    }
}
