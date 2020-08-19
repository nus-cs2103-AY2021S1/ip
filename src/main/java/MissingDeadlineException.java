public class MissingDeadlineException extends DukeException{
    MissingDeadlineException(String taskType) {
        super("The deadline of the " + taskType + " is missing (¬ ¬ )");
    }
}
