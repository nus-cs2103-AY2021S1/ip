public class DukeException extends Exception {
    String errorMessage;

    /**
     * Instantiates DukeException object.
     * @param errorMessage Error message to be displayed.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Overrides toString method so as to customize output string format.
     * @return String in our desired format.
     */
    @Override
    public String toString() {
        return this.errorMessage;
    }
}
