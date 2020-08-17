public class EmptyTaskException extends EmptyInputException {
    EmptyTaskException(String message) {
        super(message);
    }

    EmptyTaskException(String field, String type) {
        super(String.format("The %s of %s %s cannot be empty.", field, type.equals("event") ? "an" : "a", type));
    }
}
