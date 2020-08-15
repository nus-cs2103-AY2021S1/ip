public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override public String getMessage() {
        String SPACES = "____________________________________________________________\n";
        return String.format("%s %s\n%s",SPACES, super.getMessage(), SPACES);
    }

}
