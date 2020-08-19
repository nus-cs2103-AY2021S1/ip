public class DukeNoItemToMarkDoneException extends DukeException {
    DukeNoItemToMarkDoneException(String input) {
        super(input);
    }
    
    @Override
    public String toString() {
        return "ERROR: Duke doesn't know what to mark as done -> " + input;
    }
}
