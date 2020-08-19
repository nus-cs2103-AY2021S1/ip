public class EmptyDescriptionException extends DukeException {
    private String type;

    public EmptyDescriptionException(String type) {
        this.type = type;
    }

    public String toString() {
        return "____________________________________________________________"
                + "\n"
                + " ☹ OOPS!!! The description of a " + type + " cannot be empty."
                + "\n"
                + "____________________________________________________________";
    }
}
