public class EmptyInputException extends Exception {

    public EmptyInputException(String e) {
        super("☹ OOPS!!! The description of a " + e + " cannot be empty.");
    }
}
