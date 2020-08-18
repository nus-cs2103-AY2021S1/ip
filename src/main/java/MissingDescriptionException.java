public class MissingDescriptionException extends DukeException {
    MissingDescriptionException(String taskType) {
        super("The description of the " + taskType + " is missing (¬ ¬ )");
    }
}
