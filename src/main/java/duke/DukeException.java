package duke;

/**
 * Special exception class for Duke
 */
public class DukeException extends Exception {
    /**
     * DukeException constructor
     *
     * @param s Exception error message
     */
    public DukeException(String s){
        super(s);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n Please restart!";
    }
}
