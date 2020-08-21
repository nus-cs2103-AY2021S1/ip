public class EmptyTaskException extends DukeException{
    protected EmptyTaskException(TaskType complexTask) {
        super(String.format("The description of a %s cannot be empty.", complexTask));
    }
}
