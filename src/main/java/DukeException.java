abstract class DukeException extends Exception {
    protected String message;

    DukeException(String message) {
        this.message = "Something went wrong... \n " + message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}

