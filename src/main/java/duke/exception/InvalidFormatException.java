public class InvalidFormatException extends DukeException{
    public InvalidFormatException(String missing) {
        super(missing + " is missing");
    }

    public String toString() {
        return getMessage();
    }
}
