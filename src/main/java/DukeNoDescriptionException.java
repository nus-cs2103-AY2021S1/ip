public class DukeNoDescriptionException extends DukeException {
    DukeNoDescriptionException(String input) {
        super(input);
    }
    
    @Override
    public String toString() {
        return "ERROR: Duke can't find your task details -> " + input;
    }
}
