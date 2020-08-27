public class DukeDateTimeException extends DukeException {
    DukeDateTimeException(String input) {
        super(input);
    }
    
    @Override
    public String toString() {
        return "ERROR: Duke doesn't recognise the date/time -> " + input;
    }
}
