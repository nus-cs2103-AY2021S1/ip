public class EventMissingDateException extends DukeException {

    public EventMissingDateException() {
        super("Your event is missing a date! Please include a date preceded by /at");
    }
}
