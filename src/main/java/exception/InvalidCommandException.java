package exception;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException (String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\nAvailable commands are: "
            + "\n1. todo <todo_desc>"
            + "\n2. deadline <deadline_desc> /by <time>"
            + "\n3. event <event_desc> /at <time>"
            + "\n4. done <task_no>"
            + "\n5. delete <task_no>"
            + "\n6. find <task_desc_or_tag>"
            + "\n7. tag <task_no> <tags_separated_by_comma>"
            + "\n8. list"
            + "\n9. bye";
    }
}
