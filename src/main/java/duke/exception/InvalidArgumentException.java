package duke.exception;

public class InvalidArgumentException extends DukeException {
    private String arg;

    public InvalidArgumentException(String arg) {
        this.arg = arg;
    }

    @Override
    public String toString () {
        return String.format("You cant let %s be that patrick!!", arg);
    }
}
