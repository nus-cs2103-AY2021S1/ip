package duke;

public class DukeResponse {

    private String string;
    private boolean isError;

    /**
     * Constructor to initialise Duke Response object
     *
     * @param string String to display.
     * @param isError boolean to indicate whether there is an error.
     */
    public DukeResponse(String string, boolean isError) {
        this.string = string;
        this.isError = isError;
    }

    public String getString() {
        return string;
    }

    public boolean isError() {
        return isError;
    }

    @Override
    public String toString() {
        return string;
    }
}
