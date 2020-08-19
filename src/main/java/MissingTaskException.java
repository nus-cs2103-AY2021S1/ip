public class MissingTaskException extends DukeException {
    MissingTaskException(int taskIndex) {
        super("There's no task with the tag " + taskIndex + " (・・;)ゞ");
    }
}
