public class DukeException extends RuntimeException {

    public DukeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    @Override
    public String toString() {
        return "____________________________________________________________\n" + super.toString() + "\n____________________________________________________________";
    }
}

