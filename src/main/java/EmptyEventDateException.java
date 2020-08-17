public class EmptyEventDateException extends IllegalArgumentException {

    public EmptyEventDateException() {
        super("OOPS! The date / time of event cannot be empty!");
    }
}
