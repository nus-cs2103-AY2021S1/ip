/**
 * Represents the exception when the user is trying to complete or delete a
 * task that does not exist.
 */
public class MissingTaskException extends DukeException {
    MissingTaskException(int taskIndex) {
        super("There's no task with the tag " + taskIndex + " (・・;)ゞ");
    }
}
